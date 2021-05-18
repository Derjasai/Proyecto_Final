package Aplicacion;

import java.io.Serializable;
import java.util.ArrayList;

abstract class Sorpresas extends Elemento implements Serializable {

    public Sorpresas(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }
    abstract void lanzar(ArrayList<Elemento> elemento, Serpiente[] serpientes, Serpiente serpiente);
}
