package Persistencia;

import Aplicacion.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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
            throw new JuegoExcepcion(JuegoExcepcion.ABRIR);
        }
    }

    /**
     * Escribe un record en el archivo de records
     * @param file Archivo de records donde se quiere guardar el record
     * @param nombre Nombre de la serpiente del record
     * @param record Record de la serpiente
     * @throws JuegoExcepcion PRopaga la excepcion si no se encuentra el archivo
     */
    public void escribirRecords(File file, String nombre, int record)throws JuegoExcepcion{
        try {
            FileWriter fw = new FileWriter(file,true);
            fw.write(nombre+": "+record+"\n");
            fw.close();
        } catch (IOException e) {
            throw new JuegoExcepcion(JuegoExcepcion.RECORD);
        }
        organizarRecords(leerRecords(file), file);
    }

    /**
     * Lee los records de un archivo y los devuelve en forma de ArrayList
     * @param file Archivo que se quirere leer
     * @return Retorna la lista de records del archivo
     */
    private ArrayList<String> leerRecords(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Organiza un unico record en la lista
     * @param records Lista de records
     * @param index Indice donde se quiere incluir el record
     * @param recordCambiar Record a reposicionar
     */
    private void organizarRecordEnLista(ArrayList<String> records, int index, String recordCambiar){
        for (int i = 0; i < records.size(); i++) {
            if(i == index){
                String prov = records.get(index);
                records.set(index, recordCambiar);
                records.set(records.size()-1,prov);
            }
            else if(i > index){
                String prov = records.get(i);
                records.set(i,records.get(records.size()-1));
                records.set(records.size()-1,prov);
            }
        }
    }

    /**
     * Organiza la lista de records de mayor a menor
     * @param records ArrayList de los records del archivo
     * @param file Archivo de records
     */
    private void organizarRecords(ArrayList<String> records, File file){
        String organizar = records.get(records.size() - 1);
        String recordOrganizar = organizar.split(": ")[1];

        for (String record: records){
            String recordComparar = record.split(": ")[1];
            if(Integer.parseInt(recordComparar) < Integer.parseInt(recordOrganizar)){
                organizarRecordEnLista(records, records.indexOf(record), organizar  );
                break;
            }
        }
        try{
            FileWriter fw = new FileWriter(file,false);
            fw.write("");
            fw.close();
            for (String record: records){
                fw = new FileWriter(file, true);
                fw.write(record+"\n");
                fw.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtener la lista de records
     * @param path El camino del archivo
     * @return Lista de records
     */
    public ArrayList<String> getTop5(String path){
        File file = new File(path);
        ArrayList<String> records = leerRecords(file);
        return records;
    }
}
