package org.udec.Vista;

import org.udec.Modelo.Tablero;
import org.udec.Modelo.Tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaTareas extends JDialog {
    private ventanaPrincipal parent;
    private JTextField tituloField;
    private JTextArea mensajeArea;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public ventanaTareas(ventanaPrincipal parent) {
        super(parent, "Añadir Tarea", true);
        this.parent = parent;
        setSize(300, 200);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(parent);

        GridBagConstraints gbc = new GridBagConstraints();

        // Campo para el título
        tituloField = new JTextField();
        tituloField.setPreferredSize(new Dimension(200, 20));
        add(new JLabel("Título:"), gbc);
        add(tituloField);

        // Campo para el mensaje
        mensajeArea = new JTextArea();
        mensajeArea.setPreferredSize(new Dimension(200, 60)); // Set preferred size
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        add(new JScrollPane(mensajeArea), gbc);

        // Panel de botones

        JPanel panelBotones = new JPanel();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        // Acción del botón Aceptar
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText().trim();
                String mensaje = mensajeArea.getText().trim();

                if (!titulo.isEmpty() && !mensaje.isEmpty()) {
                    Tarea nuevaTarea = new Tarea(mensaje, titulo);
                    Tablero.asignarColumna(nuevaTarea);
                    parent.actualizarContadorTareas();
                    parent.agregarTareaVisualmente(nuevaTarea);
                    dispose(); // Cerrar el diálogo
                } else {
                    JOptionPane.showMessageDialog(ventanaTareas.this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(e -> dispose());
    }
    public JPanel crearPanelTarea(Tarea tarea) {
        JPanel panelTarea = new JPanel();
        panelTarea.setLayout(new BoxLayout(panelTarea, BoxLayout.Y_AXIS));
        panelTarea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelTarea.add(new JLabel("Título: " + tarea.getTitulo()));
        panelTarea.add(new JLabel("Descripción: " + tarea.getMensaje()));
        panelTarea.setPreferredSize(new Dimension(200, 100));
        return panelTarea;
    }
}


