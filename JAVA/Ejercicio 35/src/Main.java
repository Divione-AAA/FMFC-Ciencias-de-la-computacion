import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        File archivo = new File("src/test.txt");

        //FileWriter fw = new FileWriter(archivo);
        //fw.write("Love");
        //fw.close();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
        br.close();
    }
}
