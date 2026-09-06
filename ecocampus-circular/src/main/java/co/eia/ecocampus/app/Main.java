package co.eia.ecocampus.app;

import co.eia.ecocampus.personas.*;
import co.eia.ecocampus.residuos.*;
import co.eia.ecocampus.campanas.*;
import co.eia.ecocampus.comunes.Prioridad;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Personas: jerarquia polimorfica Persona -> Estudiante / Operador / ResponsableSostenibilidad
        Persona estudiante = new Estudiante("P001", "Santiago Gomez", "santiago@eia.edu.co", "Ingenieria de Sistemas");
        Operador operador = new Operador("P002", "Carlos Ruiz", "carlos@eia.edu.co", true);
        Persona responsable = new ResponsableSostenibilidad("P003", "Laura Perez", "laura@eia.edu.co", "Gestion de Residuos");

        // Punto ecologico
        PuntoEcologico punto = new PuntoEcologico("PE01", "PE-BLOQUE14", "Bloque 14", 100, "Reciclables,Organicos");

        // Reporte: crear -> asignar -> asociar recoleccion -> cerrar
        Reporte reporte = new Reporte("R001", estudiante, punto, "Desbordamiento de caneca", Prioridad.ALTA, false);
        reporte.asignar(operador);

        Recoleccion recoleccion = new Recoleccion("REC001", operador, 5);
        recoleccion.agregarMaterial(new Material("Plastico", 12.5, "kg"));
        recoleccion.agregarMaterial(new Material("Carton", 4.0, "kg"));
        reporte.asociarRecoleccion(recoleccion);
        reporte.cerrar();

        // Ruta de recoleccion con paradas
        RutaRecoleccion ruta = new RutaRecoleccion("RUTA01", operador, 10);
        ruta.agregarParada(new Parada(1, punto, "Recoger reciclables"));

        // Campana ambiental con actividad y participacion
        CampanaAmbiental campana = new CampanaAmbiental("C001", "Semana Verde EIA",
                LocalDate.of(2026, 9, 10), LocalDate.of(2026, 9, 14), 50, 5);
        campana.agregarActividad(new Actividad("A001", "Jornada de reciclaje", LocalDate.of(2026, 9, 11), 30));
        campana.inscribir(estudiante, LocalDate.now());

        // Eco-puntos
        estudiante.registrarEcoPuntos(new RegistroEcoPuntos("Reporte resuelto", 10, LocalDate.now(), estudiante));

        // Resumen general (requisito funcional 14)
        System.out.println("=== Resumen general EcoCampus Circular ===");
        System.out.println(estudiante + " | eco-puntos: " + estudiante.getTotalEcoPuntos());
        System.out.println(operador + " | disponible: " + operador.isDisponible());
        System.out.println(responsable + " | " + ((ResponsableSostenibilidad) responsable).generarIndicadores());
        System.out.println("Reporte " + reporte.getId() + " | estado: " + reporte.getEstado() + " | cerrado: " + reporte.estaCerrado());
        System.out.println("Recoleccion " + recoleccion.getId() + " | peso total: " + recoleccion.getPesoTotal() + " kg");
        System.out.println("Ruta " + ruta.getId() + " | paradas registradas: " + ruta.getParadas().length);
        System.out.println("Campana " + campana.getNombre() + " | participantes: " + campana.getParticipaciones().length);
    }
}
