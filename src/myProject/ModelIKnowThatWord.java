package myProject;

import java.util.ArrayList;

public class ModelIKnowThatWord {
    public Palabras palabra;
    public int palabrasMemorizar, palabrasNivel, estado, aciertos, aciertosNecesarios, porcentaje;
    public ArrayList<String> diccionario, arrayPalabrasNivel, arrayPalabrasMemorizar;

    public ModelIKnowThatWord() {
        palabra = new Palabras();
        palabrasNivel = 0;
        palabrasMemorizar = 0;
        porcentaje = 0;
        estado = 0;
        aciertos = 0;
        aciertosNecesarios = 0;
        diccionario = palabra.getPalabras();
        arrayPalabrasNivel = new ArrayList<String>();
        arrayPalabrasMemorizar = new ArrayList<String>();
    }

    public int determinarPalabrasNivel(int nivelAJugar) {
        switch (nivelAJugar) {
            case 1:
                palabrasNivel = 10;
                palabrasMemorizar = 5;
                aciertosNecesarios = 70;
                break;
            case 2:
                palabrasNivel = 40;
                palabrasMemorizar = 20;
                aciertosNecesarios = 70;
                break;
            case 3:
                palabrasNivel = 50;
                palabrasMemorizar = 25;
                aciertosNecesarios = 75;
                break;
            case 4:
                palabrasNivel = 60;
                palabrasMemorizar = 30;
                aciertosNecesarios = 80;
                break;
            case 5:
                palabrasNivel = 70;
                palabrasMemorizar = 35;
                aciertosNecesarios = 80;
                break;
            case 6:
                palabrasNivel = 80;
                palabrasMemorizar = 40;
                aciertosNecesarios = 85;
                break;
            case 7:
                palabrasNivel = 100;
                palabrasMemorizar = 50;
                aciertosNecesarios = 90;
                break;
            case 8:
                palabrasNivel = 120;
                palabrasMemorizar = 60;
                aciertosNecesarios = 90;
                break;
            case 9:
                palabrasNivel = 140;
                palabrasMemorizar = 70;
                aciertosNecesarios = 95;
                break;
            case 10:
                palabrasNivel = 200;
                palabrasMemorizar = 100;
                aciertosNecesarios = 100;
                break;
        }
        return palabrasNivel;
    }

    public ArrayList<String> getArrayPalabrasNivel() {

        for (int i = 0; i < palabrasNivel; i++) {
            int auxiliar = (int) (Math.random() * diccionario.size());

            if (!arrayPalabrasNivel.contains(diccionario.get(auxiliar))) {
                arrayPalabrasNivel.add(diccionario.get(auxiliar));
            } else {
                i = i - 1;
            }
        }

        return arrayPalabrasNivel;
    }

    public ArrayList<String> getArrayPalabrasMemorizar() {

        for (int i = 0; i < palabrasNivel / 2; i++) {
            int auxiliar = (int) (Math.random() * arrayPalabrasNivel.size());
            if (!arrayPalabrasMemorizar.contains(arrayPalabrasNivel.get(auxiliar))) {
                arrayPalabrasMemorizar.add(arrayPalabrasNivel.get(auxiliar));
            } else {
                i = i - 1;
            }
        }
        return arrayPalabrasMemorizar;
    }

    public boolean palabraEstaEnNivel(String palabra) {

        if (arrayPalabrasMemorizar.contains(palabra)) {
            return true;
        }else{
            return false;
        }
    }

    public int porcentaje() {

        porcentaje = (aciertos * 100) / palabrasNivel;

        return porcentaje;
    }

    public boolean superoNivel() {
        if (porcentaje >= aciertosNecesarios) {
            return true;
        } else {
            return false;
        }
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



 public int total()
 {
 switch(elegirNivel.getNumber
 }


 */
}