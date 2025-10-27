package miPrincipal.modelo;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import pila.Pila;
import cola.Cola;
import utilerias.Fecha;
import miPrincipal.servicio.ServicioDatos;
import java.util.NoSuchElementException;

public class Libreria {
    private ServicioDatos dataService;
    private ListaDoble<Libro> listaLibros;
    private Cola<Libro> colaLibros;
    private Pila<Libro> pilaLibrosEliminados;
    private ListaDoble<Pedido> listaPedidos;

    public Libreria(){
        this.dataService = new ServicioDatos();
        
        this.listaLibros = dataService.getLista(); 
        this.colaLibros = dataService.getCola(); 
        this.pilaLibrosEliminados = dataService.getPila();
        this.listaPedidos = new ListaDoble<>();
    }
    
    public void agregarLibro(Libro libro){
        if (libro != null) listaLibros.agregar(libro);
    }
    
    
    public ListaDoble<Libro> obtenerLibros(){
        return listaLibros; 
    }
    
    public boolean agregarLibroCola(Libro libro){
        if (libro != null) return colaLibros.add(libro); 
        return false;
    }

    
    public Libro obtenerLibroCola(){
        return colaLibros.poll();
    }
    
    public Cola<Libro> mostrarReservaLibros() {
        return colaLibros;
    }
    
    
    public Pedido crearPedido(Libro libro, Fecha fecha){
        if (libro != null && fecha != null) {
            Pedido pedido = new Pedido(libro, fecha);
            listaPedidos.agregar(pedido);
            return pedido;
        }
        return null;
    }
    
    
    public boolean devolverLibro(Libro libro) throws PosicionIlegalException {
        if (libro != null) return listaLibros.remover(libro) != -1;
        return false;
    }

    
    public Libro eliminarUltimoLibro() throws PosicionIlegalException {
        if (listaLibros.esVacia()) return null;
        Libro libroEliminado = listaLibros.removeLast(); 
        pilaLibrosEliminados.push(libroEliminado); 
        return libroEliminado;
    }

    
    public Libro deshacerEliminarLibro(){
        try {
            Libro libroRestaurado = pilaLibrosEliminados.pop(); 
            listaLibros.agregar(libroRestaurado); 
            return libroRestaurado;
        } catch (NoSuchElementException e) {
            return null; 
        }
    }
    
    
    public Libro obtenerLibroPila(){
    try { 
        
        return pilaLibrosEliminados.pop(); 
    } 
    catch (NoSuchElementException e) { 
        
        
        return null; 
    }
}
    
    public Libro crearLibro(String titulo, String autor, String isbn) {
        return new Libro(titulo, autor, isbn);
    }
    
    public Libro buscarLibro(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) return libro;
        }
        return null;
    }
}