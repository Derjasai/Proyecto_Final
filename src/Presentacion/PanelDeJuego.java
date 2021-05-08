package Presentacion;

import Aplicacion.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelDeJuego extends JPanel implements ActionListener {

    private final Tablero tablero;
    private final int ANCHO = 1200;
    private int ALTO = 750;
    private final int UNIDAD_TABLERO = 25;
    private final int DELAY = 100;
    private Timer timer;
    private Boolean jugando;
    private char direction = 'R';

    /**
     * Constructor de la clase
     * @param coloresCabeza Lista de los colores de las cabezas, maximo dos serpientes
     * @param coloresCuerpo Lista de los colores de los cuerpos, maximo dos serpientes
     * @param nombres Lista de los nombres de las serpientes, maximo dos serpientes
     * @param alimentosAPoner Lista de los alimentos a poner en el tablero
     */
    public PanelDeJuego(Color[] coloresCabeza, Color[] coloresCuerpo, String[] nombres, String[] alimentosAPoner){
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        ALTO -= (UNIDAD_TABLERO * 2);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                configurarMovimiento(e);
            }
        });
        tablero = new Tablero(ANCHO,ALTO,UNIDAD_TABLERO,nombres,coloresCabeza,coloresCuerpo, alimentosAPoner);
        iniciarJuego();
    }

    /**
     * Inicializa el juego
     */
    public void iniciarJuego(){
        jugando = false;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    /**
     * Funcion para pintar figuras en la pantalla
     * @param g Graficos
     */
    public void paint(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Decide que clase de figuras dibujar
     * @param g Graficos
     */
    public void draw(Graphics g){
        if(jugando){
            terminarJuego(g);
        } else{
            g.setColor(Color.WHITE);
            g.fillRect(0, ALTO, ANCHO, 50);
            for (int i = 0; i < 2; i++) {
                int[] posicones = tablero.getAlimentoPosicion(i);
                g.setColor(tablero.getColorAlimento(i));
                g.fillOval(posicones[0], posicones[1],UNIDAD_TABLERO,UNIDAD_TABLERO);
            }
            Serpiente serpiente = tablero.getSerpiente();
            for(int i = 0; i< serpiente.cuerpo;i++) {
                if(i == 0) {
                    g.setColor(tablero.getColorCabeza());
                    g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_TABLERO,UNIDAD_TABLERO);
                }
                else {
                    g.setColor(tablero.getColorCuerpo());
                    g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_TABLERO,UNIDAD_TABLERO);
                }
            }
            g.setColor(Color.BLACK);
            g.setFont( new Font("Arial",Font.BOLD, 30));
            g.drawString(serpiente.nombre+": "+tablero.getPuntuacionSerpiente(), 0, g.getFont().getSize() + ALTO);
        }
    }

    /**
     * Muestra la pantalla de finalizacion
     * @param g Graficos
     */
    private void terminarJuego(Graphics g){
        timer.stop();
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Has Perdido GG", (ANCHO - metrics2.stringWidth("Game Over"))/2, ALTO/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tablero.moveSerpiente(direction);
        tablero.serpienteComeAlimento();
        jugando = tablero.perderJuego();
        repaint();
    }

    /**
     * Configura el movimiento de las serpientes en tablero de juego
     * @param e  Tipo de tecla que se oprime
     */
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
    }
}
