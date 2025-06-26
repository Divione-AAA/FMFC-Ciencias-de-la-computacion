import java.io.*;
//esta es la misma clase que use en la pregutna de Lili
public class FicherosTexto {
    public static void main(String[] args) {
        try ( //crea los buffer de lectura y escritura
                BufferedReader br = new BufferedReader(new FileReader("fileinput.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("fileout.txt"))
        ){
            //lee la priemra linea. Para eso usa br.readline() que obtiene en un String la priemra linea
            // .trim() elimina los espacios en blancos
            //el Integer.parseInte() convierte la cadena a entero
            int cantidadEstudiantes = Integer.parseInt(br.readLine().trim());

            for (int i = 0; i < cantidadEstudiantes; i++) {
                String nombre = br.readLine().trim();
                double notaMat = Double.parseDouble(br.readLine().trim());
                double notaEsp = Double.parseDouble(br.readLine().trim());
                double notaHist = Double.parseDouble(br.readLine().trim());
                double indiceAcademico = Double.parseDouble(br.readLine().trim());
                //el br.readLine() salta automaticamente de linea en el fichero de texto
                double indice = (notaMat + notaEsp + notaHist) / 6 + indiceAcademico / 2;

                //aqui escribe la cadena en fileout.txt
                bw.write(nombre + " -> Índice: " + String.format("%.2f", indice));
                bw.newLine();//aqui salta de linea en el fichero de texto que esta escribiendo nuevo
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

