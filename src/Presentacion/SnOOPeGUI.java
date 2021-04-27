package Presentacion;

import javax.swing.*;

public class SnOOPeGUI extends JFrame{
    public SnOOPeGUI(){
        this.add(new PanelDeJuego());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args){
        new SnOOPeGUI();
        }
}
