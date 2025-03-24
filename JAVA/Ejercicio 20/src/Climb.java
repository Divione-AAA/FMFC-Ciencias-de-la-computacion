public class Climb {

    private int[] ans;

    public int answer(int n,int m){
        if(n==m) return 1;
        if(n>m) return 0;
        ans[n]=answer(n+1,m) + answer(n+2,m);
        return ans[n];
    }
}