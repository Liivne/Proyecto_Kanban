package org.udec.Modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TareaTest {
    Tarea tarea;
    String a,b;
    EstadoTarea c;

    @BeforeEach
    void setUp() {
        a = "";
        b = "Comprar";
    }
    @Test

    void TestConstructor(){
        assertThrows(IllegalArgumentException.class, () -> {
            tarea = new Tarea(a, b);
        });
    }


    @AfterEach
    void tearDown() {
    }


}