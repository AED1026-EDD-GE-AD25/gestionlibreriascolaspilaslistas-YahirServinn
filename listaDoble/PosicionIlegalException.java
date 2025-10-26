package listaDoble;

public class PosicionIlegalException extends Exception {
    public PosicionIlegalException() {
        super("Posicion Ilegal en la lista");
    }
    public PosicionIlegalException(String mensaje) {
        super(mensaje);
    }
}
