package Aplicacion;

import java.awt.*;

public class Veneno extends Alimento{

    /**
     * Alimento que mata a la serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Veneno(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
        color = Color.GREEN;
    }
}
