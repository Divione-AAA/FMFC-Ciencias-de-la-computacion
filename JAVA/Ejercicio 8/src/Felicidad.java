public class Felicidad {
    private long n;

    private long t3;
    private boolean [] ver= new boolean[1000000];

    public void setN(long n) {
        this.n = n;
    }

    public long calcularPD(long v){
        long t1,t2=0;
        while(v!=0){
            t1=v%10;
            v=(v-t1);
            v=v/10;
            t2+=(t1*t1);
        }
        return t2;
    }

    public boolean determinarFelicidad(){

        t3=calcularPD(n);

        while(t3!=n){
            if(t3==1){
                return true;
            }
            if(ver[(int)t3]==true){
                return false;
            }

            ver[(int)t3]=true;

            System.out.println(t3);
            t3=calcularPD(t3);
        }

        //System.out.println(calcularPD(7));

        return false;
    }
}
