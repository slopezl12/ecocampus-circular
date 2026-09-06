package co.eia.ecocampus.personas;

import java.time.LocalDate;

public class RegistroEcoPuntos {
    private final String concepto;
    private final int puntos;
    private final LocalDate fecha;
    private final Persona persona;

    public RegistroEcoPuntos(String concepto, int puntos, LocalDate fecha, Persona persona) {
        if (puntos < 0) {
            throw new IllegalArgumentException("Los eco-puntos no pueden ser negativos");
        }
        this.concepto = concepto;
        this.puntos = puntos;
        this.fecha = fecha;
        this.persona = persona;
    }

    public String getConcepto() { return concepto; }
    public int getPuntos() { return puntos; }
    public LocalDate getFecha() { return fecha; }
    public Persona getPersona() { return persona; }
}
