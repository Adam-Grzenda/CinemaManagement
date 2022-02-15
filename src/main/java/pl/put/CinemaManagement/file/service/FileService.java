package pl.put.CinemaManagement.file.service;

import java.io.InputStream;

public interface FileService {
    byte[] get(String key);

    void put(String key, InputStream fileInputStream);

    void delete(String key);
}
