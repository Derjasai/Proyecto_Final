package Aplicacion;

import java.awt.*;

public class Dulce  extends Alimento{

    /**
     *Alimento de color azul que disminuye en uno el cuerpo de la serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public Dulce(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
        incremento = -1;
        color = Color.BLUE;
    }
}
