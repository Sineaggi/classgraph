package nonapi.io.github.classgraph.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class UriUtils {
    /**
     * Constructor.
     */
    private UriUtils() {
        // Cannot be constructed
    }

    public static URI reverseRelativize22(URI uri, String path) {
        // todo: is the path in a resource always unix-ized?
        ModuleReference;
    }

    public static URI reverseRelativizeRecursiveParent(URI uri, String path) {
        Path fullResourceURI;
        System.out.println(uri);
        try (InputStream is = uri.toURL().openStream()){
            is.close();
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fullResourceURI = Paths.get(uri);
        } catch (FileSystemNotFoundException e) {
            throw new RuntimeException("Could not convert uri " + uri + " to path", e);
        }
        //String fullResourcePath = fullResourceURI.toString();
        //int lastIndex = fullResourcePath.lastIndexOf(resourcePath);
        //if (lastIndex == -1) {
        //    throw new IllegalStateException("Resource " + fullResourceURI + " does not end with " + resourcePath);
        //}
        //return URI.create(fullResourcePath.substring(0, lastIndex));

        int count = Paths.get(path).getNameCount();
        for (int i = 0; i < count; i++) {
            fullResourceURI = fullResourceURI.getParent();
        }
        return fullResourceURI.toUri();
    }

    public static URI reverseRelativizeWithSubPath(URI uri, String path) {
        Path fullResourceURI = Paths.get(uri);
        //String fullResourcePath = fullResourceURI.toString();
        //int lastIndex = fullResourcePath.lastIndexOf(resourcePath);
        //if (lastIndex == -1) {
        //    throw new IllegalStateException("Resource " + fullResourceURI + " does not end with " + resourcePath);
        //}
        //return URI.create(fullResourcePath.substring(0, lastIndex));
        // int count = Paths.get(path).getNameCount();
        // for (int i = 0; i < count; i++) {
        //     fullResourceURI = fullResourceURI.getParent();
        // }
        fullResourceURI = fullResourceURI.subpath(0, fullResourceURI.getNameCount() - Paths.get(path).getNameCount());
        // subpath(0,getNameCount()-1);
        return fullResourceURI.toUri();
    }
}
