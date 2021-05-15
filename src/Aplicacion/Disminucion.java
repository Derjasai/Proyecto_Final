package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Disminucion extends Flechas{

    public Disminucion(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
    }

    public void lanzar(){

    }

    @Override
    Image getImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/imgs/flecha.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


}
