package org.udec.Modelo;

import org.udec.Vista.ventanaPrincipal;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {
        Tablero tablero = null;
        ventanaPrincipal ventana = new ventanaPrincipal();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tableroKanban.ser"))) {
            tablero = (Tablero) ois.readObject();
            System.out.println("Tablero Kanban cargado exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el tablero Kanban: " + e.getMessage());
        }

        if (tablero != null) {
            // Aquí puedes usar el tablero, por ejemplo, imprimir las tareas
            ventana.cargarTablero();

}}}
