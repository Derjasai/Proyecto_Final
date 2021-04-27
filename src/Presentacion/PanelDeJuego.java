package Presentacion;

import Aplicacion.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelDeJuego extends JPanel implements ActionListener {

    private final Tablero tablero;
    private Boolean enJuego;
    private static final int ANCHO = 1200;
    private static final int ALTO = 750;
    private static final int UNIDAD_TABLERO = 25;
    private static final int DELAY = 100;
    private Timer timer;
    private char direction = 'R';


    public PanelDeJuego(){
        this.setPreferredSize(new Dimension(ANCHO,ALTO));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                configurarMovimiento(e);
            }
        });
        tablero = new Tablero(ANCHO,ALTO,1,UNIDAD_TABLERO);
        iniciarJuego();
    }

    public void iniciarJuego(){
        enJuego = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        for (int i = 0; i < 2; i++) {
            int[] posicones = tablero.getAlimentoPosicion(i);
            g.setColor(tablero.getColorAlimento(i));
            g.fillOval(posicones[0], posicones[1],UNIDAD_TABLERO,UNIDAD_TABLERO);
        }
        Serpiente serpiente = tablero.getSerpiente();
        for(int i = 0; i< serpiente.cuerpo;i++) {
            if(i == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_TABLERO,UNIDAD_TABLERO);
            }
            else {
                g.setColor(Color.CYAN);
                g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_TABLERO,UNIDAD_TABLERO);
            }
        }
    }

    private void terminarJuego(){
        timer.stop();
        System.out.println("GG");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tablero.moveSerpiente(direction);
        tablero.serpienteComeAlimento();
        if(tablero.perderJuego()){terminarJuego();}
        repaint();
    }

    public void cerrar(){
        System.exit(0);
    }

    private void configurarMovimiento(KeyEvent e){
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if(direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if(direction != 'U') {
                    direction = 'D';
                }
                break;
        }
        tablero.moveSerpiente(direction);
        tablero.serpienteComeAlimento();
        repaint();
    }
}
