package Sensores;

import java.util.ArrayList;

public class Puvliometro extends Sensores{

    private ArrayList<Double> lluvia;

    public Puvliometro(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public ArrayList<Double> getLluvia() {
        return lluvia;
    }

    public void addLluvia(double luvia) {
        lluvia.add(luvia);
    }
}
