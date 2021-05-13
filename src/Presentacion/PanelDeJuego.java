package Presentacion;

import Aplicacion.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class PanelDeJuego extends JPanel implements ActionListener {

    private JFrame ventana;
    private final int ANCHO = 1200;
    private int ALTO = 750;
    private final int UNIDAD_juego = 75;
    private final int DELAY = 150;
    public Juego juego;
    private char direction = 'R';
    private Timer timer;

    /**
     * Constructor de la clase
     * @param coloresCabeza Lista de los colores de las cabezas, maximo dos serpientes
     * @param coloresCuerpo Lista de los colores de los cuerpos, maximo dos serpientes
     * @param nombres Lista de los nombres de las serpientes, maximo dos serpientes
     * @param alimentosAPoner Lista de los alimentos a poner en el juego
     */
    public PanelDeJuego(Color[] coloresCabeza, Color[] coloresCuerpo, String[] nombres, String[] alimentosAPoner, JFrame ventana){
        this.ventana = ventana;
        ventana.getJMenuBar().getMenu(0).getItem(3).setEnabled(true);
        ventana.getJMenuBar().getMenu(0).getItem(3).addActionListener(e->guardar());
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        ALTO -= UNIDAD_juego;
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                configurarMovimiento(e);
            }
        });
        juego = new Juego(ANCHO,ALTO,UNIDAD_juego,nombres,coloresCabeza,coloresCuerpo, alimentosAPoner);
        juego.perdio = false; juego.enPausa = false;
        iniciarJuego();
    }

    public PanelDeJuego(Juego juego){
        this.juego = juego;
        juego.perderJuego(); juego.enPausa = true;
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        ALTO -= UNIDAD_juego;
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                configurarMovimiento(e);
            }
        });
        this.setBackground(Color.black);
        this.setFocusable(true);
        iniciarJuego();
    }

    /**
     * Inicializa el juego
     */
    public void iniciarJuego(){
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
        if(juego.perdio){ terminarJuego(g);}
            g.setColor(Color.WHITE);
            g.fillRect(0, ALTO, ANCHO, UNIDAD_juego);
            for (int i = 0; i < 2; i++) {
                int[] posicones = juego.getAlimentoPosicion(i);
                g.setColor(juego.getColorAlimento(i));
                g.fillOval(posicones[0], posicones[1],UNIDAD_juego,UNIDAD_juego);
            }
            Serpiente serpiente = juego.getSerpiente();
            for(int i = 0; i< serpiente.cuerpo;i++) {
                if(i == 0) {
                    g.setColor(juego.getColorCabeza());
                }
                else {
                    g.setColor(juego.getColorCuerpo());
                }
                g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_juego,UNIDAD_juego);
            }
            g.setColor(Color.BLACK);
            g.setFont( new Font("Arial",Font.BOLD, 30));
            g.drawString(serpiente.nombre+": "+juego.getPuntuacionSerpiente(), 0, g.getFont().getSize() + ALTO);
            g.setColor(Color.white);
        if(juego.enPausa){ juegoEnPausa(g); }
    }

    private void juegoEnPausa(Graphics g){
        timer.stop();
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
        timer.stop();
//        g.setColor(Color.WHITE);
//        g.setFont( new Font("Ink Free",Font.BOLD, 75));
//        FontMetrics metrics2 = getFontMetrics(g.getFont());
//        g.drawString("Has Perdido GG", (ANCHO - metrics2.stringWidth("Game Over"))/2, ALTO/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        juego.moveSerpiente(direction);
        juego.serpienteComeAlimento();
        juego.perderJuego();
        repaint();
    }

    /**
     * Configura el movimiento de las serpientes en juego de juego
     * @param e  Tipo de tecla que se oprime
     */
    private void configurarMovimiento(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(juego.enPausa){timer.restart();}
            juego.enPausa = !juego.enPausa;
        }
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
        juego.moveSerpiente(direction);
        juego.serpienteComeAlimento();
        juego.perderJuego();
        repaint();
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
}
