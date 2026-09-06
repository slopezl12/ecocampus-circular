package co.eia.ecocampus.residuos;

public class Parada {
    private final int orden;
    private final PuntoEcologico punto;
    private final String accionEsperada;

    public Parada(int orden, PuntoEcologico punto, String accionEsperada) {
        if (!punto.estaActivo()) {
            throw new IllegalStateException("Un punto inactivo no puede formar parte de una nueva ruta: " + punto.getId());
        }
        this.orden = orden;
        this.punto = punto;
        this.accionEsperada = accionEsperada;
    }

    public int getOrden() { return orden; }
    public PuntoEcologico getPunto() { return punto; }
    public String getAccionEsperada() { return accionEsperada; }
}
