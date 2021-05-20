package Aplicacion;

import java.awt.*;
import java.io.Serializable;

/**
 * Clase abstracta que contiene los elementos principales de los alimentos
 */
abstract class Alimento extends Elemento implements Serializable{
    protected Color color;

    /**
     * Constructor de las clases que hereden de alimento
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Alimento(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     * @param compararColor Colores de la serpiente que come el alimento
     */
    abstract void incremento(Color[] compararColor, Serpiente serpiente);

    /**
     * Retorna el color del alimento
     * @return Retorna el color del alimento
     */
    public Color getColor(){
        return color;
    }
}
