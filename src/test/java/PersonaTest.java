import org.example.entity.Persona;
import org.example.entity.Tarea;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class PersonaTest {
    private Persona persona;

    @BeforeEach
    public void setUp(){
        persona = new Persona(1L, "Juan", "Pérez", "juanperez@gmail.com", LocalDate.of(1990, 3, 2));
    }

    @AfterEach
    public void tearDown() {
        persona = null;
    }

    @Test
    @DisplayName("Verifica que la persona se creo con éxito")
    public void testCrearPersona() {
        Assertions.assertNotNull(persona, "La persona dada no existe");
        Assertions.assertNotNull(persona.getTareas(), "La lista de tareas es null");
        Assertions.assertNotNull(persona.getNombre(), "El nombre es null");
        Assertions.assertNotNull(persona.getApellido(), "El apellido es null");
        Assertions.assertTrue(persona.getTareas().isEmpty(), "La lista de tareas no esta vacia");
        Assertions.assertFalse(persona.getEdad() < 0, "La persona tiene una edad negativa");
        Assertions.assertTrue(persona.getEmail().contains("@"), "Formato de email invalido");
    }

    @Test
    @DisplayName("Verifica que se agreguen las tareas a la lista de tareas de la persona")
    public void testAgregarTarea() {
        Tarea tarea = new Tarea(1L, "Lavar platos", "Se deben lavar todos las vajillas antes de ir a dormir");
        int nTareas = persona.getTareas().size();

        Assertions.assertNotNull(tarea, "La tarea que se quiere agregar no puede ser null");
        Assertions.assertFalse(persona.getTareas().contains(tarea));

        persona.getTareas().add(tarea);
        Assertions.assertFalse(persona.getTareas().isEmpty(), "La tarea no se pudo agregar");
        Assertions.assertEquals(nTareas + 1, persona.getTareas().size(), "El numero de tareas de la persona no coincide con el numero esperado");
    }

    @Test
    @DisplayName("Verifica que se puedan eliminar tareas")
    public void testEliminarTarea() {
        Tarea tarea = new Tarea(1L, "Lavar platos", "Se deben lavar todos las vajillas antes de ir a dormir");

        Assertions.assertNotNull(tarea, "La tarea es null");
        persona.getTareas().add(tarea);
        Assertions.assertTrue(persona.getTareas().contains(tarea), "La tarea no esta en la lista");

        persona.getTareas().remove(tarea);
        Assertions.assertFalse(persona.getTareas().contains(tarea), "La tarea no fue eliminada");
    }

    @Test
    @DisplayName("Verifica que se puedan agregar amigos")
    public void testAgregarAmigo() {
        Persona amigo = new Persona(2L, "Maria", "Gomez", "mariagomez@gmail.com", LocalDate.of(1999, 3, 2));
        int nAmigos = persona.getAmigos().size();

        Assertions.assertNotNull(amigo, "El amigo no puede ser null");
        Assertions.assertFalse(persona.getAmigos().contains(amigo), "No se puede tener 2 veces el mismo amigo");

        persona.getAmigos().add(amigo);
        Assertions.assertFalse(persona.getAmigos().isEmpty(), "La lista de amigos esta vacia");
        Assertions.assertEquals(nAmigos + 1, persona.getAmigos().size(), "El numero de amigos de la persona no coincide con el numero esperado");
    }

    @Test
    @DisplayName("Verifica que se puedan eliminar amigos")
    public void testEliminarAmigo() {
        Persona amigo = new Persona(2L, "Maria", "Gomez", "mariagomez@gmail.com", LocalDate.of(1990, 3, 2));

        Assertions.assertNotNull(amigo, "El amigo no puede ser null");
        persona.getAmigos().add(amigo);
        Assertions.assertTrue(persona.getAmigos().contains(amigo), "La persona no tiene ese amigo");

        persona.getAmigos().remove(amigo);
        Assertions.assertFalse(persona.getAmigos().contains(amigo), "El amigo no se elimino");
    }

    // TESTS DINÁMICOS
    @TestFactory
    @DisplayName("Verifica que un grupo de personas puedan agregarse como amigos")
    public Stream<DynamicTest> testAgregarAmigos() {
        List<Persona> nuevosAmigos = List.of(
                new Persona(2L, "Maria", "Gomez", "mariagomez@gmail.com", LocalDate.of(1999, 3, 2)),
                new Persona(3L, "Jose", "Pereira", "josepereira@gmail.com", LocalDate.of(2000, 5, 22)),
                new Persona(4L, "Enrique", "Gomez", "enrique@gmail.com", LocalDate.of(2003, 1, 15))
        );

        nuevosAmigos.forEach(persona.getAmigos()::add);

        return nuevosAmigos.stream()
                .map(amigo -> DynamicTest.dynamicTest(
                        amigo.toString(),
                        () -> {
                            Assertions.assertTrue(persona.getAmigos().contains(amigo),
                                    "El amigo " + amigo.getNombre() + " no se encuentra en la lista");
                            System.out.println(amigo.getNombre() + " tiene: " + amigo.getEdad());
                        }
                ));
    }

    @TestFactory
    @DisplayName("Verifica que un grupo de personas puedan ser eliminadas de la lista de amigos")
    public Stream<DynamicTest> testEliminarAmigos() {
        List<Persona> nuevosAmigos = List.of(
                new Persona(2L, "Maria", "Gomez", "mariagomez@gmail.com", LocalDate.of(1999, 3, 2)),
                new Persona(3L, "Jose", "Pereira", "josepereira@gmail.com", LocalDate.of(2000, 5, 22)),
                new Persona(4L, "Enrique", "Gomez", "enrique@gmail.com", LocalDate.of(2003, 1, 15))
        );

        nuevosAmigos.forEach(persona.getAmigos()::add);

        return nuevosAmigos.stream()
                .map(amigo -> DynamicTest.dynamicTest(amigo.toString(), () -> {
                    Assertions.assertTrue(persona.getAmigos().contains(amigo), "El amigo existe");
                    persona.getAmigos().remove(amigo);
                    Assertions.assertFalse(persona.getAmigos().contains(amigo), "El amigo no fue eliminado correctamente");
                }));
    }

    // TESTS PARAMETRIZADOS (se necesita junit-jupiter-params como dependencia en el pom.xml)

    // Opción 1, @ValuesSource
    @ParameterizedTest
    @ValueSource(ints = {24, 26, 18, 20, 22})
    public void testMayorDeEdad(int edad) {
        Persona p = new Persona(5L, "Jennifer", "Lawrence", "katnisseverdeen@gmail.com", edad);
        Assertions.assertTrue(p.esMayorDeEdad(), "La edad: " + edad + " no es >= 18");
    }

    // Opción 2, @MethodSource
    @ParameterizedTest
    @MethodSource("proveedorDeDatos")
    public void testEsMayorDeEdadConProveedor(Long id, String nombre, String apellido, String email, int edad) {
        Persona p = new Persona(id, nombre, apellido, email, edad);
        Assertions.assertTrue(p.esMayorDeEdad(), "La edad: " + edad + " no es >= 18");
    }

    public static Stream<Arguments> proveedorDeDatos() {
        return Stream.of(
                Arguments.of(6L, "Josh", "Hutcherson", "peetamellark@gmail.com", 18),
                Arguments.of(7L, "Andy", "Samberg", "jakeperalta@gmail.com", 22),
                Arguments.of(8L, "Melissa", "Fumero", "amysantiago@gmail.com", 22),
                Arguments.of(9L, "Joe", "Lo Truglio", "charlesboyle@gmail.com", 27)
        );
    }
}
