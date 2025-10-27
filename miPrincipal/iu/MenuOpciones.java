package miPrincipal.iu;

import miPrincipal.modelo.Libro;
import miPrincipal.modelo.Pedido;
import miPrincipal.modelo.Libreria;
import utilerias.Fecha;
import listaDoble.PosicionIlegalException;
import java.util.Scanner;

public class MenuOpciones{
    static Scanner scanner = new Scanner(System.in);
    static private Libreria libreria = new Libreria();

    public static void agregarLibro(){
        System.out.print("Ingrese el título del libro:");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro:");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro:");
        String isbn = scanner.nextLine();
        
        Libro nuevoLibro = libreria.crearLibro(titulo, autor, isbn);
        libreria.agregarLibro(nuevoLibro);
        System.out.println(" Libro '" + titulo + "' agregado a la lista de prestados.");
    }
    
    public static void mostrarLibros() {
        System.out.println("\n--- Libros Prestados ---");
        if (libreria.obtenerLibros().esVacia()) {
            System.out.println("La lista de libros prestados está vacía.");
        } else {
            System.out.println(libreria.obtenerLibros());
        }
        System.out.println("------------------------");
    }

    public static void agregarLibroCola(){
        System.out.print("Título para reserva:");
        String titulo = scanner.nextLine();
        System.out.print("Autor para reserva:");
        String autor = scanner.nextLine();
        System.out.print("ISBN para reserva:");
        String isbn = scanner.nextLine();
        
        Libro libroReserva = libreria.crearLibro(titulo, autor, isbn);
        if (libreria.agregarLibroCola(libroReserva)) {
            System.out.println(" Libro '" + titulo + "' reservado y agregado a la cola.");
        } else {
            System.out.println(" Error al agregar la reserva.");
        }
    }

    public static Libro obtenerLibroCola(){
        System.out.println("\n--- Siguiente Libro para Préstamo ---");
        Libro libro = libreria.obtenerLibroCola();
        if (libro != null) {
            System.out.println("Libro obtenido de la cola: " + libro.getTitulo());
        } else {
            System.out.println("La cola de reservas está vacía.");
        }
        System.out.println("------------------------------------");
        return libro;
    }

    public static void mostrarReservaLibros(){
        System.out.println("\n--- Cola de Libros en Reserva ---");
        if (libreria.mostrarReservaLibros().isEmpty()) {
            System.out.println("La cola de reservas está vacía.");
        } else {
            System.out.println(libreria.mostrarReservaLibros()); 
        }
        System.out.println("---------------------------------");
    }

    public static void crearPedido(){
        System.out.print("Título del libro para el pedido:");
        String tituloPedido = scanner.nextLine();
        System.out.print("Autor del libro para el pedido:");
        String autorPedido = scanner.nextLine();
        System.out.print("ISBN del libro para el pedido:");
        String isbnPedido = scanner.nextLine();
        
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        
        if (libroPedido != null){
            Pedido pedido = libreria.crearPedido(libroPedido, new Fecha());
            if (pedido !=null)
                System.out.println(" Pedido creado exitosamente: "+pedido);
            else
                System.out.println(" No fue posible crear el pedido.");
        } else {
            System.out.println(" Error: no fue posible crear el Libro.");
        }
    }

    public static void devolverLibro() throws PosicionIlegalException{
        System.out.print("Ingrese el ISBN del libro a devolver:");
        String isbn = scanner.nextLine();
        
        Libro libroADevolver = libreria.buscarLibro(isbn);
        if (libroADevolver != null) {
            if (libreria.devolverLibro(libroADevolver)) {
                System.out.println(" Libro devuelto y removido de prestados: " + libroADevolver.getTitulo());
            } else {
                System.out.println(" Error: El libro fue encontrado pero no se pudo remover.");
            }
        } else {
            System.out.println(" Error: No se encontró un libro prestado con ese ISBN.");
        }
    }

    public static Libro eliminarUltimoLibro() throws PosicionIlegalException {
        System.out.println("\n--- Corrección Préstamo (Eliminar Último) ---");
        Libro libroEliminado = libreria.eliminarUltimoLibro();
        if (libroEliminado != null) {
            System.out.println(" Libro eliminado de prestados y enviado a la pila: " + libroEliminado.getTitulo());
        } else {
            System.out.println("La lista de libros prestados está vacía. ⚠️");
        }
        System.out.println("-------------------------------------------");
        return libroEliminado;
    }

    public static void deshacerEliminarLibro(){
        System.out.println("\n--- Restaura Eliminación ---");
        Libro libroRestaurado = libreria.deshacerEliminarLibro();
        if (libroRestaurado != null) {
            System.out.println(" Libro restaurado de la pila a la lista de prestados: " + libroRestaurado.getTitulo());
        } else {
            System.out.println("La pila de libros eliminados está vacía. ⚠️");
        }
        System.out.println("----------------------------");
    }
}