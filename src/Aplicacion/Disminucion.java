package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clas eencargada de manejar la sorpresa Disminucion de velocidad
 */
public class Disminucion extends Sorpresas {

    /**
     * Constructor de la clase Aumento
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Disminucion(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    @Override
    void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente) {
        System.out.println(serpienteActuar(serpientes,serpiente).nombre);
        serpienteActuar(serpientes,serpiente).setDelay(300);
    }

    @Override
    Sorpresas conseguirSorpresaAleatoria(Tablero tablero){
        return null;
    }

    /**
     * Obtener la imagen de la sorpresa del paquete imgs
     * @return La imagen de la sorpresa
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/disminucion.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
