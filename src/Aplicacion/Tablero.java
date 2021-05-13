package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
public class Tablero implements Serializable {

    private String[] jugadores;
    private final int UNIDAD_TABLERO;
    private final int ancho,alto;
    private Serpiente serpientes;
    private String[] alimentosTotales;
    private Alimento[] alimentosEnJuego;
    private Random random;

    /**
     * Constructor de la clase
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param unidadTablero Unidad cuadrada del tablero
     * @param jugdores Lista del nombre de jugadores, maximo 2
     * @param coloresCabezaSerpientes Lista del color de las cabezas de las serpientes, maximo 2
     * @param coloresCuerpoSerpiestes Lista del color de las cuerpos de las serpientes, maximo 2
     * @param alimentosAPoner Lista de alimentos para jugar
     */
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
    }

    /**
     * Revisa si el alimento fue colocado en un lugar del tablero donde no este el cuerpo de las serpientes ni otro
     * alimento
     * @param i posicion del alimento en la lista
     * @return Si el alimento esta en una posicion invalida en el tablero
     */
    private Boolean confirmarPosicionAlimentos(int i){
        if(alimentosEnJuego[0].x == alimentosEnJuego[1].x && alimentosEnJuego[0].y == alimentosEnJuego[1].y){return true;}
            for (int j = serpientes.cuerpo; j >= 0;j--) {
                if(alimentosEnJuego[i].x == serpientes.poscionX[j] && alimentosEnJuego[i].y == serpientes.poscionY[j]){
                    return true;}

            }
        return false;
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
    private void cambiarPosiconAlimento(int i){
        alimentosEnJuego[i].cambiarPosicion();
        while(confirmarPosicionAlimentos(i)){
            alimentosEnJuego[i].cambiarPosicion();
        }
    }

    /**
     * Mueve la serpiente
     * @param direccion Direccion hacia donde moverla
     */
    public void moveSerpiente(char direccion){
        serpientes.mover(direccion);
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
    public Serpiente getSerpiente(){
        return serpientes;
    }

    /**
     * Retorna la puntuacion de la serpiente
     * @return Retorna la puntuacion de la serpiente
     */
    public int getPuntuacionSerpiente(){
        return serpientes.getPuntuacion();
    }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color del cuerpo de la serpiente
     */
    public Color getColorCuerpo(){
        return serpientes.colorCuerpo;
    }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color de la cabeza de la serpiente
     */
    public Color getColorCabeza(){
        return serpientes.colorCabeza;
    }

    /**
     * Decide si la serpiente "come" un alimento, se le suma lo indicado al cuerpo de la serpiente
     */
    public void serpienteComeAlimento(){
        for (int i = 0; i < 2; i++) {
            if(alimentosEnJuego[i].x == serpientes.poscionX[0] && alimentosEnJuego[i].y == serpientes.poscionY[0]){
                if (alimentosEnJuego[i] instanceof Veneno) {
                    serpientes.cuerpo = 0;
                }else{
                    serpientes.cuerpo += alimentosEnJuego[i].incremento(new Color[] {serpientes.colorCuerpo, serpientes.colorCabeza});
                }
                alimentosEnJuego[i] = alimentoAleatorio();
                cambiarPosiconAlimento(i);
            }
        }
    }

    /**
     * Decide si el tablero esta en una posicion de perdida
     * @return Retorna si el tablero esta en una posicion de perdida
     */
    public boolean perderJuego(){
        if(serpientes.cuerpo <= 0){return true;}
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