package utilerias;

import java.time.LocalDate;

public class Fecha {
    private LocalDate fecha;

    public Fecha() {
        this.fecha = LocalDate.now(); 
    }
    
    public Fecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() { return fecha; }
    
    @Override
    public String toString() {
        return fecha.toString();
    }
}