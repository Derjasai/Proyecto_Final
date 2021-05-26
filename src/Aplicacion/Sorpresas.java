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
     * @param barriers Lista de barreras del tablero
     * @param serpientes Lista de serpientes en juego
     * @param serpiente Serpiente que lanza la sorpresa pendiente
     */
    abstract void lanzar(ArrayList<Barrier> barriers, Serpiente[] serpientes, Serpiente serpiente);

    abstract Sorpresas conseguirSorpresaAleatoria(Tablero tablero);

    public void cambiarPosicion(boolean cambiar){
        if(cambiar){cambiarPosicion();}
    }

    protected Serpiente serpienteActuar(Serpiente[] serpientes, Serpiente serpiente){
        if(multiplayer){
            if(serpientes[0] == serpiente){
                return serpientes[1];
            }
            else {return serpientes[0];}
        }else {return serpiente;}
    }
}