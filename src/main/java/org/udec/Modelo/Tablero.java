package org.udec.Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Representa un tablero Kanban que contiene las distintas columnas de tareas.
 * Utiliza el patrón de diseño Singleton para garantizar que haya una única instancia
 * del tablero que almacene los estados de las tareas y las columnas asociadas a cada uno.
 *
 * El tablero contiene tres columnas predeterminadas para representar los estados:
 * <ul>
 *     <li><b>POR_HACER:</b> Tareas que aún no han comenzado.</li>
 *     <li><b>EN_PROCESO:</b> Tareas que están en progreso.</li>
 *     <li><b>HECHO:</b> Tareas que han sido completadas.</li>
 * </ul>
 *
 * Esta clase ofrece métodos para agregar tareas a columnas, mover tareas entre columnas,
 * contar el total de tareas y obtener las columnas del tablero.
 */
public class Tablero {
    /**
     * Instancia única del tablero (patrón Singleton).
     */
    private static Tablero instance;

    /**
     * Mapa que contiene las columnas del tablero, donde cada clave es un estado de tarea
     * y su valor es la columna correspondiente.
     */
    private Map<EstadoTarea, Columna> columnas;

    /**
     * Asigna una tarea a la columna correspondiente según su estado "POR_HACER".
     *
     * @param tarea La tarea que se agregará a la columna "POR_HACER".
     */
    public static void asignarColumna(Tarea tarea) {
        Tablero.getInstance().getColumna(EstadoTarea.POR_HACER).agregarTarea(tarea);
    }

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
     * Obtiene la única instancia del tablero (Singleton).
     *
     * @return La instancia única del tablero.
     */
    public static Tablero getInstance() {
        if (instance == null) {
            instance = new Tablero(); // Crea la instancia si no existe
        }
        return instance;
    }

    /**
     * Agrega una nueva columna al tablero.
     *
     * @param columna La columna que se agregará al tablero.
     */
    public void agregarColumna(Columna columna) {
        columnas.put(columna.getEstado(), columna);
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
        t.setEstado(columna.getEstado());
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
}

