package Presentacion;

import Aplicacion.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase encargada de graficar el tablero con las serpientes,alimentos y sorpresas
 */
public class PanelDeJuego extends JPanel implements ActionListener {

    private SnOOPeGUI ventana;
    private final int ANCHO = 1200;
    private int ALTO = 770;
    private final int UNIDAD_juego = 30;
    private int DELAY = 175;
    public Juego juego;
    private Timer timerSerpiente;
    private Timer timerTablero;
    private Timer timerSerpiente2;

    /**
     * Constructor principal de la clase
     * @param coloresCabeza Colores de la cabeza de las o de la serpiente
     * @param coloresCuerpo Colores del cuerpo de las o de la serpiente
     * @param nombres Nombres de los jugadores o el jugador
     * @param alimentosAPoner Lista con los alimentos a jugar
     * @param sorpresasAPoner Lista con las sorpresas a jugar
     * @param ventana JFrame donde se encuentra el panel
     */
    public PanelDeJuego(Color[] coloresCabeza, Color[] coloresCuerpo, String[] nombres, String[] alimentosAPoner,String[] sorpresasAPoner ,SnOOPeGUI ventana){
        inicializar(ventana);
        juego = new Juego(ANCHO,ALTO,UNIDAD_juego,nombres,coloresCabeza,coloresCuerpo, alimentosAPoner, sorpresasAPoner);
        juego.perdio = false; juego.enPausa = false;
        iniciarJuego();
    }

    /**
     * Constructor secundario para cuando se tiene que cargar un archivo
     * @param juego El juego que se carga
     * @param ventana JFrame donde se encuentra el panel
     */
    public PanelDeJuego(Juego juego, SnOOPeGUI ventana){
        this.juego = juego;
        juego.perderJuego(); juego.enPausa = true;
        inicializar(ventana);
        iniciarJuego();
    }

    /**
     * Inicializa el JPnael y activa la opcion de guardar una partda
     * @param ventana JFrame donde se encuentra el panel
     */
    private void inicializar(SnOOPeGUI ventana){
        this.ventana = ventana;
        this.setPreferredSize(new Dimension(ANCHO, ALTO));
        ALTO -= 50;
        this.setBackground(Color.BLACK);
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
        timerSerpiente2 = new Timer(DELAY, this::timerSerpiente2);
        timerTablero = new Timer(10000,this);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                configurarMovimiento(e);
            }
        });
          timerTablero.start();
        if(juego.multiplayer){timerSerpiente2.start();}
        timerSerpiente.start();

    }

    /**
     * Funcion para pintar figuras en la pantalla
     * @param g Graficos
     */
    public void paint(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, ALTO, ANCHO, 50);
        if(juego.perdio){ terminarJuego(g);}
            dibujarPuntuacion(g, juego.getSerpiente(0),0);
            dibujarSerpiente(g, juego.getSerpiente(0));
            if(juego.multiplayer){dibujarPuntuacion(g, juego.getSerpiente(1),800);}
        if(juego.multiplayer){dibujarSerpiente(g, juego.getSerpiente(1));}
            dibujarElementos(g);
        if(juego.enPausa && !juego.perdio){ juegoEnPausa(g);}
    }

    /**
     * Dibuja la puntuacion de la serpiente
     * @param g Grhapics para dibujar
     * @param serpiente Serpiente de donde se toma el puntaje
     * @param x Distancia x en pixeles donde poner el nombre
     */
    private void dibujarPuntuacion(Graphics g, Serpiente serpiente, int x){

        g.setColor(Color.BLACK);
        Font font = new Font("Arial",Font.BOLD, 30);
        g.setFont(font);
        g.drawString(serpiente.nombre+": "+serpiente.getPuntuacion(), x, font.getSize() + ALTO);
        if(serpiente.sorpresaPendiente != null) {
            g.drawImage(serpiente.sorpresaPendiente(), x+g.getFontMetrics().stringWidth(serpiente.nombre+": "+serpiente.getPuntuacion())+10, ALTO, 50, 50, null);
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
                g.setColor(serpiente.colorCabeza);
            }
            else {
                g.setColor(serpiente.colorCuerpo);
            }
            if(!serpiente.muerta){g.fillRect(serpiente.poscionX[i], serpiente.poscionY[i], UNIDAD_juego,UNIDAD_juego);}
        }
    }

    /**
     * Dibujar elementos del mapa, comos sopresas, alimentos, bloques trampa
     * @param g Graphics para dibujar
     */
    private void dibujarElementos(Graphics g){
        ArrayList<Elemento> elementos = juego.getElementos();
        for(Elemento elemento: elementos){
            g.drawImage(elemento.getImage(), elemento.x, elemento.y, UNIDAD_juego, UNIDAD_juego, null);
        }
    }

    /**
     * Dibujar el cartel de pausa
     * @param g Graphics para dibujar
     */
    private void juegoEnPausa(Graphics g){
        timerSerpiente.stop();
        timerTablero.stop();
        if (juego.multiplayer){timerSerpiente2.stop();}
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("EN PAUSA", (ANCHO - metrics2.stringWidth("Game Over"))/2, ALTO/2);
    }

    /**
     * Muestra la pantalla de finalizacion
     * @param g Graphics para dibujar
     */
    private void terminarJuego(Graphics g){
        timerSerpiente.stop();
        timerTablero.stop();
        if (juego.multiplayer){timerSerpiente2.stop();}
        g.setColor(Color.WHITE);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Has Perdido GG", (ANCHO - metrics2.stringWidth("Game Over"))/2, ALTO/2);
    }

    /**
     * Procesos a repetir consecutivamente de la serpiente 1
     * @param e Evento
     */
    public void timerSerpiente1(ActionEvent e) {
        juego.moveSerpiente(0);
        juego.serpienteComeAlimento();
        juego.perderJuego();
        juego.serpienteTomaSorpresa();
        repaint();
    }

    /**
     * Procesos a repetir de la serpiente 2 en caso de que exista
     * @param e Evento
     */
    public void timerSerpiente2(ActionEvent e){
        juego.moveSerpiente(1);
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
            if(juego.enPausa){
                timerSerpiente.restart();
                timerTablero.restart();
                if (juego.multiplayer){timerSerpiente2.restart();}
            }
            juego.enPausa = !juego.enPausa;
        }
        switch(e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(juego.getSerpiente(0).direction != 'R') {
                    juego.getSerpiente(0).direction =('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(juego.getSerpiente(0).direction != 'L') {
                    juego.getSerpiente(0).direction =('R');
                }
                break;
            case KeyEvent.VK_UP:
                if(juego.getSerpiente(0).direction != 'D') {
                    juego.getSerpiente(0).direction =('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if(juego.getSerpiente(0).direction != 'U') {
                    juego.getSerpiente(0).direction =('D');
                }
                break;
            case KeyEvent.VK_M:
                juego.serpienteLanzaPoder(0);
        }
        if (juego.multiplayer){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_A:
                    if(juego.getSerpiente(1).direction != 'R') {
                        juego.getSerpiente(1).direction =('L');
                    }
                    break;
                case KeyEvent.VK_D:
                    if(juego.getSerpiente(1).direction != 'L') {
                        juego.getSerpiente(1).direction =('R');
                    }
                    break;
                case KeyEvent.VK_W:
                    if(juego.getSerpiente(1).direction != 'D') {
                        juego.getSerpiente(1).direction =('U');
                    }
                    break;
                case KeyEvent.VK_S:
                    if(juego.getSerpiente(1).direction != 'U') {
                        juego.getSerpiente(1).direction =('D');
                    }
                    break;
                case KeyEvent.VK_X:
                    juego.serpienteLanzaPoder(1);
            }
        }
    }

    /**
     * Metodo para guardar el tablero actual
     */
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

    /**
     * Abre un nuevo menu para iniciar un nuevo juego
     */
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
