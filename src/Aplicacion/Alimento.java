package Aplicacion;

import java.awt.*;
import java.util.Random;

abstract class Alimento {

    private int alto,ancho;
    private final int UNIDAD_TABLERO;
    public int x;
    public int y;
    public Random random = new Random();
    protected Color color;

    /**
     * Constructor de las clases que hereden de alimento
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Alimento(int unidadTablero, int ancho, int alto){
        this.UNIDAD_TABLERO = unidadTablero;
        this.ancho = ancho;
        this.alto = alto;
        x = random.nextInt(ancho/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = random.nextInt(alto/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
    }

    /**
     * Cambia la posicon x,y del Alimento
     */
    public void cambiarPosicion(){
        x = random.nextInt(ancho/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = random.nextInt(alto/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     * @param compararColor Colores de la serpiente que come el alimento
     */
    abstract int incremento(Color[] compararColor);

    /**
     * Retorna el color del alimento
     * @return Retorna el color del alimento
     */
    public Color getColor(){
        return color;
    }
}
