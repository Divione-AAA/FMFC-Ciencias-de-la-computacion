package MY_APP;

public class Estudiante extends Persona{

    private int engish;
    private int math;
    private int sports;
    private int sciences;

    public Estudiante(String name, int age) {
        super(name, age);
    }

    public boolean notas(int engish,int math, int sports, int sciences){

        int vmax=-1000000,vmin=1000000;
        vmax=Integer.max(engish,math);
        vmax=Integer.max(vmax,sports);
        vmax=Integer.max(vmax,sciences);

        vmin=Integer.min(engish,math);
        vmin=Integer.min(vmin,sports);
        vmin=Integer.min(vmin,sciences);

        if(vmin<-1 || vmax>10){
            System.out.println("Los valores deben estar entre 1 y 10");
            return true;
        }else{
            this.engish=engish;
            this.math=math;
            this.sports=sports;
            this.sciences=sciences;
            return false;
        }
    }

    public void promedio(){
        double k=(engish+math+sciences+sports)/4;
        System.out.println(k);
    }
}
