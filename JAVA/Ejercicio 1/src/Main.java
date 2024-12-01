import java.util.Vector;
public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Persona fulana = new Persona();
        fulana.Setdia(4);
        fulana.Setmes(8);
        fulana.Setano(2004);

        Vector<Integer> t= new Vector<Integer>();
        t=fulana.Getedad();

        Integer edad=(2024-t.getLast());

        System.out.println(edad);
    }
}