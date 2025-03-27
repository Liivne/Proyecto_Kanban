package org.udec.Vista;

import org.udec.Modelo.Tarea;
import org.udec.Modelo.Tablero;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaPrincipal extends JFrame {
    // Paneles para mostrar tareas
    private JPanel panelPorHacer;
    private JPanel panelEnProceso;
    private JPanel panelHecho;
    private JLabel cantidadTareas;

    public ventanaPrincipal(){
        super();
        this.setTitle("Tablero Kanban");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLayout(new BorderLayout());

        // Panel contenedor con GridLayout para organizar las columnas
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 10));

        // Inicializar paneles para cada columna
        panelPorHacer = new JPanel();
        panelEnProceso = new JPanel();
        panelHecho = new JPanel();

        // Configurar paneles de columnas
        panelPorHacer.setLayout(new BoxLayout(panelPorHacer, BoxLayout.Y_AXIS));
        panelEnProceso.setLayout(new BoxLayout(panelEnProceso, BoxLayout.Y_AXIS));
        panelHecho.setLayout(new BoxLayout(panelHecho, BoxLayout.Y_AXIS));

        // Establecer colores y bordes
        panelPorHacer.setBackground(Color.GREEN);
        panelEnProceso.setBackground(Color.BLUE);
        panelHecho.setBackground(Color.RED);

        panelPorHacer.setBorder(new TitledBorder("POR HACER"));
        panelEnProceso.setBorder(new TitledBorder("EN PROCESO"));
        panelHecho.setBorder(new TitledBorder("HECHO"));

        // Agregar las columnas al panel central
        panelCentral.add(panelPorHacer);
        panelCentral.add(panelEnProceso);
        panelCentral.add(panelHecho);

        this.add(panelCentral, BorderLayout.CENTER);

        // Panel para el botón en la parte inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Panel para cantidad de tareas totales
        cantidadTareas = new JLabel("Cantidad de tareas: " + Tablero.getInstance().contarAllTareas());
        panelInferior.add(cantidadTareas);

        JButton btnCrearTarea = new JButton("Crear Tarea");

        // Agregar evento al botón
        btnCrearTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaTareas ventana = new ventanaTareas(ventanaPrincipal.this);
                ventana.setVisible(true);
            }
        });
        panelInferior.add(btnCrearTarea);

        this.add(panelInferior, BorderLayout.SOUTH);

        this.setVisible(true);
    }


    // Método para crear el panel de una tarea
    private JPanel crearPanelTarea(Tarea tarea) {
        JPanel panelTarea = new JPanel();
        panelTarea.setLayout(new BoxLayout(panelTarea, BoxLayout.Y_AXIS));
        panelTarea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelTitulo = new JLabel("Título: " + tarea.getTitulo());
        JLabel labelMensaje = new JLabel("Descripción: " + tarea.getMensaje());

        panelTarea.add(labelTitulo);
        panelTarea.add(labelMensaje);

        return panelTarea;
    }

    // Método para agregar visualmente una tarea al panel correcto
    public void agregarTareaVisualmente(Tarea tarea) {
        JPanel panelDestino = obtenerPanelSegunEstado(tarea);
        JPanel panelTarea = crearPanelTarea(tarea);

        panelDestino.add(panelTarea);
        panelDestino.revalidate();
        panelDestino.repaint();
    }

    // Método para obtener el panel según el estado de la tarea
    private JPanel obtenerPanelSegunEstado(Tarea tarea) {
        switch (tarea.getEstado()) {
            case POR_HACER:
                return panelPorHacer;
            case EN_PROCESO:
                return panelEnProceso;
            case HECHO:
                return panelHecho;
            default:
                return panelPorHacer;
        }
    }

    public void actualizarContadorTareas() {
        cantidadTareas.setText("Cantidad de tareas: " + Tablero.getInstance().contarAllTareas());
    }
}
