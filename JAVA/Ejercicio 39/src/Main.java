public class Main{
    public static void main(String[] args)  {
        ArbolAdyacencia<Integer> arbol = new ArbolAdyacencia<>(1);
        try {
        arbol.insertaHijo(1, 2);
        arbol.insertaHijo(1, 3);
        arbol.insertaHermano(2, 4);
        System.out.println(arbol.primerHijo(1));
        //System.out.println(arbol.primerHijo(2));
        System.out.println(arbol.siguienHijo(2));
        //System.out.println(arbol.primerHijo(3));
        System.out.println(arbol.siguienHijo(3));
        //System.out.println(arbol.primerHijo(4));
        System.out.println(arbol.siguienHijo(4));
        arbol.eliminar(1);
        System.out.println(arbol.raiz());
        //System.out.println(arbol.padre(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}