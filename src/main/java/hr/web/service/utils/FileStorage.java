package hr.web.service.utils;


import hr.web.api.exception.BadRequest;
import hr.web.api.exception.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileStorage {
    public static void saveTo(Path path, MultipartFile file) throws IOException {
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (FileAlreadyExistsException ignored) {

        }
        Files.write(path, file.getBytes());
        log.debug("Saved file: {}", path.toString());
    }

    public static void saveTo(String path, MultipartFile file) throws IOException {
        saveTo(Paths.get(path), file);
    }

    public static void delete(Path path) throws IOException {
        Files.delete(path);
        log.debug("File {} deleted.", path.toString());
    }

    public static Resource loadFrom(Path path) {
        try {
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) {
                throw new NotFound();
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new BadRequest();
        }
    }

}
