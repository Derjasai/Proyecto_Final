package Aplicacion;

import java.awt.*;

public class Dulce  extends Alimento{

    public Dulce(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
        incremento = -1;
        color = Color.BLUE;
    }
}
