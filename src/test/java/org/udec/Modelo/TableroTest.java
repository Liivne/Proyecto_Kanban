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
    void testAsignacionClase(){
        Tablero tablero = Tablero.getInstance();

        Columna p = tablero.getColumna(EstadoTarea.POR_HACER);
        Columna e = tablero.getColumna(EstadoTarea.EN_PROCESO);

        Tarea t1 = new Tarea("Lavar la losa","Baja prioridad");
        Tarea t2 = new Tarea("Pasear al perro","Hoy día");

        p.agregarTarea(t1);
        e.agregarTarea(t2);

        assertEquals(2,tablero.contarAllTareas());
    }



    @AfterEach
    void tearDown() {
    }
}