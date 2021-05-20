package Aplicacion;

import Persistencia.Manager;

import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase encargada de conectar la interfaz grafica con la parte logica
 */
public class Juego implements Serializable {

    private Tablero tablero;
    public Boolean perdio,enPausa;
    public  Boolean multiplayer;
    public int jugadores;

    /**
     * Constructor del Juego
     * @param ancho ancho de la ventana
     * @param alto Alto de la ventana
     * @param unidadTablero Unidad de juego del tablero
     * @param jugdores Nombres de los jugadores o el jugador
     * @param coloresCabezaSerpientes Colores de la cabeza de las o de la serpiente
     * @param coloresCuerpoSerpiestes Colores del cuerpo de las o de la serpiente
     * @param alimentosAPoner Lista con los alimentos a jugar
     * @param sorpresasAPoner Lista con las sorpresas a jugar
     */
    public Juego(int ancho, int alto, int unidadTablero, String[] jugdores, Color[] coloresCabezaSerpientes, Color[] coloresCuerpoSerpiestes, String[] alimentosAPoner, String[] sorpresasAPoner){
        this.jugadores = jugdores.length;
        multiplayer = jugadores == 2;
        tablero = new Tablero(ancho,alto,unidadTablero,jugdores,coloresCabezaSerpientes,coloresCuerpoSerpiestes,alimentosAPoner, sorpresasAPoner);
    }

    /**
     * Mueve la serpiente
     */
    public void moveSerpiente(int i){
        tablero.moveSerpiente(i);
    }

    /**
     * Retorna la serpiente
     * @return Retorna la serpiente
     */
    public Serpiente getSerpiente(int i){
        return tablero.getSerpiente(i);
    }

    /**
     * Decide si la serpiente "come" un alimento, se le suma lo indicado al cuerpo de la serpiente
     */
    public void serpienteComeAlimento(){
        tablero.serpienteComeAlimento();
    }

    /**
     * Decide si el tablero esta en una posicion de perdida
     * @return Retorna si el tablero esta en una posicion de perdida
     */
    public void perderJuego(){
        perdio = tablero.perderJuego();
    }

    /**
     * Le da la instruccion al tablero de cambiar la posicion de los alimentos
     */
    public void cambiarPosAlimentos(){
        tablero.nuevosAlimentos();
    }

    /**
     * Decide si una serpiente toma la sorpresa
     */
    public void serpienteTomaSorpresa(){tablero.serpienteTomaSopresa();}

    /**
     * Lanza el poder pendiente de una serpiente
     * @param i Posicion de la serpiente que lanza el poder
     */
    public void serpienteLanzaPoder(int i){tablero.serpienteLanzaPoder(i);}

    /**
     * Obtiene la lista de elemtnos del tablero
     * @return Retorna la lista de elementos del tablero
     */
    public ArrayList<Elemento> getElementos(){return tablero.getElementos();}


    /*Seccion de persistencia*/

    /**
     * Salvar un juego
     * @param selectedFile El path donde guardar el archivo
     * @throws JuegoExcepcion Si ocurre un error al guardar
     */
    public void save(File selectedFile) throws JuegoExcepcion {
        Manager.getInstance().save(selectedFile, this);
    }
    /**
     * Guardar un juego
     * @param selectedFile El path donde abrir el archivo
     * @throws JuegoExcepcion Si ocurre un error al abrir
     */
    public static Juego abra(File selectedFile) throws  JuegoExcepcion{
        return Manager.getInstance().guardar(selectedFile);
    }
}
