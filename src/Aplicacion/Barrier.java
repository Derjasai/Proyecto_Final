package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Barrier extends Elemento implements Serializable {

    /**
     * Constructor de la clase Barrier
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Barrier(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    /**
     * Obtener la imagen del elemento del paquete imgs
     * @return Imagen del elemento
     */
    public Image getImage(){
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("imgs/barrier.png")); // la carga en una BufferedReade
            } catch (IOException e) {
                e.printStackTrace();
            }
            return img;
    }
}
