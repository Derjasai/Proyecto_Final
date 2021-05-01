package Aplicacion;

import java.awt.*;

public class Manzana extends Alimento{

    /**
     * Alimento de color rojo que aumenta el cuerpo de la serpiente en uno cada vez que se come
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Manzana(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
        incremento = 1;
        color = Color.RED;
    }
}
