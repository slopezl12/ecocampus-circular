package co.eia.ecocampus.personas;

public class Estudiante extends Persona {
    private String programa;

    public Estudiante(String id, String nombre, String correo, String programa) {
        super(id, nombre, correo, 20);
        this.programa = programa;
    }

    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }

    @Override
    public String rolDescripcion() { return "Estudiante"; }
}
