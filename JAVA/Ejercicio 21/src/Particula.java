public class Particula implements Onda,Cropusculo{

    private double long_onda,masa,velocidad;

    public Particula(double long_onda,double masa, double velocidad){
        this.long_onda = long_onda;
        this.masa = masa;
        this.velocidad = velocidad;
    }

    @Override
    public double long_onda() {
        return 0;
    }

    @Override
    public double masa() {
        return 0;
    }

    @Override
    public double velocidad() {
        return 0;
    }
}
