import java.util.*;

class Nodo implements Comparable<Nodo> {
    int frecuencia;
    String caracter;   // null si es un nodo interno
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int frecuencia, String caracter) {
        this.frecuencia = frecuencia;
        this.caracter = caracter;
    }

    public Nodo(int frecuencia, Nodo izquierdo, Nodo derecho) {
        this.frecuencia = frecuencia;
        this.caracter = null; // nodo interno
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    @Override
    public int compareTo(Nodo otro) {
        return Integer.compare(this.frecuencia, otro.frecuencia);
    }

    public boolean esHoja() {
        return (izquierdo == null && derecho == null);
    }
}

public class ArbolDeHuffman {
    final private String cadena;
    final private Map<Character, Integer> mapa = new HashMap<>();
    final private PriorityQueue<Nodo> cola = new PriorityQueue<>();
    Nodo raiz;
    Map<Character, String> codigos = new HashMap<>();

    public ArbolDeHuffman(String s) {
        this.cadena = s;
    }

    public void proceso() {
        calcularFrecuencias();
        construirNodos();
        construirArbol();
        generarCodigos(raiz, "");
    }

    private void calcularFrecuencias() {
        for (char c : cadena.toCharArray()) {
            mapa.put(c, mapa.getOrDefault(c, 0) + 1);
        }
    }

    private void construirNodos() {
        for (Map.Entry<Character, Integer> i : mapa.entrySet()) {
            cola.add(new Nodo(i.getValue(), i.getKey().toString()));
        }
    }

    private void construirArbol() {
        while (cola.size() > 1) {
            Nodo n1 = cola.poll();
            Nodo n2 = cola.poll();
            Nodo padre = new Nodo(n1.frecuencia + n2.frecuencia, n1, n2);
            cola.add(padre);
        }
        raiz = cola.poll();
    }

    private void generarCodigos(Nodo nodo, String codigo){
        if (nodo == null) return;

        if (nodo.esHoja()){
            codigos.put(nodo.caracter.charAt(0), codigo);
        } else {
            generarCodigos(nodo.izquierdo, codigo + "0");
            generarCodigos(nodo.derecho, codigo + "1");
        }
    }

    public String codificar(){
        StringBuilder sb = new StringBuilder();
        for (char c : cadena.toCharArray()) {
            sb.append(codigos.get(c));
        }
        return sb.toString();
    }

    public void imprimirCodigos() {
        System.out.println("Códigos Huffman:");
        for (Map.Entry<Character, String> entry : codigos.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

    // Método principal de prueba
    public static void main(String[] args) {
        String mensaje = "ABRACADABRA";
        ArbolDeHuffman arbol = new ArbolDeHuffman(mensaje);
        arbol.proceso();
        arbol.imprimirCodigos();

        String codificado = arbol.codificar();
        System.out.println("\nMensaje original: " + mensaje);
        System.out.println("Codificado: " + codificado);
    }
}
