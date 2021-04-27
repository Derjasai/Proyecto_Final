package Aplicacion;

public class Serpiente {

    private Tablero tablero;
    public int[] poscionX;
    public int[] poscionY;
    public char direction = 'R';
    public int cuerpo = 3;
    private Sorpresas sorpresaPendiente;

    public Serpiente(Tablero tablero){
        int totalidadSerpiente = (tablero.alto/ tablero.UNIDAD_TABLERO)*(tablero.ancho/ tablero.UNIDAD_TABLERO);
        this.tablero = tablero;
        poscionX = new int[totalidadSerpiente];
        poscionY = new int[totalidadSerpiente];
    }

    public void move(){
        for(int i = cuerpo+1;i>0;i--) {
            poscionX[i] = poscionX[i-1];
            poscionY[i] = poscionY[i-1];
        }
        switch (direction) {
            case 'U' -> poscionY[0] = poscionY[0] - tablero.UNIDAD_TABLERO;
            case 'D' -> poscionY[0] = poscionY[0] + tablero.UNIDAD_TABLERO;
            case 'L' -> poscionX[0] = poscionX[0] - tablero.UNIDAD_TABLERO;
            case 'R' -> poscionX[0] = poscionX[0] + tablero.UNIDAD_TABLERO;
        }
    }

    public void setDirection(char direction){
        this.direction = direction;
        move();
    }
}
