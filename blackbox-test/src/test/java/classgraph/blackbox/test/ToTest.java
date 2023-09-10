package classgraph.blackbox.test;

import classgraph.blackbox.To;
import io.github.classgraph.ClassGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToTest {
    @Test
    public void testMainModuleName() {
        assertEquals("classgraph.blackbox.test.main", To.class.getModule().getName());
    }

    @Test
    public void testTestModuleName() {
        assertEquals("classgraph.blackbox.test.test", ToTest.class.getModule().getName());
    }

    public static final String WEBJARS_PATH_PREFIX = "META-INF/resources/webjars";

    @Test
    public void te2() throws IOException {
        // new Classgraph();
        var classGraph = new ClassGraph()
                .acceptPaths(WEBJARS_PATH_PREFIX);
        try (var scan = classGraph.scan()) {
            var resources = scan.getAllResources();
            System.out.println(resources);
            var theOneWereLookingFor = resources.get("META-INF/resources/webjars/bar-node/1.0.0/bar.js").get(0);

            var classpathElementURI = theOneWereLookingFor.getClasspathElementURI();

            var str = theOneWereLookingFor.toString();
            var ff = theOneWereLookingFor.open();
            var uri = theOneWereLookingFor.getURI();

            var url = theOneWereLookingFor.getURL();

            var file = theOneWereLookingFor.getClasspathElementFile();
            //[file:///Users/cwalker/git/classgraph/blackbox-test/build/classes/java/test/META-INF/resources/webjars/bar-node/1.0.0/bar.js
            System.out.println(resources.size());
            System.out.println("hi");
        }

    }
}
