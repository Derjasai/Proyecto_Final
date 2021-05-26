package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cambiante extends Sorpresas{
    /**
     * Constructor de la clase Sorpresas
     *
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho         Ancho del tablero
     * @param alto          Alto del tablero
     * @param multiplayer   Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Cambiante(int unidadTablero, int ancho, int alto, boolean multiplayer) {
        super(unidadTablero, ancho, alto, multiplayer);
    }

    /**
     * Metodo para cuando se lanza una sorpresa pendiente de una serpiente
     *
     * @param barriers   Lista de barreras del tablero
     * @param serpientes Lista de serpientes en juego
     * @param serpiente  Serpiente que lanza la sorpresa pendiente
     */
    @Override
    void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente) {
        serpiente.crecer(2);
    }

    @Override
    Sorpresas conseguirSorpresaAleatoria(Tablero tablero){
        return tablero.sorpresaAleatoria();
    }
    
    @Override
    public void cambiarPosicion(boolean cambiar){
        cambiarPosicion();
    }
    /**
     * Obtener una imagen de la carpeta imgs
     * @return La imagen del alimento
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/cambiante.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
