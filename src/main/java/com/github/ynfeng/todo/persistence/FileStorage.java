package com.github.ynfeng.todo.persistence;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileStorage<T> {
    private final File dataFile;
    private final Class<T> type;
    private final Gson gson = new Gson();

    public FileStorage(String dataFilePath, Class<T> type) {
        this.dataFile = new File(dataFilePath);
        this.type = type;
        ensureDirExists(dataFile.getParent());
        ensureFileExists(dataFile);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void ensureDirExists(String dir) {
        File dataDir = new File(dir);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void ensureFileExists(File dataFile) {
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public List<T> loadAll() {
        try {
            return Files.readLines(dataFile, StandardCharsets.UTF_8)
                .stream()
                .map(line -> gson.fromJson(line, type))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void append(T item) {
        try {
            Files.asCharSink(dataFile, StandardCharsets.UTF_8, FileWriteMode.APPEND)
                .write(gson.toJson(item) + "\r\n");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void updateAll(List<T> items) {
        try {
            File tempFile = writeItemsToTempFile(items);
            replaceOldDataFile(tempFile);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    private File writeItemsToTempFile(List<T> items) throws IOException {
        File tempFile = createTempFile();
        Files.asCharSink(tempFile, StandardCharsets.UTF_8, FileWriteMode.APPEND)
            .writeLines(items.stream().map(gson::toJson));
        return tempFile;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private File createTempFile() throws IOException {
        File tempFile = new File(dataFile.getParent() + "/tmp.json");
        tempFile.delete();
        tempFile.createNewFile();
        return tempFile;
    }

    private void replaceOldDataFile(File tempFile) throws IOException {
        java.nio.file.Files.delete(dataFile.toPath());
        java.nio.file.Files.move(tempFile.toPath(), dataFile.toPath());
    }
}
