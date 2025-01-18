import java.util.HashMap;
import java.util.Map;

public class Universidad {

    private int arr[][];
    private int fac[];
    private int tf;

    public void setArr(int[][] arr) {
        this.arr = arr;
        calc();
    }

    private void calc(){
        int sum=0;

        for(int i=0;i<arr.length;i++){
            sum=0;
            for(int j=0;j<arr[i].length;j++){
                sum+=arr[i][j];
            }
            this.fac[i]=sum;
            this.tf+=sum;
        }
    }

    public int totxfa(){
        return this.tf;
    }

    public int mayor(){

        int max=-1,ans=-1;

        for(int i=0;i<this.fac.length;i++){
            if(this.fac[i]>max) {
                ans = i;
                max = fac[i];
            }
        }
        return ans;
    }

    public Map<Integer,Integer> menor(int f){
        Map<Integer,Integer> ans = new HashMap<>();
        for(int i=0;i< fac.length;i++){
            if(fac[i]<f) ans.put(i,fac[i]);
        }
        return ans;
    }
}
