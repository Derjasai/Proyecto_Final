package Aplicacion;

import java.awt.*;
import java.util.Random;

abstract class Alimento {

    private int alto,ancho;
    private final int UNIDAD_TABLERO;
    public int x;
    public int y;
    public int incremento;
    public Random random = new Random();
    protected Color color;

    public Alimento(int unidadTablero, int ancho, int alto){
        this.UNIDAD_TABLERO = unidadTablero;
        this.ancho = ancho;
        this.alto = alto;
        x = random.nextInt(ancho/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = random.nextInt(alto/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
        incremento = 1;
    }

    public void cambiarPosicion(){
        x = random.nextInt(ancho/ UNIDAD_TABLERO) * UNIDAD_TABLERO;
        y = random.nextInt(alto/ UNIDAD_TABLERO)* UNIDAD_TABLERO;
    }

    public Color getColor(){
        return color;
    }
}
