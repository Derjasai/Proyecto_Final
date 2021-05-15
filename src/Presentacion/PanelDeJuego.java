package Presentacion;

import Aplicacion.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelDeJuego extends JPanel implements ActionListener {

    private SnOOPeGUI ventana;
    private final int ANCHO = 1200;
    private int ALTO = 750;
    private final int UNIDAD_juego = 25;
    private int DELAY = 125;
    public Juego juego;
    private Timer timerSerpiente;
    private Timer timerAlimentos;
    private Timer timerSerpiente2;
    private Graphics g;

    /**
     * Constructor de la clase
     * @param coloresCabeza Lista de los colores de las cabezas, maximo dos serpientes
     * @param coloresCuerpo Lista de los colores de los cuerpos, maximo dos serpientes
     * @param nombres Lista de los nombres de las serpientes, maximo dos serpientes
     * @param alimentosAPoner Lista de los alimentos a poner en el juego
     */
    public PanelDeJuego(Color[] coloresCabeza, Color[] coloresCuerpo, String[] nombres, String[] alimentosAPoner, SnOOPeGUI ventana){
        inicializar(ventana);
        juego = new Juego(ANCHO,ALTO,UNIDAD_juego,nombres,coloresCabeza,coloresCuerpo, alimentosAPoner);
        juego.perdio = false; juego.enPausa = false;
        iniciarJuego();
    }

    public PanelDeJuego(Juego juego, SnOOPeGUI ventana){
        this.juego = juego;
        juego.perderJuego(); juego.enPausa = true;
        inicializar(ventana);
        iniciarJuego();
    }

    private void inicializar(SnOOPeGUI ventana){
        this.ventana = ventana;
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        ALTO -= 50;
        //this.setBackground(Color.white);
        this.setFocusable(true);
        ventana.getJMenuBar().getMenu(0).getItem(3).setEnabled(true);
        ventana.getJMenuBar().getMenu(0).getItem(3).addActionListener(e->guardar());
        ventana.getJMenuBar().getMenu(0).getItem(0).addActionListener(e->nuevo());
    }

    /**
     * Inicializa el juego
     */
    public void iniciarJuego(){
        timerSerpiente = new Timer(DELAY, this::timerSerpiente1);
        timerAlimentos = new Timer(10000,this);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                configurarMovimiento(e);
            }
        });
        timerSerpiente.start();
        timerAlimentos.start();
    }

    /**
     * Funcion para pintar figuras en la pantalla
     * @param g Graficos
     */
    public void paint(Graphics g){
        super.paintComponent(g);
        if(juego.perdio){ terminarJuego(g);}
            dibujarPuntuacion(g, juego.getSerpiente(0));
            dibujarAlimento(g);
            dibujarSerpiente(g, juego.getSerpiente(0));
            dibujarSorpresa(g);
        if(juego.enPausa && !juego.perdio){ juegoEnPausa(g);}
    }

    private void dibujarPuntuacion(Graphics g, Serpiente serpiente){
        g.setColor(Color.WHITE);
        g.fillRect(0, ALTO, ANCHO, 50);
        g.setColor(Color.BLACK);
        Font font = new Font("Arial",Font.BOLD, 30);
        g.setFont( font);
        g.drawString(serpiente.nombre+": "+juego.getPuntuacionSerpiente(0), 0, font.getSize() + ALTO);
        if(serpiente.sorpresaPendiente != null) {
            System.out.println("YAHAROOOOO");
            g.drawImage(serpiente.sorpresaPendiente(), g.getFontMetrics().stringWidth(serpiente.nombre) + 50, ALTO, 50, 50, null);
        }
    }

    /**
     * Grafica los alimentos en el tablero
     * @param g Graphics
     */
    private void dibujarAlimento(Graphics g){
        for (int i = 0; i < 2; i++) {
            int[] posicones = juego.getAlimentoPosicion(i);
            g.setColor(juego.getColorAlimento(i));
            g.fillOval(posicones[0], posicones[1],UNIDAD_juego,UNIDAD_juego);
        }
    }

    /**
     * Dibujar una serpeiente en el tablero
     * @param g Graphics
     * @param serpiente Serpiente a dibujar
     */
    private void dibujarSerpiente(Graphics g, Serpiente serpiente){
        for(int i = 0; i< serpiente.cuerpo;i++) {
            if(i == 0) {
                g.setColor(juego.getColorCabeza(0));
                g.setColor(juego.getColorCabeza(1));
            }
            else {
                g.setColor(juego.getColorCuerpo(0));
                g.setColor(juego.getColorCuerpo(1));
            }
            g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_juego,UNIDAD_juego);
        }
    }

    private void dibujarSorpresa(Graphics g){
        int[] posicion = juego.getSorpresaPos();
        g.drawImage(juego.getImageSorpresa(),posicion[0], posicion[1], UNIDAD_juego, UNIDAD_juego, null);
    }

    private void juegoEnPausa(Graphics g){
        timerSerpiente.stop();
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("EN PAUSA", (ANCHO - metrics2.stringWidth("Game Over"))/2, ALTO/2);
    }

    /**
     * Muestra la pantalla de finalizacion
     * @param g Graficos
     */
    private void terminarJuego(Graphics g){
        timerSerpiente.stop();
//        g.setColor(Color.WHITE);
//        g.setFont( new Font("Ink Free",Font.BOLD, 75));
//        FontMetrics metrics2 = getFontMetrics(g.getFont());
//        g.drawString("Has Perdido GG", (ANCHO - metrics2.stringWidth("Game Over"))/2, ALTO/2);
    }

    public void timerSerpiente1(ActionEvent e) {
        juego.moveSerpiente(0);
        juego.serpienteComeAlimento(0);
        juego.perderJuego();
        juego.serpienteTomaSorpresa();
        repaint();
    }

    public void timerSerpiente2(ActionEvent e){
        juego.moveSerpiente(1);
        juego.serpienteComeAlimento(1);
        juego.perderJuego();
        repaint();
    }

    /**
     * Configura el movimiento de las serpientes en juego de juego
     * @param e  Tipo de tecla que se oprime
     */
    private void configurarMovimiento(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(juego.enPausa){timerSerpiente.restart();}
            juego.enPausa = !juego.enPausa;
        }
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(juego.getDirection(0) != 'R') {
                    juego.getSerpiente(0).setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(juego.getDirection(0) != 'L') {
                    juego.getSerpiente(0).setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if(juego.getDirection(0) != 'D') {
                    juego.getSerpiente(0).setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if(juego.getDirection(0) != 'U') {
                    juego.getSerpiente(0).setDirection('D');
                }
                break;
            case KeyEvent.VK_A:
                if(juego.getDirection(1) != 'R') {
                    juego.getSerpiente(1).setDirection('L');
                }
                break;
            case KeyEvent.VK_D:
                if(juego.getDirection(1) != 'L') {
                    juego.getSerpiente(1).setDirection('R');
                }
                break;
            case KeyEvent.VK_W:
                if(juego.getDirection(1) != 'D') {
                    juego.getSerpiente(1).setDirection('U');
                }
                break;
            case KeyEvent.VK_S:
                if(juego.getDirection(1) != 'U') {
                    juego.getSerpiente(1).setDirection('D');
                }
                break;
            case KeyEvent.VK_X:
                juego.serpienteLanzaPoder(0);

        }
    }

    private void guardar(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Clases de Java (.dat)", "dat");
            fileChooser.setDialogTitle("Guardar");
            fileChooser.setFileFilter(filter);
            int seleccion = fileChooser.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                juego.save(fileChooser.getSelectedFile());
            }
        } catch (JuegoExcepcion e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void nuevo(){
        this.setVisible(false);
        ventana.getJMenuBar().getMenu(0).getItem(3).setEnabled(false);
        ventana.principal.setVisible(true);
        ventana.add(ventana.principal);
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        juego.cambiarPosAlimentos();
        repaint();
    }
}
