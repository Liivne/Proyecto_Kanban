package org.udec.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Columna implements Serializable {
    /**
     * Versión de serialización para manejar compatibilidad
     */
    private static final long serialVersionUID = 1L;
    /**
     * El estado de las tareas dentro de esta columna.
     */
    private EstadoTarea estado;

    /**
     * Lista de tareas asociadas a esta columna.
     */
    private List<Tarea> tareas;

    /**
     * Crea una nueva columna con un estado específico.
     *
     * @param estado El estado que representará esta columna.
     */
    public Columna(EstadoTarea estado){
        this.estado = estado;
        this.tareas = new ArrayList<>();
    }

    /**
     * Obtiene el estado de la columna.
     *
     * @return El estado de la columna.
     */
    public EstadoTarea getEstado() {
        return estado;
    }

    /**
     * Elimina una tarea de la lista de tareas de la columna.
     *
     * @param t La tarea que se eliminará de la columna.
     */
    public void eliminarTarea(Tarea t){
        tareas.remove(t);
    }

    /**
     * Agrega una nueva tarea a la columna.
     *
     * @param t La tarea que se agregará a la columna.
     */
    public void agregarTarea(Tarea t){
        tareas.add(t);
    }

    /**
     * Cuenta el número total de tareas en la columna.
     *
     * @return El número total de tareas en la columna.
     */
    public int contarTareas(){
        int total = 0;
        for(Tarea tarea: tareas){
            total++;
        }
        return total;
    }

    /**
     * Obtiene la lista de tareas en la columna.
     *
     * @return La lista de tareas en la columna.
     */
    public List<Tarea> getTareas(){
        return tareas;
    }
}

