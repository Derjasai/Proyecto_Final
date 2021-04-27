package Aplicacion;

import java.awt.*;

public class ManzanaArcoiris extends Alimento{

    private Tablero tablero;

    public ManzanaArcoiris(Tablero tablero){
        super(tablero);
        color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        incremento = 2;
    }

    @Override
    public Color getColor() {
        color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        return super.getColor();
    }
}
