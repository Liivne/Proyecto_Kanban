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
import java.io.File;

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
    private final JPanel panelEnProceso;

    /**
     * Panel que contiene las tareas con el estado "HECHO".
     */
    private final JPanel panelHecho;

    /**
     * Etiqueta que muestra el total de tareas en el tablero.
     */
    private final JLabel cantidadTareas;

    /**
     * Crea la ventana principal, configura los paneles de las columnas y los elementos de la interfaz.
     */
    public ventanaPrincipal(){
        super();
        this.setTitle("Tablero Kanban");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLayout(new BorderLayout());

        // Crear serialización
        crearMenuSerializacion();

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
     * Crea el menú de serialización para guardar y cargar tableros
     */
    private void crearMenuSerializacion() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");

        // Ítem para guardar tablero
        JMenuItem itemGuardar = new JMenuItem("Guardar Tablero");
        itemGuardar.addActionListener(e -> guardarTablero());

        // Ítem para cargar tablero
        JMenuItem itemCargar = new JMenuItem("Cargar Tablero");
        itemCargar.addActionListener(e -> cargarTablero());

        menuArchivo.add(itemGuardar);
        menuArchivo.add(itemCargar);

        menuBar.add(menuArchivo);
        this.setJMenuBar(menuBar);
    }

    /**
     * Guarda el tablero actual en un archivo
     */
    private void guardarTablero() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Tablero Kanban");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Asegurar extensión .kanban
            String rutaArchivo = fileToSave.getAbsolutePath();
            if (!rutaArchivo.toLowerCase().endsWith(".kanban")) {
                rutaArchivo += ".kanban";
            }

            // Guardar tablero
            Tablero.getInstance().guardarTablero(rutaArchivo);

            JOptionPane.showMessageDialog(this,
                    "Tablero guardado exitosamente",
                    "Guardar Tablero",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Carga un tablero desde un archivo
     */
    public void cargarTablero() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Cargar Tablero Kanban");

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();

            // Limpiar paneles actuales
            limpiarPaneles();

            // Cargar tablero
            Tablero tableroNuevo = Tablero.cargarTablero(fileToLoad.getAbsolutePath());

            if (tableroNuevo != null) {
                // Repoblar paneles con las tareas cargadas
                cargarTareasExistentes();

                JOptionPane.showMessageDialog(this,
                        "Tablero cargado exitosamente",
                        "Cargar Tablero",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo cargar el tablero",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Limpia todos los paneles de tareas
     */
    private void limpiarPaneles() {
        panelPorHacer.removeAll();
        panelEnProceso.removeAll();
        panelHecho.removeAll();

        panelPorHacer.revalidate();
        panelEnProceso.revalidate();
        panelHecho.revalidate();

        panelPorHacer.repaint();
        panelEnProceso.repaint();
        panelHecho.repaint();
    }

    /**
     * Carga las tareas existentes en los paneles correspondientes
     */
    private void cargarTareasExistentes() {
        // Cargar tareas de cada columna
        Tablero.getInstance().getColumna(EstadoTarea.POR_HACER)
                .getTareas().forEach(this::agregarTareaVisualmente);

        Tablero.getInstance().getColumna(EstadoTarea.EN_PROCESO)
                .getTareas().forEach(this::agregarTareaVisualmente);

        Tablero.getInstance().getColumna(EstadoTarea.HECHO)
                .getTareas().forEach(this::agregarTareaVisualmente);

        // Actualizar contador de tareas
        actualizarContadorTareas();
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
        System.out.println("Método eliminarTarea llamado para: " + tarea.getTitulo());

        // Eliminar visualmente la tarea del panel
        JPanel panelDestino = obtenerPanelSegunEstado(tarea);
        Component[] componentes = panelDestino.getComponents();

        JPanel panelTarea;
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
        // Agregar foco al panel para que pueda recibir eventos de teclado
        panelTarea.setFocusable(true);

        // Listener del mouse para aladir tareas
        panelTarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Agregar mensaje de depuración
                System.out.println("Panel de tarea clickeado: " + tarea.getTitulo());

                // Solicitar foco para el panel
                panelTarea.requestFocusInWindow();

                if (e.getButton() == MouseEvent.BUTTON3) { // Click derecho
                    moverTareaADerecha(tarea);
                } else if (e.getButton() == MouseEvent.BUTTON1) { // Click izquierdo
                    moverTareaAIzquierda(tarea);
                }
            }
        });

        // KeyListener para borrar con barra espaciadora
        panelTarea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Agregar mensaje de depuración
                System.out.println("Tecla presionada: " + e.getKeyCode() + " en tarea: " + tarea.getTitulo());
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    // Mostrar diálogo de confirmación
                    int respuesta = JOptionPane.showConfirmDialog(
                            ventanaPrincipal.this,
                            "¿Estás seguro de que quieres eliminar esta tarea?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    System.out.println("Respuesta de confirmación: " + respuesta);

                    if(respuesta == JOptionPane.YES_OPTION){
                        Tablero.getInstance().getColumna(tarea.getEstado()).eliminarTarea(tarea);
                        eliminarTarea(tarea);
                    }

                }
            }
        });
    }

    private void moverTareaADerecha(Tarea tarea) {
        EstadoTarea estadoActual = tarea.getEstado();

        switch (estadoActual) {
            case EstadoTarea.POR_HACER:
                eliminarTarea(tarea);
                Tablero.getInstance().moverTarea(tarea,Tablero.getInstance().getColumna(EstadoTarea.EN_PROCESO));
                break;
            case EstadoTarea.EN_PROCESO:
                eliminarTarea(tarea);
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
        EstadoTarea estadoActual = tarea.getEstado();
        EstadoTarea nuevoEstado;

        switch (estadoActual) {
            case HECHO:
                eliminarTarea(tarea);
                nuevoEstado = EstadoTarea.EN_PROCESO;
                break;
            case EN_PROCESO:
                eliminarTarea(tarea);
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