package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Serpiente implements Serializable {

    private final int UNIDAD_TABLERO,ANCHO,ALTO;
    public int[] poscionX;
    public int[] poscionY;
    public String nombre;
    public char direction;
    public int cuerpo = 3;
    public Sorpresas sorpresaPendiente;
    public Color colorCabeza;
    public Color colorCuerpo;
    public boolean tomarAlimentoSiguiente = true;
    public boolean muerta = false;

    /**
     * Constructor de la clase serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ANCHO ANCHO del tablero
     * @param ALTO ALTO del tablero
     * @param nombre nombre de la serpiente
     * @param colorCabeza Color de la cabeza
     * @param colorCuerpo Color del cuerpo
     */
    public Serpiente(int unidadTablero, int ANCHO, int ALTO, String nombre, Color colorCabeza, Color colorCuerpo, int ejeY, int ejeX){
        this.UNIDAD_TABLERO = unidadTablero;
        this.ANCHO = ANCHO;
        this.ALTO = ALTO;
        this.nombre = nombre;
        this.colorCuerpo = colorCuerpo;
        this.colorCabeza = colorCabeza;
        int totalidadSerpiente = (ALTO/ UNIDAD_TABLERO)*(ANCHO/UNIDAD_TABLERO);
        poscionX = new int[totalidadSerpiente];
        poscionY = new int[totalidadSerpiente];
        direction = 'D';
        if(ejeX==0){direction = 'R';}
        if(ejeY==0){direction = 'L';}
        for(int i = cuerpo+5;i>=0;i--) {
            poscionX[i] = ejeX;
            poscionY[i] = ejeY;
        }
        for (int i = 0; i < cuerpo-1; i++) {
                mover();
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
        if(!muerta){
            ubicarCuerpo();
            switch (direction) {
                case 'U' -> poscionY[0] = poscionY[0] - UNIDAD_TABLERO;
                case 'D' -> poscionY[0] = poscionY[0] + UNIDAD_TABLERO;
                case 'L' -> poscionX[0] = poscionX[0] - UNIDAD_TABLERO;
                case 'R' -> poscionX[0] = poscionX[0] + UNIDAD_TABLERO;
            }
        }else{
            for (int i = 0; i < poscionX.length; i++) {
                this.poscionX[i] = -500;
                this.poscionY[i] = -500;
            }
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
    
    public void estaMuerta(ArrayList<Elemento> elementos, Serpiente[] serpientes){
        if(this.cuerpo <= 0){muerta = true;}

        for(Serpiente serpiente: serpientes){
            if(serpiente != null){
                for(int i = serpiente.cuerpo;i>0;i--) {
                    if ((this.poscionX[0] == serpiente.poscionX[i]) && (this.poscionY[0] == serpiente.poscionY[i])) {
                        muerta = true;
                    }
                }
            }
        }

        if(this.poscionX[0] < 0) { muerta = true; }
        if(this.poscionX[0] >= ANCHO) { muerta = true; }
        if(this.poscionY[0] < 0) { muerta = true; }
        if(this.poscionY[0] >= ALTO){muerta = true;}
        
        for (int i = 3; i < elementos.size(); i++) {
            if(this.poscionX[0] == elementos.get(i).x && this.poscionY[0] == elementos.get(i).y){
                elementos.remove(i);
                muerta = true;
            }
        }
    }
}
