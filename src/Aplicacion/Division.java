package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Division extends Sorpresas{
    public Division(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    @Override
    void lanzar(ArrayList<Elemento> elemento, Serpiente[] serpientes, Serpiente serpiente) {
        if(multiplayer){
            if(serpientes[0] == serpiente){
                serpientes[1].cuerpo = serpientes[1].cuerpo/2;
            }
            else {serpientes[0].cuerpo = serpientes[0].cuerpo/2;}
        }else {serpiente.cuerpo = serpiente.cuerpo/2;}
    }

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
