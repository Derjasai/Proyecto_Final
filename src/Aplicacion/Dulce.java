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
        color = Color.BLUE;
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     *
     * @param compararColor Colores de la serpiente que come el alimento
     */
    @Override
    int incremento(Color[] compararColor) {
        for(Color color: compararColor) {
            if(this.color == color){return -2;}
        }
        return -1;
    }


}
