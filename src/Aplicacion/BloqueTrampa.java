package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BloqueTrampa extends Sorpresas{
    public BloqueTrampa(int unidadTablero, int ancho, int alto, boolean multiplayer) {
        super(unidadTablero, ancho, alto, multiplayer);
    }

    @Override
    void lanzar(ArrayList<Elemento> elemento, Serpiente[] serpientes, Serpiente serpiente) {
        elemento.add(new Barrier(UNIDAD_TABLERO,ANCHO,ALTO, multiplayer));

    }

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
