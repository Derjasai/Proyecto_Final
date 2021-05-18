package Aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
public class Tablero implements Serializable {

    private String[] jugadores;
    private final int UNIDAD_TABLERO;
    private final int ancho,alto;
    private Serpiente[] serpientes;
    private String[] alimentosTotales, sorpresasTotales;
    protected ArrayList<Elemento> elementos;
    static public Random random = new Random();
    public boolean multiplayer;

    /**
     * Constructor de la clase
     * @param ancho Ancho del tablero
     * @param alto Alto del tablero
     * @param unidadTablero Unidad cuadrada del tablero
     * @param jugdores Lista del nombre de jugadores, maximo 2
     * @param coloresCabezaserpientes Lista del color de las cabezas de las serpientes[], maximo 2
     * @param coloresCuerpoSerpiestes Lista del color de las cuerpos de las serpientes[], maximo 2
     * @param alimentosAPoner Lista de alimentos para jugar
     */
    public Tablero(int ancho, int alto, int unidadTablero, String[] jugdores, Color[] coloresCabezaserpientes, Color[] coloresCuerpoSerpiestes, String[] alimentosAPoner, String[] sorpresasAPoner) {
        random = new Random();
        multiplayer = jugdores.length == 2;

        this.jugadores = jugdores;
        this.alto = alto;
        this.ancho = ancho;
        this.UNIDAD_TABLERO = unidadTablero;
        alimentosTotales = alimentosAPoner;
        sorpresasTotales = sorpresasAPoner;
        elementos = new ArrayList<>();

        elementos.add(0,alimentoAleatorio());
        elementos.add(1,alimentoAleatorio());
        elementos.add(2,sorpresaAleatoria());

        serpientes = new Serpiente[2];

        serpientes[0] = new Serpiente(UNIDAD_TABLERO,ancho,alto,jugdores[0],coloresCabezaserpientes[0],coloresCuerpoSerpiestes[0], alto-unidadTablero, 0);
        if(multiplayer){serpientes[1] = new Serpiente(UNIDAD_TABLERO,ancho,alto,jugdores[1],coloresCabezaserpientes[1],coloresCuerpoSerpiestes[1],0,ancho-unidadTablero);}
        nuevosAlimentos();
    }

    private Sorpresas sorpresaAleatoria(){
        switch (sorpresasTotales[random.nextInt(sorpresasTotales.length)]) {
            case "Aumento vel." -> {
                return new Aumento(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Disminucion vel." -> {
                return new Disminucion(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Bloque Trampa"->{
                return new BloqueTrampa(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Division"->{
                return new Division(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Fuego"->{
                return new EstrellaFuego(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Lupa"->{
                return new Lupa(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            default -> {
                return sorpresaAleatoria();
            }
        }
    }

    /**
     * Retorna un nuevo alimento aleatorio
     * @return Nuevo alimento aleatorio
     */
    private Alimento alimentoAleatorio() {
        switch (alimentosTotales[random.nextInt(alimentosTotales.length)]){
            case "Manzana" -> {
                return new Manzana(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Manzana Arcoiris" -> {
                return new ManzanaArcoiris(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Dulce" -> {
                return new Dulce(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            case "Veneno" -> {
                return new Veneno(UNIDAD_TABLERO, ancho, alto, multiplayer);
            }
            default -> {return  alimentoAleatorio();}
        }
    }

    public void nuevosAlimentos(){
        elementos.set(0, alimentoAleatorio());
        elementos.set(1, alimentoAleatorio());
        cambiarPosiconElemento(elementos.get(0));
        cambiarPosiconElemento(elementos.get(1));
    }

    /**
     * Cambia la posicion de un elemento hasta que se encuentre en una posicion valida
     * @param elemento Posicion del alimento en la lista que se desea cambiar
     */
    public void cambiarPosiconElemento(Elemento elemento){
        while(confirmarPosicionElementos(elemento)){
            elemento.cambiarPosicion();
        }
    }

    private boolean confirmarPosicionElementos(Elemento elemento){
        for(Elemento elemento1:elementos){
            if(!elemento.equals(elemento1)){
                if(elemento1.x == elemento.x && elemento1.y == elemento.y){return true;}
            }
        }

        for (int j = serpientes[0].cuerpo; j >= 0;j--) {
            if(elemento.x == serpientes[0].poscionX[j] && elemento.y == serpientes[0].poscionY[j]){ return true;}
            if (multiplayer){if(elemento.x == serpientes[1].poscionX[j] && elemento.y == serpientes[1].poscionY[j]){ return true;}}
        }
        return false;
    }

    /**
     * Mueve una serpiente
     * @param i Numero de la serpiente
     */
    public void moveSerpiente(int i){
        serpientes[i].mover();
    }

    /**
     * Retorna la serpiente
     * @return Retorna la serpiente
     */
    public Serpiente getSerpiente(int i){
        return serpientes[i];
    }

    /**
     * Retorna la puntuacion de la serpiente
     * @return Retorna la puntuacion de la serpiente
     */
    public int getPuntuacionSerpiente(int i){
        return serpientes[i].getPuntuacion();
    }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color del cuerpo de la serpiente
     */
    public Color getColorCuerpo(int i){ return serpientes[i].colorCuerpo; }

    /**
     * Retorna el color del cuerpo de la serpiente
     * @return Retorna el color de la cabeza de la serpiente
     */
    public Color getColorCabeza(int i){
        return serpientes[i].colorCabeza;
    }

    /**
     * Decide si la serpiente "come" un alimento, se le suma lo indicado al cuerpo de la serpiente
     */
    public void serpienteComeAlimento(){
        for (int i = 0; i < 2; i++) {
            if(elementos.get(i).x == serpientes[0].poscionX[0] && elementos.get(i).y == serpientes[0].poscionY[0]){
                if(serpientes[0].tomarAlimentoSiguiente){
                    ((Alimento)elementos.get(i)).incremento(new Color[] {serpientes[0].colorCuerpo, serpientes[0].colorCabeza}, serpientes[0]);
                    elementos.set(i, alimentoAleatorio());
                    cambiarPosiconElemento(elementos.get(i));
                }else{serpientes[0].tomarAlimentoSiguiente = true;}

            }
            if (multiplayer){
                if(elementos.get(i).x == serpientes[1].poscionX[0] && elementos.get(i).y == serpientes[1].poscionY[0]){
                    if(serpientes[1].tomarAlimentoSiguiente){
                        ((Alimento)elementos.get(i)).incremento(new Color[] {serpientes[1].colorCuerpo, serpientes[1].colorCabeza}, serpientes[1]);

                        elementos.set(i, alimentoAleatorio());
                        cambiarPosiconElemento(elementos.get(i));
                    }else{serpientes[1].tomarAlimentoSiguiente = true;}
                }
            }

        }
    }

    public void serpienteTomaSopresa(){
        if(elementos.get(2).x == serpientes[0].poscionX[0] && elementos.get(2).y == serpientes[0].poscionY[0]){
            if(serpientes[0].sorpresaPendiente == null){
                serpientes[0].sorpresaPendiente = (Sorpresas) elementos.get(2);
                elementos.set(2,sorpresaAleatoria());
                cambiarPosiconElemento(elementos.get(2));
            }
        }
        if(multiplayer){
            if(elementos.get(2).x == serpientes[1].poscionX[0] && elementos.get(2).y == serpientes[1].poscionY[0]){
                if(serpientes[1].sorpresaPendiente == null){
                    serpientes[1].sorpresaPendiente = (Sorpresas) elementos.get(2);
                    cambiarPosiconElemento(elementos.get(2));
                }
            }
        }
    }

    public void serpienteLanzaPoder(int i){
        if(serpientes[i].sorpresaPendiente != null){
            serpientes[i].sorpresaPendiente.lanzar(elementos, serpientes,serpientes[i]);
            serpientes[i].sorpresaPendiente = null;
            for (int j = 3; j < elementos.size(); j++) {
                cambiarPosiconElemento(elementos.get(j));
            }
        }
    }
    public ArrayList<Elemento> getElementos(){
        return elementos;
    }

    /**
     * Decide si el tablero esta en una posicion de perdida
     * @return Retorna si el tablero esta en una posicion de perdida
     */
    public boolean perderJuego(){
        serpientes[0].estaMuerta(elementos, serpientes);
        if(multiplayer){
            serpientes[1].estaMuerta(elementos, serpientes);
            System.out.println(serpientes[0].muerta + " " + serpientes[1].muerta);
            return (serpientes[0].muerta && serpientes[1].muerta);}
        return serpientes[0].muerta;
    }

}