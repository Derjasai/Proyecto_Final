package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ManzanaArcoiris extends Alimento{

    /**
     * Manzana que cambia de color con cada tik, de manera aleatoria, aumenta en 2 el cuerpo de la serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public ManzanaArcoiris(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
        color = new Color(Tablero.random.nextInt(255),Tablero.random.nextInt(255),Tablero.random.nextInt(255));
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     *
     * @param compararColor Colores de la serpiente que come el alimento
     */
    @Override
    void incremento(Color[] compararColor, Serpiente serpiente) {
        serpiente.cuerpo += 3;
    }

    @Override
    public Color getColor() {
        color = new Color(Tablero.random.nextInt(255),Tablero.random.nextInt(255),Tablero.random.nextInt(255));
        return super.getColor();
    }

    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/multicolor.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
