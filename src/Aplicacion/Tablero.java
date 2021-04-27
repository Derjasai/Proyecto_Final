package Aplicacion;

import java.awt.*;
import java.util.*;
public class Tablero {

    private int jugadores;
    public final int UNIDAD_TABLERO;
    public int ancho, alto;
    private Serpiente serpientes;
    private Alimento[] alimentos;
    private Random random;
    private java.util.Timer timerObjetos;

    public Tablero(int ancho, int alto, int jugdores, int unidadTablero) {
        random = new Random();
        this.jugadores = jugdores;
        this.alto = alto;
        this.ancho = ancho;
        alimentos = new Alimento[2];
        this.UNIDAD_TABLERO = unidadTablero;
        serpientes = new Serpiente(this);
        colorcarAlimentos();
        timerObjetos = new Timer();
        timerObjetos.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    cambiarPosiconAlimento(i);
                }
            }
        }, 5000,5000);
    }

    public Boolean confirmarPosicionAlimentos(int i){
            for (int j = serpientes.cuerpo; j > 0;j--) {
                if(alimentos[i].x == serpientes.poscionX[j] && alimentos[i].y == serpientes.poscionY[j]){return true;}
            }

        return false;
    }

    public Alimento alimentoAleatorio() {
        switch (random.nextInt(3)) {
            case 0 -> {
                return new Manzana(this);
            }
            case 1 -> {
                return new ManzanaArcoiris(this);
            }
            case 2 -> {
                return new Dulce(this);
            }
            default -> {
                return new Veneno(this);
            }
        }
    }

    public void colorcarAlimentos() {
        for (int i = 0; i < 2; i++) {
            alimentos[i] = alimentoAleatorio();
        }
    }

    public int[] getAlimentoPosicion ( int numeroAlimento){
        return new int[]{alimentos[numeroAlimento].x, alimentos[numeroAlimento].y};
    }

    public void cambiarPosiconAlimento(int i){
        alimentos[i].cambiarPosicion();
        while(confirmarPosicionAlimentos(i)){
            alimentos[i].cambiarPosicion();
        }
    }

    public void moveSerpiente(char direccion){
        serpientes.setDirection(direccion);
    }

    public Color getColorAlimento(int i){
        return alimentos[i].getColor();
    }

    public Serpiente getSerpiente(){
        return serpientes;
    }

    public void setColorSerpiente(Color cabeza, Color cuerpo){
        this.serpientes.colorCabeza = cabeza;
        this.serpientes.colorCuerpo = cuerpo;
    }

    public Color getColorCuerpo(){
        return serpientes.colorCuerpo;
    }

    public Color getColorCabeza(){
        return serpientes.colorCabeza;
    }

    public void serpienteComeAlimento(){
        for (int i = 0; i < 2; i++) {
            if(alimentos[i].x == serpientes.poscionX[0] && alimentos[i].y == serpientes.poscionY[0]){
                serpientes.cuerpo += alimentos[i].incremento;
                alimentos[i] = alimentoAleatorio();
                cambiarPosiconAlimento(i);
            }
        }
    }

    public boolean perderJuego(){
        for(int i = serpientes.cuerpo;i>0;i--) {
            if ((serpientes.poscionX[0] == serpientes.poscionX[i]) && (serpientes.poscionY[0] == serpientes.poscionY[i])) {
                return true;
            }
        }
        if(serpientes.poscionX[0] < 0) {
            return true;
        }
        if(serpientes.poscionX[0] >= ancho) {
            return true;
        }
        if(serpientes.poscionY[0] < 0) {
            return true;
        }
        if(serpientes.poscionY[0] >= alto) {
            return true;
        }
        return false;
    }
}