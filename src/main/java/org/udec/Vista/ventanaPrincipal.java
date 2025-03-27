package org.udec.Vista;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaPrincipal extends JFrame {
    public ventanaPrincipal(){
        super();
        this.setTitle("Tablero Kanban");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLayout(new BorderLayout());

        // Panel contenedor con GridLayout para organizar las columnas
        //agregar componentes del layout (3 columnas y 1 un botón)

        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 fila, 3 columnas, con espaciado
        JPanel columna1 = new JPanel();
        JPanel columna2 = new JPanel();
        JPanel columna3 = new JPanel();

        // Establecer colores para diferenciarlas
        columna1.setBackground(Color.RED);
        columna2.setBackground(Color.GREEN);
        columna3.setBackground(Color.BLUE);

        // Establecer títulos de la columna
        columna1.setBorder(new TitledBorder("POR HACER"));
        columna2.setBorder(new TitledBorder("EN PROCESO"));
        columna3.setBorder(new TitledBorder("HECHO"));

        // Agregar las columnas al panel central
        panelCentral.add(columna1);
        panelCentral.add(columna2);
        panelCentral.add(columna3);

        this.add(panelCentral,BorderLayout.CENTER);

        // Panel para el botón en la parte inferior
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
}
