package Aplicacion;

import java.awt.*;
import java.io.Serializable;

/**
 * Clase encargada de manejar el comportamiento a grandes rasgos de los Elementos del tablero, sin  incluir a la
 * serpiente
 */
public class Elemento implements Serializable {

    protected int UNIDAD_TABLERO,ANCHO,ALTO;
    public int x,y;
    protected boolean multiplayer;

    /**
     * Constructor de la clase Elemento
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

    /**
     * Obtener la imagen de cada uno de los elementos
     * @return Imagen del elemento
     */
    public Image getImage(){
        return null;
    }
}
