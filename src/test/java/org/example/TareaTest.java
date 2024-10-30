package org.example;

import org.example.entity.Tarea;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class TareaTest {
    private Tarea tarea;

    @BeforeEach
    public void setUp() {
        tarea =  new Tarea(1L, "Lavar platos", "Lavar toda la vajilla antes de ir a dormir");
    }

    @AfterEach
    public void tearDown(){
        tarea = null;
    }

    @Test
    @DisplayName("Verifica que la tarea se crea correctamente")
    public void testCreaTarea() {
        Assertions.assertNotNull(tarea, "La tarea es null");
        Assertions.assertNotNull(tarea.getNombre(), "El nombre de la tarea es null");
        Assertions.assertNotNull(tarea.getDescripcion(), "La descripcion de la tarea es null");
        Assertions.assertEquals(LocalDate.now(), tarea.getFechaInicio(), "La fecha de inicio debería ser la fecha actual");
    }

    @Test
    @DisplayName("Verifica que se actualice el nombre de la tarea")
    public void testActualizarNombre() {
        tarea.setNombre("estudiar");
        Assertions.assertEquals("estudia", tarea.getNombre(), "No se actualizo el nombre de la tarea");
    }
}
