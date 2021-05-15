package Aplicacion;

import javax.imageio.*;
import java.awt.*;
import java.io.Serializable;

abstract class Sorpresas implements Serializable {
    public int x,y;
    private final int UNIDAD_TABLERO,ALTO,ANCHO;
    public Image image;

    public Sorpresas(int unidadTablero, int ancho, int alto){
        UNIDAD_TABLERO = unidadTablero;
        ALTO = alto;
        ANCHO = ancho;
        x = Tablero.random.nextInt(ancho/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = Tablero.random.nextInt(alto/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
    }

    /**
     * Cambia la posicon x,y del Alimento
     */
    public void cambiarPosicion(){
        x = Tablero.random.nextInt(ANCHO/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = Tablero.random.nextInt(ALTO/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
    }

    abstract void lanzar();

    abstract Image getImage();
}
