import java.util.Scanner;

public class Edificio {

    private int depa[][][]=new int[20][4][5];

    public void setDepa(int[][][] depa) {
        this.depa = depa;
    }

    void update(){
        System.out.println("indique la planta a actualizar");
        boolean c=true;
        while (c){
            System.out.println("Indique el piso, ala y habitacion a actualizar, 1 ocupado, 0 desocupado");
            int a,b,d;
            int e;
            Scanner cin = new Scanner(System.in);
            a=cin.nextInt();
            b=cin.nextInt();
            d=cin.nextInt();
            e=cin.nextInt();
            a--;b--;d--;
            depa[a][b][d]=e;
            System.out.println("Desea otra operacion? indique true 0 false como indicadores booleanos");
            c=cin.nextBoolean();
        }
    }

    int[][] total(){
        int ans[][]=new int[20][4];

        for(int i=0;i<20;i++){
            for(int j=0;j<4;j++){
                for(int k=0;k<5;k++){
                    if(depa[i][j][k]==1){
                        ans[i][j]++;
                    }
                }
            }
        }
        return ans;
    }

}
