package myProject;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Jugador {

    private FileWriter fileWriter;
    private BufferedWriter output;
    private String usuario;
    public int nivel, puesto;
    public ArrayList<String> listaUsuarios = new ArrayList<String>();
    public ArrayList<String> listaNiveles = new ArrayList<String>();

    public Jugador() {
        FileManager fileManager = new FileManager();
        listaUsuarios = fileManager.lecturaFile("Usuarios.txt");
        listaNiveles = fileManager.lecturaFile("Niveles.txt");
        puesto = 0;
        usuario = "";
        nivel = 1;
    }

    public boolean estaRegistrado(String nombre) {
        if (listaUsuarios.contains(nombre)) {
            puesto = listaUsuarios.indexOf(nombre);
            return true;
        } else {
            return false;
        }
    }

    public int getNivel() {
        nivel = Integer.parseInt(listaNiveles.get(puesto));
        System.out.println(nivel);
        return nivel;
    }

    public String getNombre() {
        return usuario;
    }

    public boolean nombreVacio(String nombre) {
        if (nombre == null || nombre == "" || nombre.length() < 3) {
            return true;
        } else {
            return false;
        }
    }

    public void registrarJugador(String nombre, int nivelSuperado) {
        String user = nombre;
        int level = nivelSuperado;

        try {
            fileWriter = new FileWriter("src/myProject/archivos/Usuarios.txt", true);//True=conservar, False=Borrar
            output = new BufferedWriter(fileWriter);
            output.newLine();
            output.write(nombre);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fileWriter = new FileWriter("src/myProject/archivos/Niveles.txt", true);//True=conservar, False=Borrar
            output = new BufferedWriter(fileWriter);
            output.newLine();
            output.write(String.valueOf(nivelSuperado));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarUsuario(String nombre, int nivelSuperado) {

        int indexAGuardar = 0;
        String valorActualizar;

        indexAGuardar = listaUsuarios.indexOf(nombre);

        System.out.println(indexAGuardar);

        valorActualizar = String.valueOf(listaNiveles.get(indexAGuardar));

        listaNiveles.set(indexAGuardar, valorActualizar);

        System.out.println(listaNiveles);

        try {
            fileWriter = new FileWriter("src/myProject/archivos/Niveles.txt", false);//True=conservar, False=Borrar
            output = new BufferedWriter(fileWriter);
            for (int i = 0; i < listaNiveles.toArray().length; i++) {
                output.write(String.valueOf(listaNiveles.get(i)));
                if ((i + 1) < listaNiveles.toArray().length) {
                    output.newLine();
                }
                System.out.println(listaNiveles.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
