package Aplicacion;

import java.awt.*;
import java.util.Random;

abstract class Alimento {
    public int x;
    public int y;
    public int incremento;
    public Random random = new Random();
    public Tablero tablero;
    protected Color color;

    public Alimento(Tablero tablero){
        this.tablero = tablero;
        x = random.nextInt(tablero.ancho/ tablero.UNIDAD_TABLERO) * tablero.UNIDAD_TABLERO;
        y = random.nextInt(tablero.alto/ tablero.UNIDAD_TABLERO)* tablero.UNIDAD_TABLERO;
        incremento = 1;
    }

    public void cambiarPosicion(){
        x = random.nextInt(tablero.ancho/ tablero.UNIDAD_TABLERO) * tablero.UNIDAD_TABLERO;
        y = random.nextInt(tablero.alto/ tablero.UNIDAD_TABLERO)* tablero.UNIDAD_TABLERO;
    }

    public Color getColor(){
        return color;
    }
}
