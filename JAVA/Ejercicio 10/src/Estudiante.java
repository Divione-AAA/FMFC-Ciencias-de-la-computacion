public class Estudiante {
    private int notas[]=new int[5];
    private double prom;
    private int nl;

    public void setNotas(int[] notas) {
        this.notas = notas;
    }

    public void setProm(double prom){
        this.prom=prom;
    }

    public void setNl(int nl) {
        this.nl = nl;
    }

    public double getProm() {
        return prom;
    }

    public boolean pase(){
        return notas[1]>=4 ? false : true;
    }
}
