public class Fecha {

    private int mes;

    public void setMes(int i){
        if(i>0 && i<13)
            this.mes=i;
        else
            System.out.println("Introduzca mes valido");
    }

    public void dias(){

        if(mes==1 || mes==3 ||mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
            System.out.println("Tiene 31 dias");
        }else if(mes==4 || mes==6 ||mes==9 || mes==11){
            System.out.println("tiene 30 dias");
        }else{
            System.out.println("Tiene 29 dias");
        }
    }
}
