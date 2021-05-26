package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase encargada de manejar el bloque tramapa
 */
public class BloqueTrampa extends Sorpresas{

    /**
     * Constructor de la clase BloqueTrampa
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public BloqueTrampa(int unidadTablero, int ancho, int alto, boolean multiplayer) {
        super(unidadTablero, ancho, alto, multiplayer);
    }

    @Override
    void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente) {
        barriers.add(new Barrier(UNIDAD_TABLERO,ANCHO,ALTO, multiplayer));

    }

    @Override
    Sorpresas conseguirSorpresaAleatoria(Tablero tablero) {
        return null;
    }

    /**
     * Obtener la imagen de la sorpresa en el paquete imgs
     * @return La imagen de la sorpresa
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/bloqueTrampa.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
