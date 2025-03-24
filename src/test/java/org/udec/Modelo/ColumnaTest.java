package org.udec.Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColumnaTest {

    @BeforeEach
    void setUp() {
    }
    @Test
    void testContarTareas() {
        Columna miClase = new Columna();

        // Caso 1: Lista vacía
        List<Tarea> listaVacia = new ArrayList<>();
        assertEquals(0, miClase.contarTareas(listaVacia), "La lista vacía debería retornar 0");

        // Caso 2: Lista con una tarea
        List<Tarea> listaUnaTarea = new ArrayList<>();
        listaUnaTarea.add(new Tarea("Mensaje", "Descripción", EstadoTarea.POR_HACER));
        assertEquals(1, miClase.contarTareas(listaUnaTarea), "La lista con una tarea debería retornar 1");

        // Caso 3: Lista con varias tareas
        List<Tarea> listaVariasTareas = new ArrayList<>();
        listaVariasTareas.add(new Tarea("Mensaje 1", "Descripción 1", EstadoTarea.POR_HACER));
        listaVariasTareas.add(new Tarea("Mensaje 2", "Descripción 2", EstadoTarea.HECHO));
        listaVariasTareas.add(new Tarea("Mensaje 3", "Descripción 3", EstadoTarea.EN_PROGRESO));
        assertEquals(3, miClase.contarTareas(listaVariasTareas), "La lista con tres tareas debería retornar 3");
}
}