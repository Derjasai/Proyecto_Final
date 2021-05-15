package Aplicacion;

import java.awt.*;
import java.io.Serializable;

public class Serpiente implements Serializable {

    private final int UNIDAD_TABLERO;
    public int[] poscionX;
    public int[] poscionY;
    public String nombre;
    public char direction;
    public int cuerpo = 3;
    public Sorpresas sorpresaPendiente;
    public Color colorCabeza;
    public Color colorCuerpo;

    /**
     * Constructor de la clase serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param nombre nombre de la serpiente
     * @param colorCabeza Color de la cabeza
     * @param colorCuerpo Color del cuerpo
     */
    public Serpiente(int unidadTablero, int ancho, int alto, String nombre, Color colorCabeza, Color colorCuerpo, int ejeY, int ejeX){
        this.UNIDAD_TABLERO = unidadTablero;
        this.nombre = nombre;
        this.colorCuerpo = colorCuerpo;
        this.colorCabeza = colorCabeza;
        int totalidadSerpiente = (alto/ UNIDAD_TABLERO)*(ancho/UNIDAD_TABLERO);
        poscionX = new int[totalidadSerpiente];
        poscionY = new int[totalidadSerpiente];
        if(ejeX==0){direction = 'R';}
        if(ejeY==0){direction = 'L';}
        for(int i = cuerpo+5;i>=0;i--) {
            poscionX[i] = ejeX;
            poscionY[i] = ejeY;
        }
    }

    private void ubicarCuerpo(){
        for(int i = cuerpo+5;i>0;i--) {
            poscionX[i] = poscionX[i-1];
            poscionY[i] = poscionY[i-1];
        }
    }

    /**
     * Metodo para cambiar la direccion de la serpiente
     */
    public void mover(){
        ubicarCuerpo();
        switch (direction) {
            case 'U' -> poscionY[0] = poscionY[0] - UNIDAD_TABLERO;
            case 'D' -> poscionY[0] = poscionY[0] + UNIDAD_TABLERO;
            case 'L' -> poscionX[0] = poscionX[0] - UNIDAD_TABLERO;
            case 'R' -> poscionX[0] = poscionX[0] + UNIDAD_TABLERO;
        }
    }

    public void setDirection(char direction){
        this.direction = direction;
    }


    public int getPuntuacion(){
        return cuerpo - 3;
    }

    public Image sorpresaPendiente(){
        return sorpresaPendiente.getImage();
    }
}
