package Aplicacion;

public class JuegoExcepcion extends Exception{

    /**/
    public final static String GUARDAR = "Ha ocurrido un error al guardar";

    /**/
    public final static String ABRIR = "Ha ocurrido un error al abrir";

    /**/
    public  final static String RECORD = "Ha ocurrido un erro al guardar el puntaje";

    public JuegoExcepcion(String message){
        super(message);
    }
}
