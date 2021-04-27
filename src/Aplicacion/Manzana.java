package Aplicacion;

import java.awt.*;

public class Manzana extends Alimento{

    private Tablero tablero;

    public Manzana(Tablero tablero){
        super(tablero);
        incremento = 1;
        color = Color.RED;
    }
}
