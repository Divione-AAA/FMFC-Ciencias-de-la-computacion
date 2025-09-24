package ConjuntoDinamicoABB;

import Exceptions.*;
import excepciones.*;

public class ConjuntoDinamicoABB<E extends Comparable> implements ConjuntoDin<E> {

    public NodoB<E> raiz;

    public ConjuntoDinamicoABB(E llave, NodoB<E> izq, NodoB<E> der) {
        raiz = new NodoB<E>(llave, izq, der);
    }

    public ConjuntoDinamicoABB(E llave) {
        raiz = new NodoB<E>(llave);
    }

    public ConjuntoDinamicoABB() {
        raiz = null;
    }

    public ConjuntoDinamicoABB(NodoB<E> r) {
        raiz = r;
    }

    public E raiz() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            return raiz.getLlave();
        }
    }

    public void vaciar() {
        raiz = null;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean buscar(E x) throws ConjuntoVacio { //buscar recursivo
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            return buscarR(raiz, x);
        }
    }

    private boolean buscarR(NodoB<E> t, E x) {
        if (t == null) {
            return false;
        }
        if (x.compareTo(t.getLlave()) == 0) {
            return true;
        } else if (x.compareTo(t.getLlave()) < 0) {
            return buscarR(t.getIzquierdo(), x);
        } else {
            return buscarR(t.getDerecho(), x);
        }
    }

    public boolean buscarIterativo(E x) throws ConjuntoVacio {
        NodoB<E> t = raiz;
        if (t == null) {
            throw new ConjuntoVacio();
        } else {
            while (t != null) {
                if (x.compareTo(t.getLlave()) == 0) {
                    return true;
                } else if (x.compareTo(t.getLlave()) < 0) {
                    t = t.getIzquierdo();
                } else {
                    t = t.getDerecho();
                }
            }
        }
        return false;
    }

    public NodoB<E> buscar2(E x) throws ConjuntoVacio {
        NodoB<E> t = raiz;
        if (t == null) {
            throw new ConjuntoVacio();
        } else {
            while (t != null) {
                if (x.compareTo(t.getLlave()) == 0) {
                    return t;
                } else if (x.compareTo(t.getLlave()) < 0) {
                    t = t.getIzquierdo();
                } else {
                    t = t.getDerecho();
                }
            }
        }
        return null;

    }

    public void insertar(E x) throws ElementoDuplicado { //insertar iterativo 
        NodoB<E> v = raiz;
        NodoB<E> p = null;
        if (v == null) {
            raiz = new NodoB<E>(x, null, null);
        } else {
            while (v != null) {
                if (v.getLlave().compareTo(x) == 0) {
                    throw new ElementoDuplicado("");
                }
                p = v;
                if (v.getLlave().compareTo(x) < 0) {
                    v = v.getDerecho();
                } else {
                    v = v.getIzquierdo();
                }
            }
            NodoB<E> newnodo = new NodoB<E>(x, null, null);
            if (p.getLlave().compareTo(x) < 0) {
                p.setDerecho(newnodo);
            } else {
                p.setIzquierdo(newnodo);
            }
        }
    }

    public void insertarRec(E x) throws ElementoDuplicado { //insertar recursivo
        raiz = insertarRec(raiz, x);
    }

    private NodoB<E> insertarRec(NodoB<E> t, E x) throws ElementoDuplicado {
        if (t == null) {
            t = new NodoB<E>(x);
        } else {
            if (x.compareTo(t.getLlave()) < 0) {
                t.setIzquierdo(insertarRec(t.getIzquierdo(), x));
            } else if (x.compareTo(t.getLlave()) > 0) {
                t.setDerecho(insertarRec(t.getDerecho(), x));
            } else //x.compareTo(t.llave)==0
            {
                throw new ElementoDuplicado("");
            }
        }
        return t;
    }

    public void eliminar(E x) throws NodoNoEncontrado, ConjuntoVacio {//Recursivo
        if (raiz == null) {
            throw new ConjuntoVacio();
        }
        raiz = eliminar(raiz, x);
    }

    private NodoB<E> eliminar(NodoB<E> t, E x) throws NodoNoEncontrado {
        if (t == null) {
            throw new NodoNoEncontrado();
        } else {
            if (x.compareTo(t.getLlave()) < 0) //si el que quiero eliminar esta para la izquierda me muevo para la izquierda
            {
                t.setIzquierdo(eliminar(t.getIzquierdo(), x));
            } else if (x.compareTo(t.getLlave()) > 0) //sino me muevo para la derecha
            {
                t.setDerecho(eliminar(t.getDerecho(), x));
            } else {//x.compareTo(t.llave)==0, sino lo encontró
                if (t.getIzquierdo() != null && t.getDerecho() != null) { // caso 3, tiene los dos hijos, voy  a aplicar el criterio del predecesor
                    E predecesor = buscarMax(t.getIzquierdo());  //obtengo la llave del predecesor para lo cual uso el metodo buscarMax en el subarbol izquierdo
                    t.setLlave(predecesor);                 //copio la llave del predecesor para t y ahora tengo que eliminar el predecesor porque esta duplicado en el arbol
                    t.setIzquierdo(eliminar(t.getIzquierdo(), predecesor));  //mando a eliminar el predecesor en el subarbol izquierdo
                } else// caso 2 o caso 1, tengo un solo hijo o soy una hoja
                if (t.getIzquierdo() != null) //si el hijo es el izquierdo
                {
                    t = t.getIzquierdo();          //entonces le asigno a t su hijo izquierdo
                } else {
                    t = t.getDerecho();   //sino el que existe es el derecho o su derecho es null y de cualquier forma le asigo entonces a t su derecho
                }
            }
        }
        return t;
    }

    public void eliminarIterativo(E x) throws NodoNoEncontrado, ConjuntoVacio {//Iterativo
        if (raiz == null) {
            throw new ConjuntoVacio();
        }
        NodoB<E> v = null;
        NodoB<E> p = null;
        NodoB<E> t = raiz;
        while (t != null)//con este ciclo estoy buscando al que quiero eliminar
        {
            if (x.compareTo(t.getLlave()) == 0) {
                v = t;
                break;
            } else if (x.compareTo(t.getLlave()) < 0) {
                p = t;
                t = t.getIzquierdo();
            } else {
                p = t;
                t = t.getDerecho();
            }
        }
        if (v == null) //en este caso el nodo no está en el árbol por tanto elevo la excepción
        {
            throw new NodoNoEncontrado();
        } else {//  tengo en v al que quiero eliminar y en p tengo a su padre
            if (v.getIzquierdo() != null && v.getDerecho() != null) { //caso 3,tiene dos hijos, voy a aplicar el criterio del predecesor
                NodoB<E> predecesor = v.getIzquierdo();
                NodoB<E> p2 = null;
                while (predecesor.getDerecho() != null) { //con este ciclo estoy buscarndo a su predecesor y a la vez me voy a quedar con su padre tambien en la variable p2
                    p2 = predecesor;
                    predecesor = predecesor.getDerecho();
                }
                v.setLlave(predecesor.getLlave());//copio la llave del predecesor para v y ahora necesito eliminar al predecesor porque los elementos estan duplicados en el arbol
                if (p2 == null) //si p2 es null estoy en el caso especifico donde el predecesor no tiene subarbol derecho y su padre es v.
                {
                    v.setIzquierdo(predecesor.getIzquierdo());  //entonces el izquierdo de v recibe al hijo izquierdo del predecesor
                } else {
                    p2.setDerecho(predecesor.getIzquierdo());  //sino pongo al izquierdo del predecesor como el hijo derecho de su padre que es p2.
                }
            } else if (v.getIzquierdo() == null && v.getDerecho() == null) //este es el caso 1, donde v es una hoja porque no tiene a ningun hijo
            {
                if (p == null) //si su padres es null es porque quiero eliminar a la raiz y como no hay hijos el arbol queda vacio.
                {
                    raiz = null;
                } else {
                    if (p.getLlave().compareTo(v.getLlave()) > 0) //sino si el padre es mayor que v, entonces v es su hijo izquierdo, por tanto pongo al izquierdo del padre en null.
                    {
                        p.setIzquierdo(null);
                    } else {
                        p.setDerecho(null);
                    }
                }  //sino es su hijo derecho porngo al derecho del padre en null.
            } else {  //este es el caso 2, existe uno de los dos hijos.
                NodoB<E> var = v.getIzquierdo() != null ? v.getIzquierdo() : v.getDerecho(); //aqui guarde en la variable "var" el hijo que existe, ya sea el derecho o el izquierdo
                if (p == null) //si p es null, entonces el padre no existe, eso quiere decir que queiro eliminar a la raiz entonces la raiz recibe al hijo que tiene.
                {
                    raiz = var;
                } else { //sino
                    if (p.getLlave().compareTo(v.getLlave()) > 0) //si el padre es mayor que v, entonces v es su hijo izquierdo, por tanto pongo como izquierdo del padre al hijo que tiene v.
                    {
                        p.setIzquierdo(var);
                    } else {
                        p.setDerecho(var);
                    }
                }
            } //sino v es hijo derecho, por tanto pongo como derecho del padre al hijo que tiene v
        }
    }

    public E buscarMax() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            return buscarMax(raiz);
        }
    }

    private E buscarMax(NodoB<E> t) {
        if (t.getDerecho() == null) {
            return t.getLlave();
        } else {
            return buscarMax(t.getDerecho());
        }
    }

    public E buscarMaxIterativo() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            NodoB<E> tv = raiz;
            while (tv.getDerecho() != null) {
                tv = tv.getDerecho();
            }
            return tv.getLlave();
        }
    }

    public E buscarMin() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            return buscarMin(raiz);
        }
    }

    private E buscarMin(NodoB<E> t) {
        if (t.getIzquierdo() == null) {
            return t.getLlave();
        } else {
            return buscarMin(t.getIzquierdo());
        }
    }

    public void eliminarMax() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            raiz = eliminarMax(raiz);
        }
    }

    private NodoB<E> eliminarMax(NodoB<E> t) {
        if (t.getDerecho() == null) {
            t = t.getIzquierdo();
        } else {
            t.setDerecho(eliminarMax(t.getDerecho()));
        }
        return t;
    }

    public void eliminarMaxIterativo() throws ConjuntoVacio {//Iterativo
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            NodoB<E> t = raiz;
            NodoB<E> p = null;  //voy a guardar en p la referencia al padre porque solo con el padre garantizo cambiar la referencia que hay en el árbol
            while (t.getDerecho() != null) {
                p = t;
                t = t.getDerecho();
            }
            if (p != null) {
                p.setDerecho(t.getIzquierdo());
            } else //si p es null entonces quiero eliminar la raiz y tengo que cambiar su referencia
            {
                raiz = t.getIzquierdo();
            }
        }
    }

    public void eliminarMin() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            raiz = eliminarMin(raiz);
        }
    }

    private NodoB<E> eliminarMin(NodoB<E> t) {
        if (t.getIzquierdo() == null) {
            t = t.getDerecho();
        } else {
            t.setIzquierdo(eliminarMin(t.getIzquierdo()));
        }
        return t;
    }

    public void eliminarMinIterativo() throws ConjuntoVacio {
        if (raiz == null) {
            throw new ConjuntoVacio();
        } else {
            NodoB<E> t = raiz;
            NodoB<E> p = null;  //voy a guardar en p la referencia al padre porque solo con el padre garantizo cambiar la referencia que hay en el árbol
            while (t.getIzquierdo() != null) {
                p = t;
                t = t.getIzquierdo();
            }
            if (p != null) {
                p.setIzquierdo(t.getDerecho());
            } else //si p es null entonces quiero eliminar la raiz y tengo que cambiar su referencia
            {
                raiz = t.getDerecho();
            }
        }
    }

    public void preOrden() throws ArbolVacioException {
        if (raiz == null) {
            throw new ArbolVacioException();
        } else {
            preOrdenR(raiz);
        }
    }

    private void preOrdenR(NodoB<E> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getLlave());
            System.out.print(',');
            preOrdenR(nodo.getIzquierdo());
            preOrdenR(nodo.getDerecho());
        }

    }

    public void inOrden() throws ArbolVacioException {
        if (raiz == null) {
            throw new ArbolVacioException();
        } else {
            inOrdenR(raiz);
        }
    }

    private void inOrdenR(NodoB<E> nodo) {
        if (nodo != null) {
            inOrdenR(nodo.getIzquierdo());
            System.out.print(nodo.getLlave());
            System.out.print(',');
            inOrdenR(nodo.getDerecho());
        }
    }

    public void postOrden() throws ArbolVacioException {
        if (raiz == null) {
            throw new ArbolVacioException();
        } else {
            postOrdenR(raiz);
        }
    }

    private void postOrdenR(NodoB<E> nodo) {
        if (nodo != null) {
            postOrdenR(nodo.getIzquierdo());
            postOrdenR(nodo.getDerecho());
            System.out.print(nodo.getLlave());
            System.out.print(',');
        }
    }

    public int altura(NodoB<E> a) { //método que va a heredar la clase hija para calcular la altura en el método balancear y en el método factor de equilibrio
        return alturaR(a);
    }

    private int alturaR(NodoB<E> a) {
        if (a == null) {
            return -1;
        }
        return 1 + Math.max(alturaR(a.getIzquierdo()), alturaR(a.getDerecho()));
    }

}
