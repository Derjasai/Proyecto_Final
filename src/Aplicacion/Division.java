package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;

/**
 * Clase encargada de manejar la sorpresa Division
 */
public class Division extends Sorpresas{

    /**
     * Constructor de la clase Division
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Division(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    @Override
    void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente) {
        Serpiente prov = serpienteActuar(serpientes,serpiente);
        prov.crecer(-(prov.getCuerpo()-3)/2);
    }

    @Override
    Sorpresas conseguirSorpresaAleatoria(Tablero tablero) {
        return null;
    }

    /**
     * Obtener la imagen de la sorpresa del paquete imgs
     * @return La imagen de la sorpresa
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/division.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
