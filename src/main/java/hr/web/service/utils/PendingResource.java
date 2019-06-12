package hr.web.service.utils;

import hr.domain.FileResource;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PendingResource {
    private Path parent;
    private List<ImmutablePair<Path, MultipartFile>> pending = new ArrayList<>();

    PendingResource(Path parent) {
        this.parent = parent;
    }

    public FileResource predictByTimestamp(MultipartFile file) {
        Path path = predictImpl(String.valueOf(new Date().getTime()), file);
        return new FileResource(path.toString());
    }

    public FileResource predict(String filename, MultipartFile file) {
        Path path = predictImpl(filename, file);
        return new FileResource(path.toString());
    }

    private Path predictImpl(String filename, MultipartFile file) {
        Path path = parent.resolve(byHint(filename, file));
        waitResource(path, file);
        return path;
    }

    public void waitResource(Path path, MultipartFile file) {
        pending.add(new ImmutablePair<>(path, file));
    }

    private String byHint(String filename, MultipartFile file) {
        return byHint(filename, FilenameUtils.getExtension(file.getOriginalFilename()));
    }

    private String byHint(String filename, String extension) {
        return filename + "." + extension;
    }

    public void writeResources() throws IOException {
        for (ImmutablePair<Path, MultipartFile> pair: pending)
            FileStorage.saveTo(pair.left, pair.right);
    }
}
