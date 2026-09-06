package co.eia.ecocampus.personas;

public class ResponsableSostenibilidad extends Persona {
    private String areaResponsabilidad;

    public ResponsableSostenibilidad(String id, String nombre, String correo, String areaResponsabilidad) {
        super(id, nombre, correo, 20);
        this.areaResponsabilidad = areaResponsabilidad;
    }

    public String getAreaResponsabilidad() { return areaResponsabilidad; }
    public void setAreaResponsabilidad(String areaResponsabilidad) { this.areaResponsabilidad = areaResponsabilidad; }

    public String generarIndicadores() {
        return "Indicadores del area " + areaResponsabilidad + " para " + getNombre();
    }

    @Override
    public String rolDescripcion() { return "Responsable de Sostenibilidad"; }
}
