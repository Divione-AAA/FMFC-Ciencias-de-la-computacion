import java.util.*;
public class Calculadora {
    private Vector<Integer> v=new Vector<Integer>();

    private void determinarDivisores(int n){
        for (int i=1;i<n;i++){
            if(n%i==0) this.v.add(i);
        }
    }

    public boolean determinarPerfeccion(int n){
        int s=0;

        determinarDivisores(n);
        for (int i=0;i<v.size();i++) {
            s+=v.elementAt(i);
        }

        return s==n ? true : false;
    }

    public  long fibonnacci(int n){
        if(n==1) return 1;
        if(n==2) return 1;
        return fibonnacci(n-1)+fibonnacci(n-2);
    }

    public long digitSum(int n){

        int s=0;

        while(n>=1){
            int a=n%10;
            n=n/10;
            s+=a;
        }
    return s;
    }

}
