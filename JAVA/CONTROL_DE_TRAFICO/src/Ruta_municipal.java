import java.io.Serializable;

public class Ruta_municipal extends Ruta{

    private static final long serialVersionUID=1L;

    private double frecuencia;
    private String matricula;
    private String mantenimiento;
    private int distancia;

    public Ruta_municipal(int d, int i, String c, String m){

        super(i);
        this.distancia=d;
        this.matricula=c;
        this.mantenimiento=m;
        frecuenciaEstimada();
    }

    @Override
    public void frecuenciaEstimada(){
        this.frecuencia=(double)((distancia/60)+(1/6));
    }
}
