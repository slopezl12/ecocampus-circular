package co.eia.ecocampus.residuos;

import co.eia.ecocampus.comunes.Asignable;
import co.eia.ecocampus.comunes.Identificable;
import co.eia.ecocampus.comunes.EstadoRuta;
import co.eia.ecocampus.personas.Operador;

public class RutaRecoleccion implements Identificable, Asignable {
    private final String id;
    private final Operador operador;
    private EstadoRuta estado;
    private final Parada[] paradas;
    private int numParadas;

    public RutaRecoleccion(String id, Operador operador, int capacidadParadas) {
        this.id = id;
        this.operador = operador;
        this.paradas = new Parada[capacidadParadas];
        this.numParadas = 0;
        this.estado = EstadoRuta.PLANEADA;
    }

    @Override
    public String getId() { return id; }
    public Operador getOperador() { return operador; }
    public EstadoRuta getEstado() { return estado; }

    //las paradas no existen sin la ruta
    public void agregarParada(Parada parada) {
        if (numParadas >= paradas.length) {
            throw new IllegalStateException("Capacidad de paradas agotada en la ruta " + id);
        }
        paradas[numParadas++] = parada;
    }

    public Parada[] getParadas() {
        Parada[] copia = new Parada[numParadas];
        System.arraycopy(paradas, 0, copia, 0, numParadas);
        return copia;
    }

    public void cerrarRuta() { this.estado = EstadoRuta.CERRADA; }

    @Override
    public boolean puedeRecibirAsignacion() { return estado != EstadoRuta.CERRADA; }
}
