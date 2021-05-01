package Aplicacion;

import java.awt.*;
import java.util.*;
public class Tablero {

    private String[] jugadores;
    private final int UNIDAD_TABLERO;
    private final int ancho,alto;
    private Serpiente serpientes;
    private String[] alimentosTotales;
    private Alimento[] alimentosEnJuego;
    private Random random;
    private java.util.Timer timerObjetos;

    public Tablero(int ancho, int alto, int unidadTablero, String[] jugdores, Color[] coloresCabezaSerpientes, Color[] coloresCuerpoSerpiestes, String[] alimentosAPoner) {
        random = new Random();
        this.jugadores = jugdores;
        this.alto = alto;
        this.ancho = ancho;
        this.UNIDAD_TABLERO = unidadTablero;
        alimentosTotales = alimentosAPoner;
        alimentosEnJuego = new Alimento[2];
        serpientes = new Serpiente(UNIDAD_TABLERO,ancho,alto,jugdores[0],coloresCabezaSerpientes[0],coloresCuerpoSerpiestes[0]);
        colorcarAlimentos();
        timerObjetos = new Timer();
    }

    private Boolean confirmarPosicionAlimentos(int i){
            for (int j = serpientes.cuerpo; j > 0;j--) {
                if(alimentosEnJuego[i].x == serpientes.poscionX[j] && alimentosEnJuego[i].y == serpientes.poscionY[j]){
                    if(alimentosEnJuego[0].x != alimentosEnJuego[1].x && alimentosEnJuego[0].y != alimentosEnJuego[1].y)
                    {return true;}
                    }
            }
        return false;
    }

    private Alimento alimentoAleatorio() {
        switch (alimentosTotales[random.nextInt(alimentosTotales.length)]){
            case "Manzana" -> {
                return new Manzana(UNIDAD_TABLERO, ancho, alto);
            }
            case "Manzana Arcoiris" -> {
                return new ManzanaArcoiris(UNIDAD_TABLERO, ancho, alto);
            }
            case "Dulce" -> {
                return new Dulce(UNIDAD_TABLERO, ancho, alto);
            }
            case "Veneno" -> {
                return new Veneno(UNIDAD_TABLERO, ancho, alto);
            }
            default -> {return  alimentoAleatorio();}
        }
    }

    private void colorcarAlimentos() {
        for (int i = 0; i < 2; i++) {
            alimentosEnJuego[i] = alimentoAleatorio();
        }
    }

    public int[] getAlimentoPosicion ( int numeroAlimento){
        return new int[]{alimentosEnJuego[numeroAlimento].x, alimentosEnJuego[numeroAlimento].y};
    }

    private void cambiarPosiconAlimento(int i){
        alimentosEnJuego[i].cambiarPosicion();
        while(confirmarPosicionAlimentos(i)){
            alimentosEnJuego[i].cambiarPosicion();
        }
    }

    public void moveSerpiente(char direccion){
        serpientes.mover(direccion);
    }

    public Color getColorAlimento(int i){
        return alimentosEnJuego[i].getColor();
    }

    public Serpiente getSerpiente(){
        return serpientes;
    }

    public int getPuntuacionSerpiente(){
        return serpientes.getPuntuacion();
    }

    public Color getColorCuerpo(){
        return serpientes.colorCuerpo;
    }

    public Color getColorCabeza(){
        return serpientes.colorCabeza;
    }

    public void serpienteComeAlimento(){
        for (int i = 0; i < 2; i++) {
            if(alimentosEnJuego[i].x == serpientes.poscionX[0] && alimentosEnJuego[i].y == serpientes.poscionY[0]){
                serpientes.cuerpo += alimentosEnJuego[i].incremento;
                alimentosEnJuego[i] = alimentoAleatorio();
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