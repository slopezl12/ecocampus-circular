# EcoCampus Circular — Entrega 1

Proyecto integrador de Programación Orientada a Objetos (Java). Sistema de consola
para gestionar reportes de residuos, rutas de recolección, campañas ambientales y
eco-puntos en el campus de la Universidad EIA.

## Cómo importar y ejecutar

**Desde IntelliJ:**
1. `File → Open` y seleccionar la carpeta `ecocampus-circular` (IntelliJ detecta el `pom.xml` solo)
2. Esperar a que sincronice el proyecto Maven
3. Ir a `src/main/java/co/eia/ecocampus/app/Main.java` y correrlo (▶️ al lado de `main`)

**Desde terminal (sin Maven, con javac plano):**
```bas
cd ecocampus-circular
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out co.eia.ecocampus.app.Main
```

**Con Maven** (si tenés conexión para bajar plugins):
```bash
mvn compile exec:java -Dexec.mainClass="co.eia.ecocampus.app.Main"
```

## Estructura del proyecto

```
src/main/java/co/eia/ecocampus/
├── comunes/    -> interfaces (Identificable, Cerrable, Asignable, Observador) y enums de estado
├── personas/   -> Persona (abstracta), Estudiante, Operador, ResponsableSostenibilidad, RegistroEcoPuntos
├── residuos/   -> PuntoEcologico, Reporte, RutaRecoleccion, Parada, Recoleccion, Material
├── campanas/   -> CampanaAmbiental, Actividad, Participacion
└── app/        -> Main.java (escenario de demostración)
```

## Decisiones para la Entrega 1

- **Capacidad `n` de los arreglos**: por ahora está fija en el código como parámetro del
  constructor (ej. `capacidadMateriales`, `capacidadParadas`), en vez de pedirse por teclado.
  Falta conectar esto con un menú de consola que lea `n` con `Scanner` antes de crear
  cada colección, tal como pide el enunciado.
- Todas las multiplicidades `0..*` se manejan con arreglos de tamaño fijo + un contador
  de elementos ocupados (`numParadas`, `numMateriales`, etc.), sin usar `List`/`Set`/`Map`,
  porque esas colecciones no son obligatorias hasta la Entrega 2.
- Las validaciones de reglas de negocio (punto inactivo, cierre de reporte, campaña
  cerrada, inscripción duplicada) están implementadas lanzando `IllegalStateException`
  con un mensaje descriptivo — revisen con el equipo si prefieren manejarlo distinto
  (por ejemplo con excepciones propias, que sí aplica desde la Entrega 2).
- `Main.java` demuestra el flujo completo: crear personas (jerarquía polimórfica),
  reportar, asignar, recolectar, cerrar, crear ruta, crear campaña e inscribir.

## Pendiente para completar la Entrega 1

- [ ] Menú de consola interactivo (leer `n`, capturar datos por teclado, no solo el
      escenario fijo de `Main.java`)
- [ ] Diagrama de objetos (`Diagrama_Objetos_E1`)
- [ ] Informe (`Informe_E1.pdf`): portada, riesgos (5), requisitos no funcionales (4)
- [ ] Revisar que la lógica de negocio implementada coincida con lo que el equipo acordó
