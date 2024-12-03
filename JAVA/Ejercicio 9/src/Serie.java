public class Serie {
    private double n;

    public void setN(double n) {
        this.n = n;
    }

    private double factorial(double m){

        if(m==1) return 1.00;
        if(m==2) return 2.00;

        return m*factorial(m-1);
    }

    private double potenciar(double m,int p){
        return Math.pow(m,p);
    }

    public double serie(int j){

        double ans=1;

        for(int i =1;i<=j;i++){
            if(i%2==1)ans-=(potenciar(n,i*2)/factorial(i*2));
            else ans+=(potenciar(n,i*2)/factorial(i*2));
            //System.out.println((potenciar(n,i*2)/factorial(i*2)));
            //System.out.println(ans);
        }
        return ans;
    }
}
