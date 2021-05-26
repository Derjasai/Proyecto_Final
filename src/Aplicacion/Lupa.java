package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para manejar el comportamiento de la sorpresa Lupa
 */
public class Lupa extends Sorpresas{

    /**
     * Constructor de la clase Lupa
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Lupa(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    @Override
    void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente) {
        serpienteActuar(serpientes,serpiente).tomarAlimentoSiguiente = false;
    }

    @Override
    Sorpresas conseguirSorpresaAleatoria(Tablero tablero) {
        return null;
    }

    /**
     * Obtener la imagen del paquete imgs
     * @return Retorna la imagen de la sorpresa
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/lupa.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
