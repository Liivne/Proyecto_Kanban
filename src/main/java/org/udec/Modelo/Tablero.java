package org.udec.Modelo;
import java.util.HashMap;
import java.util.Map;
// El tablero contendrá a las distintas columnas
// Utilizo patrón de diseño singleton para que exista un solo tablero que almacena los estados

public class Tablero {
    private static Tablero instance;
    private Map<EstadoTarea, Columna> columnas;

    // Constructor privado para el Singleton
    private Tablero() {
        columnas = new HashMap<>();
        // Inicializa las columnas predeterminadas
        columnas.put(EstadoTarea.POR_HACER, new Columna(EstadoTarea.POR_HACER));
        columnas.put(EstadoTarea.EN_PROCESO, new Columna(EstadoTarea.EN_PROCESO));
        columnas.put(EstadoTarea.HECHO, new Columna(EstadoTarea.HECHO));
    }

    // Método para obtener la única instancia del Singleton
    public static Tablero getInstance() {
        if (instance == null) {
            instance = new Tablero(); // Crea la instancia si no existe
        }
        return instance;
    }

    // Agrega una nueva columna al tablero
    public void agregarColumna(Columna columna) {
        columnas.put(columna.getEstado(), columna);
    }

    // Obtiene una columna por su estado
    public Columna getColumna(EstadoTarea estado) {
        return columnas.get(estado);
    }

    // Mueve una tarea a otra columna
    public void moverTarea(Tarea t, Columna columna) {
        t.setEstado(columna.getEstado());
    }

    // Cuenta el total de tareas en todas las columnas
    public int contarAllTareas() {
        int total = 0;
        for (Columna columna : columnas.values()) {
            total += columna.contarTareas();
        }
        return total;
    }

    // Resetea la instancia para pruebas
    public static void resetInstance() {
        instance = null;
    }
}
