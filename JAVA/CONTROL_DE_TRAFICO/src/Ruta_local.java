import java.io.Serializable;

public class Ruta_local  extends Ruta{

    private static final long serialVersionUID=1L;

    private int paradasIntermedias;
    private int omnibusAsignados;
    private double frecuencia;
    private int distancia;
    private int numeroIdentificacion;

    public Ruta_local(int p, int o, int d, int i){

        super(i);
        this.paradasIntermedias=p;
        this.omnibusAsignados=o;
        this.distancia=d;

        frecuenciaEstimada();
    }

   @Override
    public int getNumeroIdentificacion(){
        return numeroIdentificacion;
    }

    @Override
    public void frecuenciaEstimada(){
        this.frecuencia=(double)((distancia/30)+(paradasIntermedias/12)+(1/6)/omnibusAsignados);
    }
}
