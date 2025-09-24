/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConjuntoDinamicoAVL;

import ConjuntoDinamicoABB.*;
import Exceptions.*;
import excepciones.*;
//import excepciones.*;

/**
 *
 * @author leidysc
 */
public class ConjuntoDinamicoAVL<E extends Comparable> extends ConjuntoDinamicoABB {

    public ConjuntoDinamicoAVL(E llave, NodoB<E> izq, NodoB<E> der) {
        super(llave, izq, der);
    }

    public ConjuntoDinamicoAVL() {
        super();
    }

    public ConjuntoDinamicoAVL(NodoB<E> r) {
        super(r);
    }

    public NodoB<E> simpleIzq(NodoB<E> k2) { //rotacion simple izquierda, cada vez que se ejecute se va a imprimir
        NodoB<E> k1 = k2.getDerecho();
        k2.setDerecho(k1.getIzquierdo());
        k1.setIzquierdo(k2);
        return k1;
    }

    public NodoB<E> simpleDer(NodoB<E> k2) { //rotacion simple derecha,cada vez que se ejecute se va a imprimir
        NodoB<E> k1 = k2.getIzquierdo();
        k2.setIzquierdo(k1.getDerecho());
        k1.setDerecho(k2);
        return k1;
    }

    public NodoB<E> dobleIzqDer(NodoB<E> k2) { //doble izquierda-derecha, se imprime cuando se ejecuta
        k2.setIzquierdo(simpleIzq(k2.getIzquierdo()));
        return simpleDer(k2);
    }

    public NodoB<E> dobleDerIzq(NodoB<E> k2) { //doble derecha-izquierda, se imprime cuando se ejecuta
        k2.setDerecho(simpleDer(k2.getDerecho()));
        return simpleIzq(k2);
    }

    public void balancearE(E x) throws NodoNoEncontrado {//metodo que se encarga de balancear el arbol despues que se inserta o se elimina
        raiz = balancearE(raiz, x);
    }

    private NodoB<E> balancearE(NodoB<E> t, E x) throws NodoNoEncontrado {
        if ((t == null) || (t.getIzquierdo() == null && t.getDerecho() == null)) {
            return t;
        } else if (x.compareTo(t.getLlave()) == 0) {
            return t;
        } else if (x.compareTo(t.getLlave()) < 0 && t.getIzquierdo() != null)//me muevo para la izquierda si tengo que balancear en el sub-arbol izquierdo
        {
            t.setIzquierdo(balancearE(t.getIzquierdo(), x));
        } else if (t.getDerecho() != null) {
            t.setDerecho(balancearE(t.getDerecho(), x));//me muevo para la derecha si tengo que balancear en subarbol derecho
        }	//esto esta fuera de todos los if-else
        int FE = FE(t);                          //busco el factor de equilibrio del nodo
        if (Math.abs(FE) > 1) // si el modulo del factor de equilibrio es mayor que 1, Esta desbalanceaddo
        {
            if (altura(t.getIzquierdo()) > altura(t.getDerecho())) {  //si altura de izquierdo es mayor que la de derecho 
                int FEHI = FE(t.getIzquierdo());                       //busco factor de equilibrio de izquierdo
                if (Math.signum(FEHI) == Math.signum(FE) || Math.signum(FEHI) == 0) //si los dos factores tienen el mismo signo o el factor del izquierdo es 0
                {
                    System.out.println("Simple Derecha en el nodo: " + t.getLlave().toString() + " al insertar el nodo " + x.toString());
                    t = simpleDer(t);      //se aplica rotacion simple derecha
                } else {
                    System.out.println("Doble Izq Derecha en el nodo: " + t.getLlave().toString() + " al insertar el nodo " + x.toString());
                    t = dobleIzqDer(t);   //sino es una doble izquierda derecha
                }
            } else { // sino altura de derecho es mayor
                int FEHD = FE(t.getDerecho()); //busco factor de equilibrio de derecho
                if (Math.signum(FEHD) == Math.signum(FE) || Math.signum(FEHD) == 0) //si tienen signos iguales o factor de derecho es 0
                {
                    System.out.println("Simple Izquierda en el nodo: " + t.getLlave().toString() + " al insertar el nodo " + x.toString());
                    t = simpleIzq(t);   //se aplica una simple izquierda
                } else {
                    System.out.println("Doble Derecha Izq en el nodo: " + t.getLlave().toString() + " al insertar el nodo " + x.toString());
                    t = dobleDerIzq(t);   //sino es una doble derecha izquierda
                }
            }
        }
        return t;
    }

    private int FE(NodoB<E> t) { //metodo que calcula el factor de equilibrio	   
        int altHI = -1;
        int altHD = -1;
        if (t.getIzquierdo() != null) {
            altHI = altura(t.getIzquierdo());
        }
        if (t.getDerecho() != null) {
            altHD = altura(t.getDerecho());
        }
        return altHD - altHI;   //se devuelve la altura del derecho menos la del izquierdo
    }

    public void insertar2(E x) throws NodoNoEncontrado, ElementoDuplicado {
        super.insertar(x); //insertamos igual que antes con el metodo de la clase padre
        balancearE(x);     //y despues balanceamos
    }

    public void eliminar2(E x) throws NodoNoEncontrado, ConjuntoVacio {
        super.eliminar(x);   //eliminamos igual que antes con el metodo de la clase padre
        balancearE(x);      //y despues balanceamos
    }

}
