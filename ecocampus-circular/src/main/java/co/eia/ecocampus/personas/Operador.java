package co.eia.ecocampus.personas;

import co.eia.ecocampus.comunes.Asignable;

public class Operador extends Persona implements Asignable {
    private boolean disponible;
    private final boolean habilitadoResiduosEspeciales;

    public Operador(String id, String nombre, String correo, boolean habilitadoResiduosEspeciales) {
        super(id, nombre, correo, 20);
        this.disponible = true;
        this.habilitadoResiduosEspeciales = habilitadoResiduosEspeciales;
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public boolean isHabilitadoResiduosEspeciales() { return habilitadoResiduosEspeciales; }

    @Override
    public boolean puedeRecibirAsignacion() { return disponible; }

    @Override
    public String rolDescripcion() { return "Operador"; }
}
