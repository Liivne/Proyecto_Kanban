package org.udec.Modelo;

import java.io.Serializable;

/**
 * Enum que representa los diferentes estados de una tarea dentro de un tablero Kanban.
 *
 * Los estados definidos son:
 * <ul>
 *     <li><b>POR_HACER:</b> La tarea aún no ha comenzado.</li>
 *     <li><b>EN_PROCESO:</b> La tarea está en progreso.</li>
 *     <li><b>HECHO:</b> La tarea ha sido completada.</li>
 * </ul>
 *
 * Este enum es utilizado para asignar y gestionar el estado de las tareas en el tablero Kanban.
 *
 */
public enum EstadoTarea implements Serializable {
    /**
     * Estado que indica que la tarea aún no ha comenzado.
     */
    POR_HACER,

    /**
     * Estado que indica que la tarea está en progreso.
     */
    EN_PROCESO,

    /**
     * Estado que indica que la tarea ha sido completada.
     */
    HECHO,
}
