package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SnOOPeGUI extends JFrame{

    private JButton unSoloJugador, jugadorVsJugador, jugadorVsMaquina;
    private JPanel principal;
    private JMenuBar menuBar;
    private JMenu archivo;
    private JMenuItem nuevo,abrir,guardarComo,importar,exportar,salir;

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
        this.setLayout(new BorderLayout());
        this.setTitle("Snake");
        this.setResizable(false);
        this.setSize(new Dimension(1200,800));
        this.setPreferredSize(new Dimension(1200,800));
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
    }

    /**
     * Muestra la ventana de configuracion del juego
     */
    private void configuracionJuego(){
        principal.setVisible(false);
        this.add(new ConfiguracionJuego(this));
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
        importar = new JMenuItem("Importar");
        exportar = new JMenuItem("Exportar");
        salir = new JMenuItem("Salir");

        archivo.add(nuevo);
        archivo.addSeparator();
        archivo.add(abrir);
        archivo.add(guardarComo);
        archivo.addSeparator();
        archivo.add(importar);
        archivo.add(exportar);
        archivo.addSeparator();
        archivo.add(salir);
        menuBar.add(archivo);
        this.setJMenuBar(menuBar);
    }

    public static void main(String[] args){
        new SnOOPeGUI().setVisible(true);
        }
}
