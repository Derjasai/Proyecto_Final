package Aplicacion;

import java.awt.*;
import java.io.Serializable;

public class Elemento implements Serializable {

    protected int UNIDAD_TABLERO,ANCHO,ALTO;
    public int x,y;
    protected boolean multiplayer;

    /**
     * Constructor de las clases que hereden de alimento
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Elemento(int unidadTablero, int ancho, int alto, boolean multiplayer){
        this.UNIDAD_TABLERO = unidadTablero;
        this.ANCHO = ancho;
        this.ALTO = alto;
        this.multiplayer = multiplayer;
        cambiarPosicion();
    }

    /**
     * Cambia la posicon x,y del Alimento
     */
    public void cambiarPosicion(){
        x = Tablero.random.nextInt(ANCHO/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = Tablero.random.nextInt(ALTO/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
    }

    public Image getImage(){
        return null;
    }
}
