package Aplicacion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase encargada de manejar el comportamiento general de las sorpresas
 */
abstract class Sorpresas extends Elemento implements Serializable {

    /**
     * Constructor de la clase Sorpresas
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Sorpresas(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }

    /**
     * Metodo para cuando se lanza una sorpresa pendiente de una serpiente
     * @param elemento Lista de elementos del tablero para modificar
     * @param serpientes Lista de serpientes en juego
     * @param serpiente Serpiente que lanza la sorpresa pendiente
     */
    abstract void lanzar(ArrayList<Elemento> elemento, Serpiente[] serpientes, Serpiente serpiente);
}
