package pl.put.CinemaManagement.file;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{objectKey}")
    FileDetails getFile(@PathVariable String objectKey) {
        return fileService.get(objectKey);
    }

    @RolesAllowed("admin")
    @PostMapping("/{objectKey}")
    void putFile(@PathVariable String objectKey, @RequestParam MultipartFile file) throws IOException {
        fileService.put(objectKey, file.getInputStream());
    }

    @RolesAllowed("admin")
    @DeleteMapping("/{objectKey}")
    void deleteFile(@PathVariable String objectKey) {
        fileService.delete(objectKey);
    }

    @GetMapping("/list")
    @RolesAllowed("admin")
    FileList listFiles(@RequestParam Integer maxKeys,
                       @RequestParam(required = false) String continuationToken) {
        return fileService.listFiles(new FileListRequest(maxKeys, continuationToken));
    }

}
