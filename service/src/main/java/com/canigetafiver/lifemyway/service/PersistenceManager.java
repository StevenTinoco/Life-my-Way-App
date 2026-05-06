package com.canigetafiver.lifemyway.service;

import com.canigetafiver.lifemyway.api.UserDataBase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PersistenceManager {
    public void saveUserData(UserDataBase db, String path) throws IOException {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Path cannot be null or blank.");
        }

        Path mainPath = Path.of(path);
        Path tempPath = Path.of(path + ".tmp");
        Path parent = mainPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        UserDataBase dataToSave = db == null ? new UserDataBase() : db;
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(tempPath))) {
            output.writeObject(dataToSave);
        }

        try {
            Files.move(tempPath, mainPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException atomicMoveFailed) {
            Files.move(tempPath, mainPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public UserDataBase loadUserData(String path) throws IOException {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Path cannot be null or blank.");
        }

        Path mainPath = Path.of(path);
        Path tempPath = Path.of(path + ".tmp");
        Path pathToLoad = mainPath;

        if (Files.notExists(mainPath)) {
            if (Files.exists(tempPath)) {
                restoreTempFile(tempPath, mainPath);
            } else {
                return new UserDataBase();
            }
        }

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(pathToLoad))) {
            Object savedData = input.readObject();
            if (savedData instanceof UserDataBase userDataBase) {
                return userDataBase;
            }
            return new UserDataBase();
        } catch (ClassNotFoundException | IOException unreadableData) {
            return new UserDataBase();
        }
    }

    private void restoreTempFile(Path tempPath, Path mainPath) throws IOException {
        Path parent = mainPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.copy(tempPath, mainPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
