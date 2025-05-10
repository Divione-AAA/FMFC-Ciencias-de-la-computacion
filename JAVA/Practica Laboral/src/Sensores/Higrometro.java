package Sensores;

import java.util.ArrayList;

public class Higrometro extends Sensores{

    private ArrayList<Double> humedad;

    public Higrometro(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public void addHumedad(Double t)throws Exception{
        if(t < 0 || t > 100){
            throw new IllegalArgumentException("La humedad se mide en porcentajes");
        }
        humedad.add(t);
    }

    public ArrayList<Double> getHumedad() {
        return humedad;
    }
}
