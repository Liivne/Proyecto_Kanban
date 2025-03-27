package org.udec.Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

    @BeforeEach
    void setUp() {
        Tablero.resetInstance();

    }


    @Test
    void testAgregarTarea(){
        Tablero tablero = Tablero.getInstance();

        Columna p = tablero.getColumna(EstadoTarea.POR_HACER);
        Columna e = tablero.getColumna(EstadoTarea.EN_PROCESO);

        Tarea t1 = new Tarea("Lavar la losa","Baja prioridad");
        Tarea t2 = new Tarea("Pasear al perro","Hoy día");

        p.agregarTarea(t1);
        e.agregarTarea(t2);

        assertEquals(2,tablero.contarAllTareas());
    }
    @Test
    public void testAsignarColumna() {
        // Crear una tarea
        String titulo = "Test Tarea";
        String mensaje = "Descripción de la tarea";
        Tarea tarea = new Tarea(mensaje, titulo);

        // Asignar la tarea a la columna POR_HACER
        Tablero.asignarColumna(tarea);

        // Verificar que la tarea fue añadida a la columna correcta (POR_HACER)
        Columna columnaPorHacer = Tablero.getInstance().getColumna(EstadoTarea.POR_HACER);

        // Verificar que la tarea está en la columna correcta
        assertTrue(columnaPorHacer.contarTareas() == 1, "La tarea no fue asignada correctamente a POR_HACER");

        // Verificar que la tarea asignada tiene el mismo título
        Tarea tareaAsignada = columnaPorHacer.getTareas().getFirst();
        assertEquals("Test Tarea", titulo, tareaAsignada.getTitulo());
        assertEquals("Descripción de la tarea", mensaje, tareaAsignada.getMensaje());
    }



    @AfterEach
    void tearDown() {
    }
}