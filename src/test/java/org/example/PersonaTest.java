package org.example;

import org.example.entity.Persona;
import org.example.entity.Tarea;
import org.junit.jupiter.api.*;

public class PersonaTest {
    private Persona persona;

    @BeforeEach
    public void setUp(){
        persona = new Persona(1L, "Juan", "Pérez", "juanperez@gmail.com", 20);
    }

    @AfterEach
    public void tearDown() {
        persona = null;
    }

    @Test
    @DisplayName("Verifica que la persona se creo con éxito")
    public void testCrearPersona() {
        Assertions.assertNotNull(persona, "La persona dada no existe");
        Assertions.assertNotNull(persona.getTareas(), "La lista de tareas no puede ser null");
        Assertions.assertTrue(persona.getTareas().isEmpty(), "La lista de tareas debe estar vacia");
    }

    @Test
    @DisplayName("Verifica que se agreguen las tareas a la lista de tareas de la persona")
    public void testAgregarTarea() {
        Tarea tarea = new Tarea(1L, "Lavar platos", "Se deben lavar todos las vajillas antes de ir a dormir");
        int nTareas = persona.getTareas().size();

        Assertions.assertNotNull(tarea, "La tarea que se quiere agregar no puede ser null");

        persona.getTareas().add(tarea);
        Assertions.assertFalse(persona.getTareas().isEmpty(), "La tarea no se pudo agregar");
        Assertions.assertEquals(nTareas + 1, persona.getTareas().size(), "El numero de tareas de la persona no coincide con el numero esperado");
    }

    @Test
    @DisplayName("Verifica que se puedan agregar amigos")
    public void testAgregarAmigo() {
        Persona amigo = new Persona(2L, "Maria", "Gomez", "mariagomez@gmail.com", 27);
        int nAmigos = persona.getAmigos().size();

        Assertions.assertNotNull(amigo, "El amigo no puede ser null");

        persona.getAmigos().add(amigo);
        Assertions.assertFalse(persona.getAmigos().isEmpty(), "La lista de amigos esta vacia");
        Assertions.assertEquals(nAmigos + 1, persona.getAmigos().size(), "El numero de amigos de la persona no coincide con el numero esperado");
    }
}
