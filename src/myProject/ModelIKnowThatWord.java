package myProject;

import java.util.ArrayList;

public class ModelIKnowThatWord {
    public Palabras palabra;
    public int palabrasNivel;
    public int estado;
    public int aciertos;
    private ArrayList<String> arrayPalabrasNivel, arrayPalabrasMemorizar;

    public ModelIKnowThatWord() {
        palabra = new Palabras();
        palabrasNivel = 20;
        estado = 0;
        aciertos = 0;
    }

    public int determinarPalabrasNivel(int nivelAJugar) {
        switch (nivelAJugar) {
            case 1:
                palabrasNivel = 20;
                break;
            case 2:
                palabrasNivel = 40;
                break;
            case 3:
                palabrasNivel = 50;
                break;
            case 4:
                palabrasNivel = 60;
                break;
            case 5:
                palabrasNivel = 70;
                break;
            case 6:
                palabrasNivel = 80;
                break;
            case 7:
                palabrasNivel = 100;
                break;
            case 8:
                palabrasNivel = 120;
                break;
            case 9:
                palabrasNivel = 140;
                break;
            case 10:
                palabrasNivel = 200;
                break;
        }
        return palabrasNivel;
    }

    public ArrayList<String> getArrayPalabrasNivel(){
        return getArrayPalabrasNivel();
    }

    public ArrayList<String> getArrayPalabrasMemorizar(){
        return getArrayPalabrasMemorizar();
    }

/**
 public int aciertosTotales() {
 int aciertos = 0;
 if (palabraAcierto = true)
 {
 return aciertos + 1;
 }
 return aciertos;
 }

 public boolean palabraEstaEnNivel()
 {
 for(int i=0; i<=palabrasNivel.length;i++)
 if (palabrasNivel[i] == palabra)
 {
 return true;
 }
 else
 {
 return false;
 }
 }

 public int total()
 {
 switch(elegirNivel.getNumber
 }

 public int porcentaje()
 {
 return (aciertos*100)/total;
 }
 */
}