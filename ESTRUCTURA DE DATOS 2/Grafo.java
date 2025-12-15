import java.util.List;

public interface Grafo {

//manipulacion del grafo
int  insVertice(String nombre);
void insArista(String origen, String destino, float peso);
void insArista(String origen, String destino);
void elimVertice(String nombre);
void elimArista(String origen, String destino);

//operacions sobre grafos
int buscar(String nombre);
boolean esAdyacente(String origen, String destino);

//recorridos
List<String> recorridoAmplitud(String VerticeOrigen);
List<String> recorridoProfundidad(String VerticeOrig);

}
