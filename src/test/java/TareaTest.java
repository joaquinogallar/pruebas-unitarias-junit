import org.example.entity.Tarea;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

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
        Assertions.assertEquals("estudiar", tarea.getNombre(), "No se actualizo el nombre de la tarea");
    }

    @Test
    @DisplayName("Verifica que se actualice la descripcion de la tarea")
    public void testActualizarDescripcion() {
        String nuevaDescripcion = "estudiar 6hs, 2 antes de comer y 4 despues de comer";
       tarea.setDescripcion(nuevaDescripcion);
       Assertions.assertEquals(nuevaDescripcion, tarea.getDescripcion(), "La descripcion no se actualizo");
    }
}
