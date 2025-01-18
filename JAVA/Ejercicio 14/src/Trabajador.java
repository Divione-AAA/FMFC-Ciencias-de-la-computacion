import java.time.*;
import java.time.chrono.ChronoLocalDate;

public class Trabajador {

    private String carnet,nombre,str;

    boolean pal(int a, int b){
        if(b<=a) return true;
        char f=str.charAt(a);
        char g=str.charAt(b);
        return f==g ? pal(a+1,b-1) : false;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    int date(){
        String dia=carnet.substring(0,2);
        String mes=carnet.substring(2,4);
        String año=carnet.substring(4,8);
        int d=Integer.parseInt(dia);
        int m=Integer.parseInt(mes);
        int a=Integer.parseInt(año);

        LocalDate born = LocalDate.of(a,m,d);
        LocalDate now = LocalDate.now();

        Period espacio =Period.between(born,now);

        return espacio.getYears();
    }
}
