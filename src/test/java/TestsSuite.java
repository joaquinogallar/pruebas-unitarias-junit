import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        PersonaTest.class,
        TareaTest.class
})
public class TestsSuite {
}
