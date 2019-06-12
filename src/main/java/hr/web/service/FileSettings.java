package hr.web.service;

import hr.web.service.utils.PendingResourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class FileSettings {
    private Path rootDir;

    public FileSettings(@Value("${files.root:data}") String root) {
        this.rootDir = Paths.get(root).toAbsolutePath();
    }

    public PendingResourceBuilder rootResolve(String directory) {
        return new PendingResourceBuilder(rootDir.resolve(directory));
    }
}
