package org.example;

import org.example.entity.Tarea;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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


}
