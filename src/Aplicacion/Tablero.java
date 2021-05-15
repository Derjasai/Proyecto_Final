package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
public class Tablero implements Serializable {

    private String[] jugadores;
    private final int UNIDAD_TABLERO;
    private final int ancho,alto;
    private Serpiente[] serpientes;
    private String[] alimentosTotales, sorpresasTotales;
    private Alimento[] alimentosEnJuego;
    private Sorpresas sopresa;
    static public Random random = new Random();

    /**
     * Constructor de la clase
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param unidadTablero Unidad cuadrada del tablero
     * @param jugdores Lista del nombre de jugadores, maximo 2
     * @param coloresCabezaserpientes Lista del color de las cabezas de las serpientes[], maximo 2
     * @param coloresCuerpoSerpiestes Lista del color de las cuerpos de las serpientes[], maximo 2
     * @param alimentosAPoner Lista de alimentos para jugar
     */
    public Tablero(int ancho, int alto, int unidadTablero, String[] jugdores, Color[] coloresCabezaserpientes, Color[] coloresCuerpoSerpiestes, String[] alimentosAPoner) {
        random = new Random();
        this.jugadores = jugdores;
        this.alto = alto;
        this.ancho = ancho;
        this.UNIDAD_TABLERO = unidadTablero;
        alimentosTotales = alimentosAPoner;
        alimentosEnJuego = new Alimento[2];
        sopresa = new Aumento(unidadTablero,ancho,alto);
        serpientes = new Serpiente[2];
        serpientes[0] = new Serpiente(UNIDAD_TABLERO,ancho,alto,jugdores[0],coloresCabezaserpientes[0],coloresCuerpoSerpiestes[0], alto-unidadTablero, 0);
        serpientes[1] = new Serpiente(UNIDAD_TABLERO,ancho,alto,jugdores[0],coloresCabezaserpientes[0],coloresCuerpoSerpiestes[0],0,ancho-unidadTablero);
        colorcarAlimentos();
    }

    /**
     * Revisa si el alimento fue colocado en un lugar del tablero donde no este el cuerpo de las serpientes[] ni otro
     * alimento
     * @param i posicion del alimento en la lista
     * @return Si el alimento esta en una posicion invalida en el tablero
     */
    private Boolean confirmarPosicionAlimentos(int i){
        if(alimentosEnJuego[0].x == alimentosEnJuego[1].x && alimentosEnJuego[0].y == alimentosEnJuego[1].y){return true;}
            for (int j = serpientes[0].cuerpo; j >= 0;j--) {
                for (int k = 0; k < 2; k++) {
                    if(alimentosEnJuego[i].x == serpientes[k].poscionX[j] && alimentosEnJuego[i].y == serpientes[k].poscionY[j]){
                        return true;}
                }
            }
        return false;
    }

    private Sorpresas sorpresaAleatoria(){
        switch (alimentosTotales[random.nextInt(alimentosTotales.length)]) {
            case "Aumento" -> {
                return new Aumento(UNIDAD_TABLERO, ancho, alto);
            }
            case "Disminucion" -> {
                return new Disminucion(UNIDAD_TABLERO, ancho, alto);
            }
            default -> {
                return sorpresaAleatoria();
            }
        }
    }

    /**
     * Retorna un nuevo alimento aleatorio
     * @return Nuevo alimento aleatorio
     */
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

    /**
     * Coloca los alimentos de forma aleatoria en el tablero
     */
    private void colorcarAlimentos() {
        for (int i = 0; i < 2; i++) {
            alimentosEnJuego[i] = alimentoAleatorio();
        }
    }

    /**
     * Retorna en donde se encuentra el alimento
     * @param numeroAlimento Poscion del alimento en la lista
     * @return Retorna la posicion del alimento
     */
    public int[] getAlimentoPosicion ( int numeroAlimento){
        return new int[]{alimentosEnJuego[numeroAlimento].x, alimentosEnJuego[numeroAlimento].y};
    }

    /**
     * Cambia la posicion del alimento hasta que se encuentre en una posicion valida
     * @param i Posicion del alimento en la lista que se desea cambiar
     */
    public void cambiarPosiconAlimento(int i){
        alimentosEnJuego[i].cambiarPosicion();
        while(confirmarPosicionAlimentos(i)){
            alimentosEnJuego[i].cambiarPosicion();
        }
    }

    /**
     * Mueve una serpiente
     * @param i Numero de la serpiente
     */
    public void moveSerpiente(int i){
        serpientes[i].mover();
    }

    /**
     * Retorna el color del alimento
     * @param i Posicion del alimento en la lista
     * @return Retorna el color del alimento
     */
    public Color getColorAlimento(int i){
        return alimentosEnJuego[i].getColor();
    }

    /**
     * Retorna la serpiente
     * @return Retorna la serpiente
     */
    public Serpiente getSerpiente(int i){
        return serpientes[i];
    }

    /**
     * Retorna la puntuacion de la serpiente
     * @return Retorna la puntuacion de la serpiente
     */
    public int getPuntuacionSerpiente(int i){
        return serpientes[i].getPuntuacion();
    }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color del cuerpo de la serpiente
     */
    public Color getColorCuerpo(int i){ return serpientes[i].colorCuerpo; }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color de la cabeza de la serpiente
     */
    public Color getColorCabeza(int i){
        return serpientes[i].colorCabeza;
    }

    /**
     * Decide si la serpiente "come" un alimento, se le suma lo indicado al cuerpo de la serpiente
     */
    public void serpienteComeAlimento(){
        for (int i = 0; i < 2; i++) {
            if(alimentosEnJuego[i].x == serpientes[0].poscionX[0] && alimentosEnJuego[i].y == serpientes[0].poscionY[0]){
                if (alimentosEnJuego[i] instanceof Veneno) {
                    serpientes[0].cuerpo = 0;
                }else{
                    serpientes[0].cuerpo += alimentosEnJuego[i].incremento(new Color[] {serpientes[0].colorCuerpo, serpientes[0].colorCabeza});
                }
                alimentosEnJuego[i] = alimentoAleatorio();
                cambiarPosiconAlimento(i);
            }
            if(alimentosEnJuego[i].x == serpientes[1].poscionX[0] && alimentosEnJuego[i].y == serpientes[1].poscionY[0]){
                if (alimentosEnJuego[i] instanceof Veneno) {
                    serpientes[1].cuerpo = 0;
                }else{
                    serpientes[1].cuerpo += alimentosEnJuego[i].incremento(new Color[] {serpientes[1].colorCuerpo, serpientes[1].colorCabeza});
                }
                alimentosEnJuego[i] = alimentoAleatorio();
                cambiarPosiconAlimento(i);
            }
        }
    }

    private boolean confirmarSorpresa(){
        if(sopresa.x == alimentosEnJuego[0].x && sopresa.y == alimentosEnJuego[0].y){return true;}
        if(sopresa.x == alimentosEnJuego[1].x && sopresa.y == alimentosEnJuego[1].y){return true;}
        for (int i = 0; i < 2; i++) {
            for (int j = serpientes[0].cuerpo; j >= 0;j--) {
                if(sopresa.x == serpientes[i].poscionX[j] && sopresa.y == serpientes[i].poscionY[j]){
                    return true;}
            }
        }
        return false;
    }

    private void cambiarPosicionSorpresa(){
        sopresa.cambiarPosicion();
        while(confirmarSorpresa()){
            sopresa.cambiarPosicion();
        }
    }

    public void serpienteTomaSopresa(){
        if(sopresa.x == serpientes[0].poscionX[0] && sopresa.y == serpientes[0].poscionY[0]){
            if(serpientes[0].sorpresaPendiente == null){
                serpientes[0].sorpresaPendiente = sopresa;
                cambiarPosicionSorpresa();
            }
        }
    }

    public void serpienteLanzaPoder(int i){
        if(serpientes[i].sorpresaPendiente != null){
            serpientes[i].sorpresaPendiente = null;
        }
    }

    public int[] getSorpresaPosicion (){
        return new int[]{sopresa.x, sopresa.y};
    }

    public Image getSorpresaImage(){
        return sopresa.getImage();
    }

    /**
     * Decide si el tablero esta en una posicion de perdida
     * @return Retorna si el tablero esta en una posicion de perdida
     */
    public boolean perderJuego(){
        if(serpientes[0].cuerpo <= 0){return true;}
        for(int i = serpientes[0].cuerpo;i>0;i--) {
            if ((serpientes[0].poscionX[0] == serpientes[0].poscionX[i]) && (serpientes[0].poscionY[0] == serpientes[0].poscionY[i])) {
                return true;
            }
        }
        if(serpientes[0].poscionX[0] < 0) {
            return true;
        }
        if(serpientes[0].poscionX[0] >= ancho) {
            return true;
        }
        if(serpientes[0].poscionY[0] < 0) {
            return true;
        }
        if(serpientes[0].poscionY[0] >= alto) {
            System.out.println(alto);
            return true;
        }
        return false;
    }

}