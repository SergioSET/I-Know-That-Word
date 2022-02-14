package myProject;

import java.util.ArrayList;
import java.util.Random;

public class Palabras {
    private ArrayList<String> diccionario = new ArrayList<String>();

    public Palabras(){
        FileManager fileManager = new FileManager();
        diccionario = fileManager.lecturaFile("Palabras.txt");
    }

    public ArrayList<String> getPalabras(){
        return diccionario;
    }
}