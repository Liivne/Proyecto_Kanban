package org.udec.Vista;
import javax.swing.*;
import java.awt.*;

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

        // Agregar las columnas al panel central
        panelCentral.add(columna1);
        panelCentral.add(columna2);
        panelCentral.add(columna3);

        this.add(panelCentral,BorderLayout.CENTER);
        this.setVisible(true);

    }
}
