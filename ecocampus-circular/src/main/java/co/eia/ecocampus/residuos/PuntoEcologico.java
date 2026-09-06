package co.eia.ecocampus.residuos;

import co.eia.ecocampus.comunes.Identificable;
import co.eia.ecocampus.comunes.EstadoPunto;

public class PuntoEcologico implements Identificable {
    private final String id;
    private String codigo;
    private String ubicacion;
    private final int capacidad;
    private EstadoPunto estado;
    private final String categoriasAceptadas;

    public PuntoEcologico(String id, String codigo, String ubicacion, int capacidad, String categoriasAceptadas) {
        this.id = id;
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.categoriasAceptadas = categoriasAceptadas;
        this.estado = EstadoPunto.ACTIVO;
    }

    @Override
    public String getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public int getCapacidad() { return capacidad; }
    public String getCategoriasAceptadas() { return categoriasAceptadas; }

    // Regla de negocio 2
    public boolean estaActivo() { return estado == EstadoPunto.ACTIVO; }
    public void desactivar() { this.estado = EstadoPunto.INACTIVO; }
    public void activar() { this.estado = EstadoPunto.ACTIVO; }
}
