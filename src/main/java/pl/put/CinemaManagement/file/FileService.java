package pl.put.CinemaManagement.file;

import pl.put.CinemaManagement.file.s3.S3FileList;

import java.io.InputStream;

public interface FileService {
    FileDetails get(String key);

    void put(String key, InputStream fileInputStream);

    void delete(String key);

    S3FileList listFiles(FileListRequest request);
}
