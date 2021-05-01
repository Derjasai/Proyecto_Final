package Aplicacion;

import java.awt.*;

public class Manzana extends Alimento{

    private Tablero tablero;

    public Manzana(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
        incremento = 1;
        color = Color.RED;
    }
}
