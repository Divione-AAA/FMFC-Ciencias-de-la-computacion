/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ConjuntoDinamicoABB;

import java.util.LinkedList;

/**
 *
 * @author leidysc
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception{
                //Ejemplo #1
		ConjuntoDinamicoABB<Integer> arbol=new ConjuntoDinamicoABB<Integer>();
		arbol.insertar(7);
		arbol.insertar(4);
		arbol.insertar(1);
		arbol.insertar(3);
		arbol.insertar(2);
		arbol.insertar(11);
		arbol.insertar(8);
                arbol.insertar(16);
                arbol.insertar(0);
                arbol.eliminar(0);
                arbol.postOrden();
                System.out.println();
                System.out.println();
                arbol.preOrden();
                System.out.println();
                arbol.inOrden();
                System.out.println();
                System.out.println(arbol.buscarMax()); //es 16
                System.out.println(arbol.buscarMin());  //es 1
                System.out.println(arbol.buscarIterativo(7));
                System.out.println(arbol.buscar(3));
                System.out.println(arbol.altura(arbol.raiz));
                arbol.eliminar(7); //esta es la raiz
                arbol.postOrden();
                System.out.println();
                System.out.println(arbol.raiz()); //ahora la raiz es 4 pq eliminamos el 7 anteriormente
                System.out.println(arbol.altura(arbol.raiz));//altura del nodo raiz que es 3
                arbol.eliminarMaxIterativo(); //elimina el 16
                System.out.println(arbol.buscarMax());
                arbol.postOrden();
                System.out.println();
                arbol.eliminarMin();  //elimina el 1
                arbol.postOrden();
//               

                
    }

}
