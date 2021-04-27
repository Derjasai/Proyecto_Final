package Presentacion;

import javax.swing.*;
import java.awt.*;

public class SnOOPeGUI extends JFrame{

    private static final int ANCHO = 1200;
    private static final int ALTO = 750;
    private JButton unSoloJugador, jugadorVsJugador, jugadorVsMaquina;

    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }

    public void prepareElementos(){
        this.add(new PanelDeJuego());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public void prepareAcciones(){

    }

    public static void main(String[] args){
        new SnOOPeGUI();
        }
}
