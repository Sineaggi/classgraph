package nonapi.io.github.classgraph.utils;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class UriUtilsTest {
    @Test
    public void test() throws URISyntaxException {
        String path = "class-path-manifest-entry.jar";
        URI resource = UriUtilsTest.class.getResource("/" + path).toURI();
        URI hekkin = UriUtils.reverseRelativizeRecursiveParent(resource, path);
        assertEquals(resource, hekkin.resolve(path));
    }

    @Test
    public void testWithSubPath() throws URISyntaxException {
        String path = "class-path-manifest-entry.jar";
        URI resource = UriUtilsTest.class.getResource("/" + path).toURI();
        URI hekkin = UriUtils.reverseRelativizeWithSubPath(resource, path);
        assertEquals(resource, hekkin.resolve(path));
    }

    @Test
    public void komez() throws URISyntaxException {
        String path = "META-INF/LICENSE-notice.md";
        URI resource = Test.class.getResource("/" + path).toURI();
        URI hekkin = UriUtils.reverseRelativizeRecursiveParent(resource, path);
        assertEquals(resource, hekkin.resolve(path));
    }
}
