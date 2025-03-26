package org.udec.Modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ColumnaTest {

    @BeforeEach
    void setUp() {
    }
    @Test
    void testContarTareas() {
        Columna c = new Columna(EstadoTarea.POR_HACER);

        // Caso 1: Lista vacía
        assertEquals(0, c.contarTareas(), "La lista vacía debería retornar 0");

        // Caso 2: Lista con una tarea
        c.agregarTarea(new Tarea("Mensaje", "Descripción"));
        assertEquals(1, c.contarTareas(), "La lista con una tarea debería retornar 1");

        // Caso 3: Lista con varias tareas
        c.agregarTarea(new Tarea("Mensaje 1", "Descripción 1"));
        c.agregarTarea(new Tarea("Mensaje 2", "Descripción 2"));
        c.agregarTarea(new Tarea("Mensaje 3", "Descripción 3"));
        assertEquals(4, c.contarTareas(), "La lista con cuatro tareas debería retornar 4");
    }

    @Test
    void testEliminarTarea(){

        Columna c = new Columna(EstadoTarea.POR_HACER);
        Tarea t1 = new Tarea("Mensaje 1", "Descripción 1");
        Tarea t2 = new Tarea("Mensaje 2", "Descripción 2");
        Tarea t3 = new Tarea("Mensaje 3", "Descripción 3");
        c.agregarTarea(t1);
        c.agregarTarea(t2);
        c.agregarTarea(t3);
        c.eliminarTarea(t1);
        assertEquals(2,c.contarTareas(),"La lista debería retornar 2");
    }
    void testAsignacionClase(){
        Tablero tablero = Tablero.getInstance();

        Columna p = tablero.getColumna(EstadoTarea.POR_HACER);

        Tarea t1 = new Tarea("Lavar la losa","Baja prioridad");
        Tarea t2 = new Tarea("Pasear al perro","Hoy día");

        System.out.println(p.contarTareas());}
    }

