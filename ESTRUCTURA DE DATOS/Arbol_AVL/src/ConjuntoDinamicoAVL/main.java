/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConjuntoDinamicoAVL;

/**
 * @author leidysc
 */
public class main {

    public static void main(String[] args) throws Exception {

        ConjuntoDinamicoAVL<Integer> arbol = new ConjuntoDinamicoAVL<Integer>();
        arbol.insertar2(21);
        arbol.insertar2(10);
        arbol.insertar2(30);
        arbol.insertar2(14);
        arbol.insertar2(24);
        arbol.insertar2(41);
        arbol.insertar2(27);
        arbol.insertar2(55);
        arbol.insertar2(50);
        arbol.insertar2(49);
        arbol.insertar2(15);
        arbol.insertar2(8);
        arbol.insertar2(9);
        arbol.insertar2(7);

        arbol.preOrden();
        System.out.println("");
        arbol.postOrden();
        System.out.println("");

    }
}
