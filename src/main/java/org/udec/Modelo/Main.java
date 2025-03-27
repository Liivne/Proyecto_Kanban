package org.udec.Modelo;

import org.udec.Vista.ventanaPrincipal;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = Tablero.getInstance();

        Tarea t1 = new Tarea("Lavar la losa","Baja prioridad");
        Tablero.asignarColumna(t1);

        Tarea t2 = new Tarea("Pasear al perro","Hoy día");
        Tablero.asignarColumna(t2);

        ventanaPrincipal ventana = new ventanaPrincipal();

    }
}