package Persistencia;

import Aplicacion.*;

import java.io.*;

public class Manager {

    private static Manager manager;

    private Manager(){
    }

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            return manager;
        }
        return manager;
    }

    public void save(File file, Juego juego) throws JuegoExcepcion {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(juego);
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JuegoExcepcion(JuegoExcepcion.GUARDAR);
        }
    }
    
    public Juego guardar(File file) throws JuegoExcepcion {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Juego automata = (Juego) objectIn.readObject();
            objectIn.close();
            return automata;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JuegoExcepcion(JuegoExcepcion.ABRIR);
        }
    }
}
