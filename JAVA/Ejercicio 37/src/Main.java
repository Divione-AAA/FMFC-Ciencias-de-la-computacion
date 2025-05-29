import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //Aqui declaro los strings de los archivos para no escribirlos directamente
        //Preferi usar un mapa porque almacena los valores por llave y valor y me es mas sencillo que acerlo con arreglo
        //o vectores y puedo igualmente ordenarlos

        String archivoEntrada = "encuesta.txt";
        String archivoSalida = "resultados_popularidad.txt";

        try {
            // Se llama a la clase que hace la manipulacion de los archivos y se le pasa de parametro el archiov de entrada
            Map<Integer, Integer> resultados = Manipulacion.leerYSumarPuntajes(archivoEntrada);

            // Se ordena y se escribe
            Manipulacion.escribirResultadosOrdenados(resultados, archivoSalida);
           // System.out.println("Archivo de salida generado correctamente.");
        } catch (IOException e) {
            //coge la excepcion
            e.printStackTrace();
        }
    }
}