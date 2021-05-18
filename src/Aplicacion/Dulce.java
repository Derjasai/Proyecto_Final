package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dulce  extends Alimento{

    /**
     *Alimento de color azul que disminuye en uno el cuerpo de la serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Dulce(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
        color = Color.BLUE;
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     *
     * @param compararColor Colores de la serpiente que come el alimento
     */
    @Override
    void incremento(Color[] compararColor, Serpiente serpiente) {
        for(Color color: compararColor) {
            if(this.color == color){serpiente.cuerpo -= 2;}
        }
        serpiente.cuerpo -= 1;
    }

    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/dulce.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


}
