package pl.put.CinemaManagement.file.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    byte[] get(String key) throws IOException;

    void put(String key, InputStream fileInputStream) throws IOException;

    void delete(String key);
}
