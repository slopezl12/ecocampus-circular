package co.eia.ecocampus.residuos;

public class Material {
    private final String tipo;
    private final double cantidad;
    private final String unidad;

    public Material(String tipo, double cantidad, String unidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public String getTipo() { return tipo; }
    public double getCantidad() { return cantidad; }
    public String getUnidad() { return unidad; }
}
