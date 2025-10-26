package pila;

import java.util.Stack;
import java.util.NoSuchElementException;

public class Pila<T> {
    private Stack<T> pilaInterna = new Stack<>();

    // Métodos requeridos por ServicioDatos
    public void apilar(T valor) { pilaInterna.push(valor); }
    public T cima() { try { return pilaInterna.peek(); } catch (NoSuchElementException e) { return null; } }
    public void retirar() { try { pilaInterna.pop(); } catch (NoSuchElementException e) { /* Ignorar */ } }

    // Métodos requeridos por Libreria/AppTest (push/pop)
    public T push(T item) { return pilaInterna.push(item); } 
    public T pop() throws NoSuchElementException { return pilaInterna.pop(); } 
    
    public boolean isEmpty() { return pilaInterna.isEmpty(); }
}