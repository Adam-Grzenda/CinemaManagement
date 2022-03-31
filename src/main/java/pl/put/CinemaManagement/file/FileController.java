package pl.put.CinemaManagement.file;

import com.amazonaws.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{objectKey}")
    byte[] getFile(@PathVariable String objectKey) {
        return fileService.get(objectKey);
    }

    @PostMapping("/{objectKey}")
    void putFile(@PathVariable String objectKey, @RequestBody String file) { //TODO - migrate to multipart?
        fileService.put(objectKey, new ByteArrayInputStream(Base64.decode(file)));
    }

    @DeleteMapping("/{objectKey}")
    void deleteFile(@PathVariable String objectKey) {
        fileService.delete(objectKey);
    }

    @GetMapping("/list")
    FileList listFiles(@RequestParam Integer maxKeys,
                       @RequestParam(required = false) String continuationToken) {
        return fileService.listFiles(new FileListRequest(maxKeys, continuationToken));
    }

}
