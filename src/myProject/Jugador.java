package myProject;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Jugador {

    private String usuario;
    private int nivel;
    public ArrayList<String> listaUsuarios = new ArrayList<String>();
    public ArrayList<String> listaNiveles = new ArrayList<String>();

    public Jugador() {
        FileManager fileManager = new FileManager();
        listaUsuarios = fileManager.lecturaFile("Usuarios.txt");
        listaNiveles = fileManager.lecturaFile("Niveles.txt");
        usuario = "";
        nivel = 1;
    }

    public boolean estaRegistrado(String nombre) {

        for (int i = 0; i <= listaUsuarios.toArray().length; i++) {
            if(listaUsuarios.get(i) == nombre){
                usuario = nombre;
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public int getNivel() {
        for (int i = 0; i <= listaNiveles.toArray().length; i++) {
            if(listaUsuarios.get(i) == usuario){
                nivel = Integer.parseInt(listaNiveles.get(i));
            }
        }
        return nivel;
    }

    public String getNombre() {
        return usuario;
    }

    public void registrarJugador(String nombre, int nivelSuperado) {

        if (estaRegistrado(nombre) == false) {

            String user = nombre;
            int level = nivelSuperado;

            FileWriter fw = null;
            try {
                fw = new FileWriter("Usuarios.txt", true);
                fw.write(user);
                fw.close();
                fw = new FileWriter("Niveles.txt", true);
                fw.write(level);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (estaRegistrado(nombre) == true) {
            JOptionPane.showInputDialog(null, "Este jugador ya está registrado");
        }
    }

    public void actualizarNivel(String nombre, int nivelSuperado) {

        if (estaRegistrado(nombre) == false) {

            String user = nombre;
            int level = nivelSuperado;

            FileWriter fw = null;
            try {
                fw = new FileWriter("Usuarios.txt", true);
                fw.write(user);
                fw.close();
                fw = new FileWriter("Niveles.txt", true);
                fw.write(level);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (estaRegistrado(nombre) == true) {
            JOptionPane.showInputDialog(null, "Este jugador ya está registrado");
        }
    }
}
