package Aplicacion;

import java.awt.*;

public class ManzanaArcoiris extends Alimento{

    /**
     * Manzana que cambia de color con cada tik, de manera aleatoria, aumenta en 2 el cuerpo de la serpiente
     * @param unidadTablero Unidad cuadrada del tablero
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     */
    public ManzanaArcoiris(int unidadTablero, int ancho, int alto){
        super(unidadTablero,ancho,alto);
        color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

    /**
     * Decide el incremento que da los alimentos al ser comida
     *
     * @param compararColor Colores de la serpiente que come el alimento
     */
    @Override
    int incremento(Color[] compararColor) {
        return 3;
    }


    @Override
    public Color getColor() {
        color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        return super.getColor();
    }
}
