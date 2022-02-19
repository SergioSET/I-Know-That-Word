package myProject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase obtiene las palabras totales del juego
 */
public class Palabras {
    private ArrayList<String> diccionario = new ArrayList<String>();

    public Palabras(){
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile("Palabras.txt");
    }

    /**
     * Retorna el diccionario de todas las palabras del juego
     * */

    public ArrayList<String> getPalabras(){
        return diccionario;
    }
}