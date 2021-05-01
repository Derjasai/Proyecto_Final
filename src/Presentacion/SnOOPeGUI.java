package Presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SnOOPeGUI extends JFrame{

    private static final int ANCHO = 1200;
    private static final int ALTO = 750;
    private JButton unSoloJugador, jugadorVsJugador, jugadorVsMaquina;
    private JPanel principal;

    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    public void prepareElementos(){
        principal = new JPanel();
        this.setLayout(new BorderLayout());
        this.setTitle("Snake");
        this.setResizable(false);
        this.setSize(450,450);
        this.setLocationRelativeTo(null);

        prepareElementosEleccion();
        this.add(principal, BorderLayout.CENTER);
    }

    public void prepareAcciones(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        unSoloJugador.addActionListener(e->configuracionJuego());
    }

    private void configuracionJuego(){
        principal.setVisible(false);
        this.add(new ConfiguracionSerpiente(principal, this));
    }

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

    public static void main(String[] args){
        new SnOOPeGUI().setVisible(true);
        }
}
