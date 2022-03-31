package pl.put.CinemaManagement.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class FileList {
    private List<FileDetails> files;
}
