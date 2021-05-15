package Aplicacion;

import java.awt.*;

abstract class Flechas extends Sorpresas{

    public Flechas(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);

    }

    abstract void lanzar();

    abstract Image getImage();
}
