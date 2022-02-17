package myProject;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    //public static final String PATH = "src/myProject/archivos/Palabras.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    public ArrayList<String> lecturaFile(String archivo) {
        ArrayList<String> lista = new ArrayList<String>();

        String PATH = "src/myProject/archivos/"+archivo;

        try {
            fileReader = new FileReader(PATH);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while (line != null) {
                lista.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}
