public class Primalidad {
    private boolean criba[]=new boolean[1000005];

    public void setCriba(){
        crib();
    }

    public void crib(){

        for(int i=2;i<1000000;i++){
            criba[i]=true;
        }

        for(int i=2;i<=1000000;i++){
            if(criba[i]){
                for(int j=i;j<=100000;j+=i){
                    //System.out.println(j);
                    criba[j]=false;
                }
                criba[i]=true;
            }
        }
    }

    public boolean detP(int k){
        return criba[k];
    }
}
