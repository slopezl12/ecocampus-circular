package co.eia.ecocampus.campanas;

import co.eia.ecocampus.personas.Persona;
import java.time.LocalDate;

// Clase asociativa: no es una entidad del dominio, es el vinculo Persona <-> CampanaAmbiental
public class Participacion {
    private final Persona persona;
    private final CampanaAmbiental campana;
    private final LocalDate fechaInscripcion;

    public Participacion(Persona persona, CampanaAmbiental campana, LocalDate fechaInscripcion) {
        this.persona = persona;
        this.campana = campana;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Persona getPersona() { return persona; }
    public CampanaAmbiental getCampana() { return campana; }
    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
}
