package cola;

import java.util.LinkedList;
import java.util.Queue;

public class Cola<T> {
    private Queue<T> colaInterna = new LinkedList<>();

    
    public void encolar(T element) { if (element != null) colaInterna.add(element); }
    public T frente() { return colaInterna.peek(); } 
    public void desencolar() { colaInterna.poll(); } 

    
    public boolean add(T element) { return colaInterna.add(element); } 
    public T poll() { return colaInterna.poll(); } 
    public boolean isEmpty() { return colaInterna.isEmpty(); }

    @Override
    public String toString() {
        return colaInterna.toString();
    }
}