package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase encargada de manjear el comportamiento de la manzana
 */
public class Manzana extends Alimento{

    /**
     * Alimento de color rojo que aumenta el cuerpo de la serpiente en uno cada vez que se come
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Manzana(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
        color = Color.RED;
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     *
     * @param compararColor Colores de la serpiente que come el alimento
     */
    @Override
    void incremento(Color[] compararColor, Serpiente serpiente) {
        for(Color color: compararColor) {
            if(this.color == color){serpiente.crecer(2);return;}
        }
        serpiente.crecer(1);
    }

    /**
     * Obtener la imagen de la carpeta imgs
     * @return La imagen del alimento
     */
    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/roja.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


}
