package Sensores;

import java.util.ArrayList;

public class Termometro extends Sensores{

    private ArrayList<Double> temperaturas;

    public Termometro(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public void addTemperatura(double temp){
        temperaturas.add(temp);
    }
    public ArrayList<Double> getTemperatura(){
        return temperaturas;
    }
}
