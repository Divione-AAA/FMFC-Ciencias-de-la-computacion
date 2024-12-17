import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Introduzca el numero y las cantidad de asignaturas de un estudiante");
        Scanner cin = new Scanner(System.in);
        int n=cin.nextInt();
        int m=cin.nextInt();
        Estudiante fulano = new Estudiante();
        fulano.setNl(n);
        int arr[] = new int[5];
        double sum=0,t;
        for (int i=0;i<m;i++){
            t=cin.nextInt();
            sum+=t;
            if(t==2) arr[1]++;
            else if(t==3) arr[2]++;
            else if(t==4) arr[3]++;
            else if(t==5) arr[4]++;
        }
        fulano.setProm((double)(sum/m));
        fulano.setNotas(arr);
        boolean p= fulano.pase();
        System.out.println(p ? "Pasa con un promedio de: " + fulano.getProm() : "Poncho con un promedio de:" + fulano.getProm());
    }
}