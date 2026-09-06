package co.eia.ecocampus.residuos;

import co.eia.ecocampus.comunes.Asignable;
import co.eia.ecocampus.comunes.Cerrable;
import co.eia.ecocampus.comunes.Identificable;
import co.eia.ecocampus.comunes.EstadoReporte;
import co.eia.ecocampus.comunes.Prioridad;
import co.eia.ecocampus.personas.Operador;
import co.eia.ecocampus.personas.Persona;
import java.time.LocalDate;

public class Reporte implements Cerrable, Identificable {
    private final String id;
    private final LocalDate fecha;
    private final Persona autor;
    private final PuntoEcologico punto;
    private final String descripcion;
    private final Prioridad prioridad;
    private EstadoReporte estado;
    private final boolean residuoEspecial;
    private Recoleccion recoleccionAsociada;

    public Reporte(String id, Persona autor, PuntoEcologico punto, String descripcion, Prioridad prioridad, boolean residuoEspecial) {
        // Regla de negocio 2: no se crean reportes sobre puntos inactivos
        if (!punto.estaActivo()) {
            throw new IllegalStateException("No se pueden crear reportes sobre un punto inactivo: " + punto.getId());
        }
        this.id = id;
        this.fecha = LocalDate.now();
        this.autor = autor;
        this.punto = punto;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.residuoEspecial = residuoEspecial;
        this.estado = EstadoReporte.ABIERTO;
    }

    @Override
    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public Persona getAutor() { return autor; }
    public PuntoEcologico getPunto() { return punto; }
    public String getDescripcion() { return descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public EstadoReporte getEstado() { return estado; }

    // D de SOLID: depende de la abstraccion Asignable, no de Operador/RutaRecoleccion directamente
    public void asignar(Asignable destino) {
        if (estado == EstadoReporte.CERRADO) {
            throw new IllegalStateException("Un reporte cerrado no puede asignarse sin reabrirlo antes");
        }
        if (!destino.puedeRecibirAsignacion()) {
            throw new IllegalStateException("El destino no esta disponible para recibir la asignacion");
        }
        if (residuoEspecial && destino instanceof Operador operador && !operador.isHabilitadoResiduosEspeciales()) {
            throw new IllegalStateException("Este reporte requiere un operador habilitado para residuos especiales");
        }
        this.estado = EstadoReporte.ASIGNADO;
    }

    public void asociarRecoleccion(Recoleccion recoleccion) {
        this.recoleccionAsociada = recoleccion;
    }

    @Override
    public void cerrar() {
        // Regla de negocio 4
        if (recoleccionAsociada == null || recoleccionAsociada.getPesoTotal() <= 0) {
            throw new IllegalStateException("Solo se cierra un reporte con una recoleccion asociada y peso total mayor que cero");
        }
        this.estado = EstadoReporte.CERRADO;
    }

    public void reabrir() {
        if (estado != EstadoReporte.CERRADO) {
            throw new IllegalStateException("Solo se puede reabrir un reporte que esta cerrado");
        }
        this.estado = EstadoReporte.ABIERTO;
    }

    @Override
    public boolean estaCerrado() { return estado == EstadoReporte.CERRADO; }
}
