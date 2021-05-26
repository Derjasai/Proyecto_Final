package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para la sorpresa de aumento de velocidad
 */
public class Aumento extends Sorpresas {

    /**
     * Constructor de la clase Aumento
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Aumento(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    @Override
    void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente) {
        serpiente.setDelay(50);
    }

    @Override
    Sorpresas conseguirSorpresaAleatoria(Tablero tablero){
        return null;
    }


    /**
     * Retorna la imagen de la sorpresa guardada en la carpeta imgs
     * @return Imagen de la sorpresa
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/aumento.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
