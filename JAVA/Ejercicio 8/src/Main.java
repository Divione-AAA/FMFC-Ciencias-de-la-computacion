import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Felicidad t = new Felicidad();
        t.setN(7896532);
        boolean f=t.determinarFelicidad();
        System.out.println(f);
    }
}