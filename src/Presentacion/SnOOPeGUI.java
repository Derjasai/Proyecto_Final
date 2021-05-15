package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

import Aplicacion.Juego;
import Persistencia.*;

public class SnOOPeGUI extends JFrame{

    private JButton unSoloJugador, jugadorVsJugador, jugadorVsMaquina;
    public JPanel principal, panelJuego, configuraciones;
    public JMenuBar menuBar;
    public JMenu archivo;
    private JMenuItem nuevo,abrir,salir;
    public JMenuItem guardarComo;

    /**
     *Construsctor de la clase SnOOPeGUI
     */
    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepara los elementos en general
     */
    public void prepareElementos(){
        principal = new JPanel();
        configuraciones = new ConfiguracionJuego(this);
        this.setLayout(new BorderLayout());
        this.setTitle("Snake");
        this.setResizable(false);
        this.setSize(new Dimension(1225,800));
        this.setPreferredSize(new Dimension(1225,800));
        this.setLocationRelativeTo(null);

        prepareElementosEleccion();
        prepareElementosMenu();

        this.add(principal, BorderLayout.CENTER);
    }

    /**
     * Prepara las acciones de los elementos
     */
    public void prepareAcciones(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        unSoloJugador.addActionListener(e->configuracionJuego());
        nuevo.addActionListener(e->opcionNuevo());
        abrir.addActionListener(e->opcionAbrir());
        guardarComo.setEnabled(false);
        salir.addActionListener(e -> opcionSalir());
    }

    /**
     * Implementa la opcion de abrir
     */
    private void opcionAbrir(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Clases de Java (.dat)", "dat");
            fileChooser.setDialogTitle("Abrir");
            fileChooser.setFileFilter(filter);
            int seleccion = fileChooser.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                Juego juego = Juego.abra(fileChooser.getSelectedFile());
                principal.setVisible(false);
                if(panelJuego != null){panelJuego.setVisible(false);}
                panelJuego = new PanelDeJuego(juego,this);
                panelJuego.setVisible(true);
                this.add(panelJuego);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


    /**
     * Limpia y genera un nuevo automata
     */
    private void opcionNuevo(){
    }

    /**
     * Implementa la opcion de salir
     */
    private void opcionSalir(){
        System.exit(0);
    }

    /**
     * Muestra la ventana de configuracion del juego
     */
    private void configuracionJuego(){
        principal.setVisible(false);
        configuraciones.setVisible(true);
        this.add(configuraciones);
        validate();
    }

    /**
     * Prepara los elementos de eleccion de modo de juego
     */
    public void prepareElementosEleccion(){
        principal.setLayout(new BoxLayout(principal,BoxLayout.X_AXIS));
        principal.setBorder(new LineBorder(Color.BLACK,3));
        JPanel selecciones = new JPanel();
        unSoloJugador = new JButton("Un Solo Jugador");
        jugadorVsJugador = new JButton("Jugador vs Jugador");
        jugadorVsMaquina = new JButton("Jugador vs IA");
        selecciones.setLayout(new GridLayout(3,1,100,100));

        selecciones.add(unSoloJugador);
        selecciones.add(jugadorVsJugador);
        selecciones.add(jugadorVsMaquina);
        principal.add(Box.createHorizontalGlue());
        principal.add(selecciones);
        principal.add(Box.createHorizontalGlue());
    }

    /**
     * Prepara los elementos del menu
     */
    private void prepareElementosMenu(){
        menuBar = new JMenuBar();
        archivo = new JMenu("archivo");

        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardarComo = new JMenuItem("Guardar Como");
        salir = new JMenuItem("Salir");

        archivo.add(nuevo);
        archivo.addSeparator();
        archivo.add(abrir);
        archivo.add(guardarComo);
        archivo.addSeparator();
        archivo.add(salir);

        menuBar.add(archivo);
        this.setJMenuBar(menuBar);
    }

    public static void main(String[] args){
        new SnOOPeGUI().setVisible(true);
        }
}
