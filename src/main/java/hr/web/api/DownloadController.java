package hr.web.api;

import hr.data.FileResourceRepository;
import hr.domain.FileResource;
import hr.web.api.exception.NotFound;
import hr.web.service.utils.FileStorage;
import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/download")
public class DownloadController {
    private Tika tika = new Tika();
    private FileResourceRepository fileResourceRepo;

    public DownloadController(FileResourceRepository fileResourceRepo) {
        this.fileResourceRepo = fileResourceRepo;
    }

    @GetMapping
    public ResponseEntity<Resource> download(@RequestParam long resourceId) {

        FileResource fileResource = fileResourceRepo.findById(resourceId).orElseThrow(() -> new NotFound(resourceId));

        Resource resource = FileStorage.loadFrom(Paths.get(fileResource.getPath()));
        String contentType = tika.detect(fileResource.getPath());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
