import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int s=4;
        System.out.println("Introduzca parte real e inmaginaria de 2 complejos");
        //s=cin.nextInt();
        int x1=cin.nextInt();
        int y1=cin.nextInt();
        int x2=cin.nextInt();
        int y2=cin.nextInt();
        Irreal i1=new Irreal(x1,y1);
        Irreal i2=new Irreal(x2,y2);
        Irreal i3=new Irreal(0,0);
        i3=i1.add(i2);
        System.out.println("La suma es " );
        System.out.print(i3.real_int);
        System.out.print(" i");
        System.out.print(i3.imaginary_int );

    }
}