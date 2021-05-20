package Aplicacion;

/**
 * Clase encargada de manejar el comportamiento de las felchas de velocidad
 */
abstract class Flechas extends Sorpresas{

    /**
     * Constructor de la clase Flechas
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param multiplayer Valor de verdad para sbaer si hay mas de un jugador en el juego
     */
    public Flechas(int unidadTablero, int ancho, int alto, boolean multiplayer){
        super(unidadTablero,ancho,alto,multiplayer);
    }
}
