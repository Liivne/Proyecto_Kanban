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
    //dejaré el contarAllTareas para después
    void TestcontarAllTareas() {
        Tablero tablero = Tablero.getInstance();

        Columna p = tablero.getColumna(EstadoTarea.POR_HACER);
        Columna e = tablero.getColumna(EstadoTarea.EN_PROCESO);

        p.agregarTarea(new Tarea("Mensaje 1", "Descripción 1"));
        e.agregarTarea(new Tarea("Mensaje 1", "Descripción 1"));

        assertEquals(2,tablero.contarAllTareas(),"Deberían haber 2 tareas en total");
    }



    @AfterEach
    void tearDown() {
    }
}