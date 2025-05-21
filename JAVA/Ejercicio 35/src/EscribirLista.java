import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class EscribirLista {

    public static void escribirLista(ArrayList<Persona> lista) throws Exception {
        File archivo = new File("src/test.txt");
        FileWriter fw = new FileWriter(archivo);
        String text = lista.toString();
        System.out.println(text);
        fw.write(text);
        fw.close();
    }
}
