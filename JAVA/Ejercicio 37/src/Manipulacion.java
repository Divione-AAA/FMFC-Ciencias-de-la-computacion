import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Manipulacion {

    public static Map<Integer, Integer> leerYSumarPuntajes(String ruta) throws Exception {
        Map<Integer, Integer> mapaPuntajes = new HashMap<>();
        if(true){
            throw new LimiteException("hh");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int control=0; //para controlar el orden ascendente
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();//elimina los espacios en blanco
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(" ");
                    int numero = Integer.parseInt(partes[0]);
                    int puntos = Integer.parseInt(partes[1]);
                    if(puntos<1 || puntos>10){
                        throw new LimiteException("Los valores van de 1 a 10");
                    }
                    if(control!=numero-1){
                        throw new AscendenteException();
                    }

                    //aqui anade a el numero en cuestion los puntos
                    //si ya existia previamente, cosa que no debe ser por la excepcion de alla arriba
                    //aun asi lo anade a el numero ya existente y se lo suma
                    mapaPuntajes.put(numero, mapaPuntajes.getOrDefault(numero, 0) + puntos);
                    control=numero;// aqui lo redefino
                }
            }
        } catch (LimiteException e) {
            e.printStackTrace();
        }catch (AscendenteException e) {
            e.printStackTrace();
        }

        return mapaPuntajes;
    }

    public static void escribirResultadosOrdenados(Map<Integer, Integer> mapa, String rutaSalida) throws IOException {

        //Los convierto a una lista y los ordeno
        List<Map.Entry<Integer, Integer>> listaOrdenada = mapa.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    int cmp = Integer.compare(e2.getValue(), e1.getValue());
                    return (cmp != 0) ? cmp : Integer.compare(e1.getKey(), e2.getKey());
                })
                .collect(Collectors.toList());

        //crea el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
            for (Map.Entry<Integer, Integer> entrada : listaOrdenada) {// for each
                bw.write(entrada.getKey() + " " + entrada.getValue());//los escribe
                bw.newLine();//salta de linea
            }
        }

        //lanza la Excepcion

    }
}
