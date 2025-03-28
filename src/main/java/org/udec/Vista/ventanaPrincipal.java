package org.udec.Vista;

import org.udec.Modelo.EstadoTarea;
import org.udec.Modelo.Tarea;
import org.udec.Modelo.Tablero;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana principal que representa el tablero Kanban. Contiene tres columnas de tareas
 * ("POR HACER", "EN PROCESO", "HECHO"), y permite la creación y manipulación de tareas
 * mediante la interacción con la interfaz gráfica.
 *
 * La ventana permite arrastrar y soltar tareas entre las diferentes columnas y actualizar
 * la cantidad total de tareas. También proporciona la opción de crear nuevas tareas a través
 * de un botón.
 */
public class ventanaPrincipal extends JFrame {
    /**
     * Panel que contiene las tareas con el estado "POR HACER".
     */
    private JPanel panelPorHacer;

    /**
     * Panel que contiene las tareas con el estado "EN PROCESO".
     */
    private JPanel panelEnProceso;

    /**
     * Panel que contiene las tareas con el estado "HECHO".
     */
    private JPanel panelHecho;

    /**
     * Etiqueta que muestra el total de tareas en el tablero.
     */
    private JLabel cantidadTareas;

    /**
     * Crea la ventana principal, configura los paneles de las columnas y los elementos de la interfaz.
     */
    public ventanaPrincipal(){
        super();
        this.setTitle("Tablero Kanban");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLayout(new BorderLayout());

        // Panel central con columnas
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 10));

        // Inicialización de los paneles
        panelPorHacer = new JPanel();
        panelEnProceso = new JPanel();
        panelHecho = new JPanel();

        // Configuración de los paneles de tareas
        panelPorHacer.setLayout(new BoxLayout(panelPorHacer, BoxLayout.Y_AXIS));
        panelEnProceso.setLayout(new BoxLayout(panelEnProceso, BoxLayout.Y_AXIS));
        panelHecho.setLayout(new BoxLayout(panelHecho, BoxLayout.Y_AXIS));

        // Colores y bordes de los paneles
        panelPorHacer.setBackground(Color.GREEN);
        panelEnProceso.setBackground(Color.BLUE);
        panelHecho.setBackground(Color.RED);


        // Agregar bordes con títulos a cada columna
        panelPorHacer.setBorder(new TitledBorder("POR HACER"));
        panelEnProceso.setBorder(new TitledBorder("EN PROCESO"));
        panelHecho.setBorder(new TitledBorder("HECHO"));

        // Agregar columnas al panel central
        panelCentral.add(panelPorHacer);
        panelCentral.add(panelEnProceso);
        panelCentral.add(panelHecho);

        this.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior para botón y contador de tareas
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Contador de tareas
        cantidadTareas = new JLabel("Cantidad de tareas: " + Tablero.getInstance().contarAllTareas());
        panelInferior.add(cantidadTareas);

        // Botón para crear tarea
        JButton btnCrearTarea = new JButton("Crear Tarea");

        // Acción del botón para crear nueva tarea
        btnCrearTarea.addActionListener(e -> {
            ventanaTareas ventana = new ventanaTareas(ventanaPrincipal.this);
            ventana.setVisible(true);
        });
        panelInferior.add(btnCrearTarea);

        this.add(panelInferior, BorderLayout.SOUTH);

        this.setVisible(true);
    }


    /**
     * Agrega visualmente una tarea al panel correspondiente según su estado.
     *
     * @param tarea La tarea a agregar visualmente.
     */
    public void agregarTareaVisualmente(Tarea tarea) {
        JPanel panelDestino = obtenerPanelSegunEstado(tarea);
        JPanel panelTarea = crearPanelTarea(tarea);

        panelDestino.add(panelTarea);
        panelDestino.revalidate();
        panelDestino.repaint();
    }

    /**
     * Obtiene el panel correspondiente según el estado de la tarea.
     *
     * @param tarea La tarea cuyo estado se verifica.
     * @return El panel correspondiente al estado de la tarea.
     */
    private JPanel obtenerPanelSegunEstado(Tarea tarea) {
        return switch (tarea.getEstado()) {
            case EN_PROCESO -> panelEnProceso;
            case HECHO -> panelHecho;
            default -> panelPorHacer;
        };
    }

    private void eliminarTarea(Tarea tarea) {
        // Eliminar visualmente la tarea del panel
        JPanel panelDestino = obtenerPanelSegunEstado(tarea);
        Component[] componentes = panelDestino.getComponents();

        JPanel panelTarea = null;
        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                panelTarea = (JPanel) componente;
                // Verificar si el panel corresponde a la tarea a eliminar
                JLabel labelTitulo = (JLabel) panelTarea.getComponent(0);
                if (labelTitulo.getText().equals("Título: " + tarea.getTitulo())) {
                    panelDestino.remove(panelTarea);
                    break;
                }
            }
        }

        // Actualizar la vista
        panelDestino.revalidate();
        panelDestino.repaint();

        // Actualizar el contador de tareas
        actualizarContadorTareas();
    }

    /**
     * Actualiza el contador de tareas mostrando el número total de tareas en el tablero.
     */
    public void actualizarContadorTareas() {
        cantidadTareas.setText("Cantidad de tareas: " + Tablero.getInstance().contarAllTareas());
    }

    private void configurarAccionesTarea(JPanel panelTarea, Tarea tarea) {
        panelTarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // Click derecho
                    moverTareaADerecha(tarea);
                } else if (e.getButton() == MouseEvent.BUTTON1) { // Click izquierdo
                    moverTareaAIzquierda(tarea);
                }
            }
        });

        // Agregar KeyListener para borrar con barra espaciadora
        panelTarea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    eliminarTarea(tarea);
                }
            }
        });

        // Asegurar que el panel pueda recibir foco para el KeyListener
        panelTarea.setFocusable(true);
    }

    private void moverTareaADerecha(Tarea tarea) {
        eliminarTarea(tarea);
        EstadoTarea estadoActual = tarea.getEstado();

        switch (estadoActual) {
            case EstadoTarea.POR_HACER:
                Tablero.getInstance().moverTarea(tarea,Tablero.getInstance().getColumna(EstadoTarea.EN_PROCESO));
                break;
            case EstadoTarea.EN_PROCESO:
                Tablero.getInstance().moverTarea(tarea,Tablero.getInstance().getColumna(EstadoTarea.HECHO));
                break;
            case EstadoTarea.HECHO:
                // Si ya está en el último estado, no hacer nada
                return;
            default:
                return;
        }

        // Actualizar la vista
        actualizarVistaTarea(tarea);
    }

    private void moverTareaAIzquierda(Tarea tarea) {
        eliminarTarea(tarea);
        EstadoTarea estadoActual = tarea.getEstado();
        EstadoTarea nuevoEstado;

        switch (estadoActual) {
            case HECHO:
                nuevoEstado = EstadoTarea.EN_PROCESO;
                break;
            case EN_PROCESO:
                nuevoEstado = EstadoTarea.POR_HACER;
                break;
            case POR_HACER:
                // Si ya está en el primer estado, no hacer nada
                return;
            default:
                return;
        }

        // Cambiar el estado de la tarea
        tarea.setEstado(nuevoEstado);

        // Actualizar la vista
        actualizarVistaTarea(tarea);
    }

    // Modificar el método crearPanelTarea para incluir estas configuraciones
    private JPanel crearPanelTarea(Tarea tarea) {
        JPanel panelTarea = new JPanel();
        panelTarea.setLayout(new BoxLayout(panelTarea, BoxLayout.Y_AXIS));
        panelTarea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelTitulo = new JLabel("Título: " + tarea.getTitulo());
        JLabel labelMensaje = new JLabel("Descripción: " + tarea.getMensaje());

        panelTarea.add(labelTitulo);
        panelTarea.add(labelMensaje);

        // Configurar acciones de ratón y teclado
        configurarAccionesTarea(panelTarea, tarea);

        return panelTarea;
    }

    private void actualizarVistaTarea(Tarea tarea) {
        // Eliminar la tarea del panel actual
        JPanel panelActual = obtenerPanelSegunEstado(tarea);
        Component[] componentes = panelActual.getComponents();

        for (Component componente : componentes) {
            if (componente instanceof JPanel) {
                JPanel panelTarea = (JPanel) componente;
                // Verificar si el panel corresponde a la tarea a mover
                JLabel labelTitulo = (JLabel) panelTarea.getComponent(0);
                if (labelTitulo.getText().equals("Título: " + tarea.getTitulo())) {
                    panelActual.remove(panelTarea);
                    break;
                }
            }
        }

        // Actualizar la vista del panel actual
        panelActual.revalidate();
        panelActual.repaint();

        // Agregar la tarea al nuevo panel
        agregarTareaVisualmente(tarea);

        // Actualizar el contador de tareas
        actualizarContadorTareas();
    }
}

