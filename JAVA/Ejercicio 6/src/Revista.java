public class Revista {
    private int ISSN,cantidad_accesos,cantidad_enlaces;

    public void setCantidad_accesos(int cantidad_accesos) {
        this.cantidad_accesos = cantidad_accesos;
    }

    public void setCantidad_enlaces(int cantidad_enlaces) {
        this.cantidad_enlaces = cantidad_enlaces;
    }

    public void setISSN(int ISSN) {
        this.ISSN = ISSN;
    }

    public int factorDeImpacto(){
        return (cantidad_enlaces/cantidad_accesos);
    }

    public char claseificacion(){
        if(cantidad_accesos>200){
            return 'E';
        }else if(cantidad_accesos<200 && cantidad_accesos>100){
            return 'R';
        }else if(cantidad_accesos<100){
            return 'A';
        }
        return ' ';
    }
}
