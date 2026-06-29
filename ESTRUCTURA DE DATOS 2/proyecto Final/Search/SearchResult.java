package Search;

import java.util.ArrayList;

/**
 * Guarda resultados de busqueda.
 *
 * Se almacena:
 *
 * posiciones encontradas
 * numero comparaciones
 * algoritmo usado
 */

public class SearchResult {

    private ArrayList<Integer> posiciones;

    private int comparaciones;

    private String algoritmo;

    public SearchResult(
            String algoritmo
    ){

        this.algoritmo=
                algoritmo;

        posiciones=
                new ArrayList<>();

        comparaciones=
                0;

    }

    public void agregarPosicion(
            int pos
    ){

        posiciones.add(pos);

    }

    public void agregarComparacion(){

        comparaciones++;

    }

    public ArrayList<Integer>
    getPosiciones(){

        return posiciones;

    }

    public int getComparaciones(){

        return comparaciones;

    }

    public String getAlgoritmo(){

        return algoritmo;

    }

    public int totalCoincidencias(){

        return posiciones.size();

    }

}