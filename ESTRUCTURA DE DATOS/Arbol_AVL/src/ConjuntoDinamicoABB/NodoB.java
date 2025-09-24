package ConjuntoDinamicoABB;



public class NodoB <E> {
     private E llave;  //es el equivalente al ¨elemento¨ que habiamos definido anteriormente
     private NodoB <E> izquierdo;
     private NodoB <E> derecho;
   

    // Constructors
    public NodoB( E nuevaLlave,NodoB<E> izq, NodoB<E> der) {
    	llave=nuevaLlave;
        izquierdo=izq;
    	derecho=der;
   }

    public NodoB(NodoB<E> n) {
    	llave=n.llave;
        izquierdo=n.izquierdo;
    	derecho=n.derecho;
    }

    public NodoB(E nuevaLlave) {
    	this( nuevaLlave,null, null);
    }

    public NodoB<E> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoB<E> derecho) {
        this.derecho = derecho;
    }

    public NodoB<E> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoB<E> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public E getLlave() {
        return llave;
    }

    public void setLlave(E llave) {
        this.llave = llave;
    }
   

}
    

