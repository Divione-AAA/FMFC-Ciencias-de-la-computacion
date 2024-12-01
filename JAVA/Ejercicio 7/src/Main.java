import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Introduzca un numero!");
        Scanner cin = new Scanner(System.in);
        int n=cin.nextInt();
        Calculadora p = new Calculadora();
        boolean j=p.determinarPerfeccion(n);
        if(j) System.out.println("Es perfecto");
        else System.out.println("No es perfecto");
        long  f=p.digitSum(n);
        System.out.println(f);
    }
}