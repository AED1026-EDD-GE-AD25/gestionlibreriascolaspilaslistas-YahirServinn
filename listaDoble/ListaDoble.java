package listaDoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoble<T> implements Iterable<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola; 
    private int tamanio;

    public ListaDoble(){
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    
    public int getTamanio() {
        return tamanio;
    }

    public boolean esVacia(){ 
        return cabeza == null;
    }

    
    public void agregar(T valor){
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setValor(valor);
        
        if (esVacia()){
            cabeza = nuevo;
            cola = nuevo;
        }else{
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo); 
            cola = nuevo;
        }
        tamanio++;
    }

    
    public T removeLast() throws PosicionIlegalException {
        if (esVacia()) {
            throw new PosicionIlegalException("La lista está vacía.");
        }
        T valor = cola.getValor();
        
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            Nodo<T> nuevoCola = cola.getAnterior();
            nuevoCola.setSiguiente(null);
            cola = nuevoCola;
        }
        tamanio--;
        return valor;
    }

    
    public int remover(T valor) throws PosicionIlegalException{
        Nodo<T> aux = cabeza;
        int pos = 0;
        
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                if (aux == cabeza) {
                    cabeza = aux.getSiguiente();
                    if (cabeza != null) cabeza.setAnterior(null); else cola = null;
                } else if (aux == cola) {
                    cola = aux.getAnterior();
                    cola.setSiguiente(null);
                } else {
                    aux.getAnterior().setSiguiente(aux.getSiguiente());
                    aux.getSiguiente().setAnterior(aux.getAnterior());
                }
                tamanio--;
                return pos;
            }
            aux = aux.getSiguiente();
            pos++;
        }
        return -1;
    }

    
    public boolean contiene(T valor){
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getValor().equals(valor)) { 
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }
    
    public T getValor(int pos) throws PosicionIlegalException{
        if(pos>=0 && pos<tamanio){
            Nodo<T> aux = cabeza;
            for(int i=0; i<pos; i++){
                aux = aux.getSiguiente();
            }
            return aux.getValor();
        }else{
            throw new PosicionIlegalException();
        }
    }

    @Override
    public String toString() {
        if (esVacia()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> aux = cabeza;
        while (aux != null) {
            sb.append(aux.getValor().toString());
            if (aux.getSiguiente() != null) sb.append(", ");
            aux = aux.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ListaDobleIterator();
    }
    
    private class ListaDobleIterator implements Iterator<T> {
        private Nodo<T> actual = cabeza;

        @Override
        public boolean hasNext() { return actual != null; }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T valor = actual.getValor();
            actual = actual.getSiguiente();
            return valor;
        }
    }
}