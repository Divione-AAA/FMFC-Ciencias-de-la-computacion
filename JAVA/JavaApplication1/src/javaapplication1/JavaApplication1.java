package javaapplication1;

class JavaApplication1 {

    public static void main(String[] args) {
        // TODO code application logic here

        int [] arreglo = {2, 7, 11, 4};
        int target = 15;

        int indice1=0;
        int indice2=0;

       
        for (int i = 0; i < arreglo.length; i++) {
            for (int j = 0; j < arreglo.length; j++) {
                if (arreglo[i] + arreglo[j] == target) {
                    indice1 = i;
                    indice2 = j;
                    break;
                }
                
            }

        }
    System.out.print(indice1+" "+" "+indice2);
    }

}
