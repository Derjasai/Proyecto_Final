package Aplicacion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Lupa extends Sorpresas{
    public Lupa(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    @Override
    void lanzar(ArrayList<Elemento> elemento, Serpiente[] serpientes, Serpiente serpiente) {
        if(multiplayer){
            if(serpientes[0] == serpiente){
                serpientes[1].tomarAlimentoSiguiente = false;
            }
            else {serpientes[0].tomarAlimentoSiguiente = false;}
        }else {serpiente.tomarAlimentoSiguiente = false;}
    }

    public Image getImage(){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("imgs/lupa.png")); // la carga en una BufferedReade
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
