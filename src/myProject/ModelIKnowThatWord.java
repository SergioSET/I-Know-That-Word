package myProject;

public class ModelIKnowThatWord {
    public Palabras palabra;
    public int nivel;
    public Palabras[] palabrasNivel;

    public ModelIKnowThatWord() {
        palabra = new Palabras();
        nivel = 0;
        palabrasNivel = new Palabras[0];
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