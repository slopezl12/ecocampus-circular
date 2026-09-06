package co.eia.ecocampus.campanas;

import java.time.LocalDate;

public class Actividad {
    private final String id;
    private final String nombre;
    private final LocalDate fecha;
    private final int cupo;

    public Actividad(String id, String nombre, LocalDate fecha, int cupo) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.cupo = cupo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }
    public int getCupo() { return cupo; }
}
