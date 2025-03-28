package org.udec.Vista;

import org.udec.Modelo.Tarea;

import javax.swing.*;
import java.awt.*;

/**
 * Panel personalizado que representa una tarea en la interfaz gráfica de usuario (GUI).
 * Este panel muestra el título y la descripción de la tarea, y permite que la tarea
 * sea arrastrada para ser transferida a otros elementos del sistema.
 *
 * La clase utiliza el componente `JPanel` y se configura para que tenga un diseño vertical
 * (mediante `BoxLayout`) y una frontera para distinguir visualmente la tarea.
 * También implementa un `TransferHandler` para permitir que el título de la tarea
 * sea copiado y transferido a otros componentes.
 *
 * @see Tarea
 */
public class TareaPanel extends JPanel {
    /**
     * La tarea representada por este panel.
     */
    private Tarea tarea;
    /**
     * Crea un nuevo panel que representa una tarea.
     *
     * @param tarea La tarea que será representada en este panel.
     */
    public TareaPanel(Tarea tarea) {
        this.tarea = tarea;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Establece el layout del panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Establece una frontera alrededor del panel

        // Etiquetas para mostrar el título y la descripción de la tarea
        JLabel labelTitulo = new JLabel("Título: " + tarea.getTitulo());
        JLabel labelMensaje = new JLabel("Descripción: " + tarea.getMensaje());

        add(labelTitulo);
        add(labelMensaje);

        // Hacer el panel arrastrable, permitiendo que se transfiera el título de la tarea

        }
    }




