package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Barrier extends Elemento implements Serializable {

    /**
     * Constructor de las clases que hereden de alimento
     *
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho         Ancho del tablero
     * @param alto          Alto del tablero
     */
    public Barrier(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

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
