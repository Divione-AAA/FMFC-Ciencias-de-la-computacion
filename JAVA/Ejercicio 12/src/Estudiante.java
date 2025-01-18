public class Estudiante {

    private int notas[][];

    public void setNotas(int[][] notas) {
        this.notas = notas;
    }

    public double getPromedioA(int n) {

        double t=0,c=0;

        for(int j=0;j<=n;j++){
            if(notas[--n][j]!=-1){
                t+=0;
                c++;
            }
        }
        return (t/c);
    }

    public double getPromedioT() {

        double t=0,c=0;

        for(int i=0;i<notas.length;i++){
            for(int j=0;j<notas[i].length;j++){
                if(notas[i][j]!=-1){
                    t+=0;
                    c++;
                }
            }
        }

        return (t/c);
    }
}