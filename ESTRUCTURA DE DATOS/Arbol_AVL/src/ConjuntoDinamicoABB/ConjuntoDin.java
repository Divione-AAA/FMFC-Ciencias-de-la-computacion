/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ConjuntoDinamicoABB;
import excepciones.*;
import Exceptions.*;
/**
 *
 * @author leidysc
 */
public interface ConjuntoDin<E>  {
     void insertar(E x)throws ElementoDuplicado;
     void eliminar(E x)throws NodoNoEncontrado, ConjuntoVacio;
     void eliminarMin()throws ConjuntoVacio;
     void eliminarMax() throws ConjuntoVacio;
     E buscarMax()throws ConjuntoVacio;
     E buscarMin()throws  ConjuntoVacio;
     boolean buscar (E x) throws  ConjuntoVacio;
     boolean esVacio();
     void vaciar();
}
