package Aplicacion;

import Persistencia.Manager;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

public class Juego implements Serializable {

    private Tablero tablero;
    public Boolean perdio,enPausa;

    public Juego(int ancho, int alto, int unidadTablero, String[] jugdores, Color[] coloresCabezaSerpientes, Color[] coloresCuerpoSerpiestes, String[] alimentosAPoner){
        tablero = new Tablero(ancho,alto,unidadTablero,jugdores,coloresCabezaSerpientes,coloresCuerpoSerpiestes,alimentosAPoner);
    }

    /**
     * Retorna en donde se encuentra el alimento
     * @param numeroAlimento Poscion del alimento en la lista
     * @return Retorna la posicion del alimento
     */
    public int[] getAlimentoPosicion ( int numeroAlimento){
        return tablero.getAlimentoPosicion(numeroAlimento);
    }

    /**
     * Mueve la serpiente
     */
    public void moveSerpiente(int i){
        tablero.moveSerpiente(i);
    }

    /**
     * Retorna el color del alimento
     * @param i Posicion del alimento en la lista
     * @return Retorna el color del alimento
     */
    public Color getColorAlimento(int i){
        return tablero.getColorAlimento(i);
    }

    /**
     * Retorna la serpiente
     * @return Retorna la serpiente
     */
    public Serpiente getSerpiente(int i){
        return tablero.getSerpiente(i);
    }

    /**
     * Retorna la puntuacion de la serpiente
     * @return Retorna la puntuacion de la serpiente
     */
    public int getPuntuacionSerpiente(int i){
        return  tablero.getPuntuacionSerpiente(i);
    }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color del cuerpo de la serpiente
     */
    public Color getColorCuerpo(int i){
        return tablero.getColorCuerpo(i);
    }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color de la cabeza de la serpiente
     */
    public Color getColorCabeza(int i){
        return tablero.getColorCabeza(i);
    }

    /**
     * Decide si la serpiente "come" un alimento, se le suma lo indicado al cuerpo de la serpiente
     */
    public void serpienteComeAlimento(int i){
        tablero.serpienteComeAlimento();
    }

    /**
     * Decide si el tablero esta en una posicion de perdida
     * @return Retorna si el tablero esta en una posicion de perdida
     */
    public void perderJuego(){
        perdio = tablero.perderJuego();
    }

    public char getDirection(int i){
        return tablero.getSerpiente(i).direction;
    }

    public void cambiarPosAlimentos(){
        for (int i = 0; i < 2; i++) {
            tablero.cambiarPosiconAlimento(i);
        }
    }

    public int[] getSorpresaPos(){return tablero.getSorpresaPosicion();}

    public Image getImageSorpresa(){return tablero.getSorpresaImage();}

    public void serpienteTomaSorpresa(){tablero.serpienteTomaSopresa();}

    public void serpienteLanzaPoder(int i){tablero.serpienteLanzaPoder(i);}

    public void save(File selectedFile) throws JuegoExcepcion {
        Manager.getInstance().save(selectedFile, this);
    }

    public static Juego abra(File selectedFile) throws  JuegoExcepcion{
        return Manager.getInstance().guardar(selectedFile);
    }
}
