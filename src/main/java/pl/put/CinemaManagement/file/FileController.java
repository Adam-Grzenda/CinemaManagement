package pl.put.CinemaManagement.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.put.CinemaManagement.file.dto.FileUploadResponse;
import pl.put.CinemaManagement.file.service.FileService;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @CrossOrigin(origins = "${angular.client}")
    @PostMapping(value="/upload", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestPart MultipartFile image) {
        String randomObjectKey = UUID.randomUUID().toString();
        try {
            fileService.put(randomObjectKey, image.getInputStream());
            return ResponseEntity.status(HttpStatus.CREATED).body(new FileUploadResponse(randomObjectKey));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "${angular.client}")
    @GetMapping(value="/file/{id}", produces= MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> getFile(@PathVariable String id) {
        try {
            return ResponseEntity.ok(fileService.get(id));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
