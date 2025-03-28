package org.udec.Modelo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Tablero implements Serializable {
    /**
     * Versión de serialización para manejar compatibilidad
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instancia única del tablero (patrón Singleton)
     */
    private static transient Tablero instance;

    /**
     * Mapa que contiene las columnas del tablero, donde cada clave es un estado de tarea
     * y su valor es la columna correspondiente.
     */
    private final Map<EstadoTarea, Columna> columnas;

    /**
     * Constructor privado para el patrón Singleton. Inicializa las columnas predeterminadas
     * del tablero.
     */
    private Tablero() {
        columnas = new HashMap<>();
        columnas.put(EstadoTarea.POR_HACER, new Columna(EstadoTarea.POR_HACER));
        columnas.put(EstadoTarea.EN_PROCESO, new Columna(EstadoTarea.EN_PROCESO));
        columnas.put(EstadoTarea.HECHO, new Columna(EstadoTarea.HECHO));
    }

    /**
     * Asigna una tarea a la columna correspondiente según su estado "POR_HACER".
     *
     * @param tarea La tarea que se agregará a la columna "POR_HACER".
     */
    public static void asignarColumna(Tarea tarea) {
        getInstance().getColumna(EstadoTarea.POR_HACER).agregarTarea(tarea);
    }

    /**
     * Obtiene la única instancia del tablero (Singleton).
     *
     * @return La instancia única del tablero.
     */
    public static synchronized Tablero getInstance() {
        if (instance == null) {
            instance = new Tablero();
        }
        return instance;
    }

    /**
     * Obtiene una columna por su estado.
     *
     * @param estado El estado de la columna que se desea obtener.
     * @return La columna correspondiente al estado proporcionado.
     */
    public Columna getColumna(EstadoTarea estado) {
        return columnas.get(estado);
    }

    /**
     * Mueve una tarea a otra columna.
     *
     * @param t La tarea que se moverá.
     * @param columna La columna a la que se moverá la tarea.
     */
    public void moverTarea(Tarea t, Columna columna) {
        // Eliminar de la columna original
        getColumna(t.getEstado()).eliminarTarea(t);

        // Cambiar estado
        t.setEstado(columna.getEstado());

        // Agregar a nueva columna
        columna.agregarTarea(t);
    }

    /**
     * Cuenta el total de tareas en todas las columnas del tablero.
     *
     * @return El número total de tareas en todas las columnas.
     */
    public int contarAllTareas() {
        int total = 0;
        for (Columna columna : columnas.values()) {
            total += columna.contarTareas();
        }
        return total;
    }

    /**
     * Resetea la instancia del tablero, útil para pruebas.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Guarda el tablero en un archivo
     *
     * @param rutaArchivo Ruta del archivo de guardado
     */
    public void guardarTablero(String rutaArchivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            out.writeObject(this);
            System.out.println("Tablero guardado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el tablero: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga un tablero desde un archivo
     *
     * @param rutaArchivo Ruta del archivo a cargar
     * @return Tablero cargado, o null si hay un error
     */
    public static Tablero cargarTablero(String rutaArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Tablero tableroCargado = (Tablero) in.readObject();

            // Importante: Actualizar la instancia singleton
            instance = tableroCargado;

            System.out.println("Tablero cargado exitosamente desde: " + rutaArchivo);
            return instance;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el tablero: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método readResolve para garantizar el Singleton durante la deserialización
     *
     * @return La instancia del tablero
     */
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}

