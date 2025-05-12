package UI.GestionValores;

import java.util.Date;

public class ValorSensor {
    private final Date fecha;
    private final double valor;

    public ValorSensor(Date fecha, double valor) {
        this.fecha = fecha;
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getValor() {
        return valor;
    }
}
