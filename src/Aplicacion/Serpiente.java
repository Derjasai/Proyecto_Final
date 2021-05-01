package Aplicacion;

import java.awt.*;

public class Serpiente {

    private final int UNIDAD_TABLERO;
    public int[] poscionX;
    public int[] poscionY;
    public String nombre;
    public char direction = 'R';
    public int cuerpo = 3;
    private Sorpresas sorpresaPendiente;
    public Color colorCabeza;
    public Color colorCuerpo;

    public Serpiente(int unidadTablero, int ancho, int alto, String nombre, Color colorCabeza, Color colorCuerpo){
        this.UNIDAD_TABLERO = unidadTablero;
        this.nombre = nombre;
        this.colorCuerpo = colorCuerpo;
        this.colorCabeza = colorCabeza;
        int totalidadSerpiente = (alto/ UNIDAD_TABLERO)*(ancho/UNIDAD_TABLERO);
        poscionX = new int[totalidadSerpiente];
        poscionY = new int[totalidadSerpiente];
        poscionX[0] = 0; poscionY[0] = alto - UNIDAD_TABLERO;
    }

    public void mover(char direction){
        this.direction = direction;
        for(int i = cuerpo+2;i>0;i--) {
            poscionX[i] = poscionX[i-1];
            poscionY[i] = poscionY[i-1];
        }
        switch (direction) {
            case 'U' -> poscionY[0] = poscionY[0] - UNIDAD_TABLERO;
            case 'D' -> poscionY[0] = poscionY[0] + UNIDAD_TABLERO;
            case 'L' -> poscionX[0] = poscionX[0] - UNIDAD_TABLERO;
            case 'R' -> poscionX[0] = poscionX[0] + UNIDAD_TABLERO;
        }
    }

    public int getPuntuacion(){
        return cuerpo -3;
    }
}
