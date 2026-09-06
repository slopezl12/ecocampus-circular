package co.eia.ecocampus.residuos;

import co.eia.ecocampus.personas.Operador;
import java.time.LocalDate;

public class Recoleccion {
    private final String id;
    private final LocalDate fecha;
    private final Operador operadorResponsable;
    private String observaciones;
    private final Material[] materiales;
    private int numMateriales;

    public Recoleccion(String id, Operador operadorResponsable, int capacidadMateriales) {
        this.id = id;
        this.fecha = LocalDate.now();
        this.operadorResponsable = operadorResponsable;
        this.materiales = new Material[capacidadMateriales];
        this.numMateriales = 0;
    }

    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public Operador getOperadorResponsable() { return operadorResponsable; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    // Composicion: un material registrado aqui no se reutiliza en otra recoleccion
    public void agregarMaterial(Material material) {
        if (numMateriales >= materiales.length) {
            throw new IllegalStateException("Capacidad de materiales agotada en la recoleccion " + id);
        }
        materiales[numMateriales++] = material;
    }

    public Material[] getMateriales() {
        Material[] copia = new Material[numMateriales];
        System.arraycopy(materiales, 0, copia, 0, numMateriales);
        return copia;
    }

    public double getPesoTotal() {
        double total = 0;
        for (int i = 0; i < numMateriales; i++) {
            total += materiales[i].getCantidad();
        }
        return total;
    }
}
