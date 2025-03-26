package org.udec.Modelo;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = Tablero.getInstance();

        Columna p = tablero.getColumna(EstadoTarea.POR_HACER);
        Columna e = tablero.getColumna(EstadoTarea.EN_PROCESO);
        Columna f = tablero.getColumna(EstadoTarea.HECHO);

        Tarea t1 = new Tarea("Lavar la losa","Baja prioridad");
        Tablero.asignarColumna(t1);
        Tarea t2 = new Tarea("Pasear al perro","Hoy día");
        Tablero.asignarColumna(t2);

        System.out.println(p.contarTareas());

    }
}