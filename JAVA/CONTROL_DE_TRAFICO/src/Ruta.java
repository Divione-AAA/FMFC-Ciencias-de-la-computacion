import java.io.Serializable;

public class Ruta implements Serializable {

    private static final long serialVersionUID=1L;

    private int numeroIdentificacion;

    public Ruta(int n){
        this.numeroIdentificacion=n;
    }

    void frecuenciaEstimada(){
    };

    public int getNumeroIdentificacion(){
        return numeroIdentificacion;
    };
}
