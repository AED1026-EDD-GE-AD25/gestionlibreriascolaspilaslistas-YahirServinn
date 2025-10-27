package pila;

import java.util.Stack;
import java.util.NoSuchElementException;

public class Pila<T> {
    private Stack<T> pilaInterna = new Stack<>();

    
    public void apilar(T valor) { pilaInterna.push(valor); }
    public T cima() { try { return pilaInterna.peek(); } catch (NoSuchElementException e) { return null; } }
    public void retirar() { try { pilaInterna.pop(); } catch (NoSuchElementException e) {  } }

    
    public T push(T item) { return pilaInterna.push(item); } 
    
    
    public T pop() { 
        try {
            return pilaInterna.pop(); 
        } catch (java.util.EmptyStackException e) {
            
            return null; 
        }
    } 
    
    public boolean isEmpty() { return pilaInterna.isEmpty(); }
}