package org.udec.Modelo;

import java.io.Serializable;

/**
 * Representa una tarea dentro de un sistema de gestión de tareas, como un tablero Kanban.
 *
 * Cada tarea tiene un título, un mensaje descriptivo y un estado que indica en qué fase
 * se encuentra dentro del flujo de trabajo. El estado inicial de la tarea es "POR_HACER".
 *
 * La clase proporciona métodos para obtener y modificar el estado de la tarea, así como
 * para acceder a su título y mensaje.
 */
public class Tarea implements Serializable {
    /**
     * Versión de serialización para manejar compatibilidad
     */
    private static final long serialVersionUID = 1L;

    private String mensaje;
    private String titulo;

    /**
     * El estado actual de la tarea, que indica en qué fase se encuentra.
     */
    private EstadoTarea estado;

    /**
     * Crea una nueva tarea con un título y un mensaje, estableciendo el estado inicial como "POR_HACER".
     *
     * @param mensaje El mensaje descriptivo de la tarea.
     * @param titulo El título de la tarea.
     * @throws IllegalArgumentException Si el mensaje o el título están vacíos o son nulos.
     */
    public Tarea(String mensaje, String titulo){
        if (mensaje == null || mensaje.isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }

        this.mensaje = mensaje;
        this.titulo = titulo;
        this.estado = EstadoTarea.POR_HACER;
    }

    /**
     * Establece el estado de la tarea.
     *
     * @param nuevoEstado El nuevo estado de la tarea.
     */
    public void setEstado(EstadoTarea nuevoEstado){
        this.estado = nuevoEstado;
    }

    /**
     * Obtiene el mensaje descriptivo de la tarea.
     *
     * @return El mensaje de la tarea.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Obtiene el título de la tarea.
     *
     * @return El título de la tarea.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el estado actual de la tarea.
     *
     * @return El estado de la tarea.
     */
    public EstadoTarea getEstado() {
        return estado;
    }
}
