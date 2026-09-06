package co.eia.ecocampus.campanas;

import co.eia.ecocampus.comunes.Cerrable;
import co.eia.ecocampus.comunes.Identificable;
import co.eia.ecocampus.comunes.EstadoCampana;
import co.eia.ecocampus.personas.Persona;
import java.time.LocalDate;

public class CampanaAmbiental implements Cerrable, Identificable {
    private final String id;
    private final String nombre;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final int cupo;
    private EstadoCampana estado;
    private final Actividad[] actividades;
    private int numActividades;
    private final Participacion[] participaciones;
    private int numParticipaciones;

    public CampanaAmbiental(String id, String nombre, LocalDate fechaInicio, LocalDate fechaFin, int cupo, int capacidadActividades) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cupo = cupo;
        this.estado = EstadoCampana.ABIERTA;
        this.actividades = new Actividad[capacidadActividades];
        this.numActividades = 0;
        this.participaciones = new Participacion[cupo];
        this.numParticipaciones = 0;
    }

    @Override
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public int getCupo() { return cupo; }

    // Composicion: las actividades dependen del ciclo de vida de la campana (requisito 8)
    public void agregarActividad(Actividad actividad) {
        if (estaCerrado()) {
            throw new IllegalStateException("Una campana cerrada no admite nuevas actividades");
        }
        if (numActividades >= actividades.length) {
            throw new IllegalStateException("Capacidad de actividades agotada en la campana " + id);
        }
        actividades[numActividades++] = actividad;
    }

    public Actividad[] getActividades() {
        Actividad[] copia = new Actividad[numActividades];
        System.arraycopy(actividades, 0, copia, 0, numActividades);
        return copia;
    }

    // Regla de negocio 5: una persona no puede inscribirse dos veces en la misma campana
    public void inscribir(Persona persona, LocalDate fechaInscripcion) {
        if (estaCerrado()) {
            throw new IllegalStateException("Una campana cerrada no admite nuevas inscripciones");
        }
        for (int i = 0; i < numParticipaciones; i++) {
            if (participaciones[i].getPersona().getId().equals(persona.getId())) {
                throw new IllegalStateException(persona.getNombre() + " ya esta inscrito en esta campana");
            }
        }
        if (numParticipaciones >= participaciones.length) {
            throw new IllegalStateException("La campana " + nombre + " alcanzo su cupo");
        }
        participaciones[numParticipaciones++] = new Participacion(persona, this, fechaInscripcion);
    }

    public Participacion[] getParticipaciones() {
        Participacion[] copia = new Participacion[numParticipaciones];
        System.arraycopy(participaciones, 0, copia, 0, numParticipaciones);
        return copia;
    }

    @Override
    public void cerrar() { this.estado = EstadoCampana.CERRADA; }

    @Override
    public boolean estaCerrado() { return estado == EstadoCampana.CERRADA; }
}
