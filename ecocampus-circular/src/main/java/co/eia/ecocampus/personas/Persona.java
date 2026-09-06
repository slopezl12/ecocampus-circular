package co.eia.ecocampus.personas;

import co.eia.ecocampus.comunes.Identificable;
import co.eia.ecocampus.comunes.Observador;

public abstract class Persona implements Identificable, Observador {
    private final String id;
    private String nombre;
    private String correo;
    private final RegistroEcoPuntos[] historialEcoPuntos;
    private int numRegistros;

    protected Persona(String id, String nombre, String correo, int capacidadHistorial) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.historialEcoPuntos = new RegistroEcoPuntos[capacidadHistorial];
        this.numRegistros = 0;
    }

    @Override
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    // Cada subtipo describe su propio rol -> polimorfismo (requisito 1)
    public abstract String rolDescripcion();

    public void registrarEcoPuntos(RegistroEcoPuntos registro) {
        if (numRegistros >= historialEcoPuntos.length) {
            throw new IllegalStateException("Capacidad de historial de eco-puntos agotada para " + nombre);
        }
        historialEcoPuntos[numRegistros++] = registro;
    }

    public int getTotalEcoPuntos() {
        int total = 0;
        for (int i = 0; i < numRegistros; i++) {
            total += historialEcoPuntos[i].getPuntos();
        }
        return total;
    }

    public RegistroEcoPuntos[] getHistorialEcoPuntos() {
        RegistroEcoPuntos[] copia = new RegistroEcoPuntos[numRegistros];
        System.arraycopy(historialEcoPuntos, 0, copia, 0, numRegistros);
        return copia;
    }

    @Override
    public void actualizar(String evento, Object origen) {
        System.out.println("[Notificacion para " + nombre + "] " + evento + " (origen: " + origen + ")");
    }

    @Override
    public String toString() {
        return rolDescripcion() + " " + nombre + " (" + id + ")";
    }
}
