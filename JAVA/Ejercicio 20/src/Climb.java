public class Climb {

    private int[] ans;

    public int answer(int n,int m){
        if(n==m) return 1;
        if(n>m) return 0;
        ans[n]=answer(n+1,m) + answer(n+2,m);
        return ans[n];
    }

    public int fib(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        return fib(n-1)+fib(n-2);
    }

    public int pow(int n,int p){
        if(p==0) return 1;
        if(p==1) return n;
        return pow(n,p-1)*n;
    }
}