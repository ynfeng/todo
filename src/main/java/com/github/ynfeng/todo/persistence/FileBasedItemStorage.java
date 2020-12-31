package com.github.ynfeng.todo.persistence;

import com.github.ynfeng.todo.Item;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileBasedItemStorage {
    private final File dataFile;
    private final Gson gson = new Gson();

    public FileBasedItemStorage(String dataDir) {
        ensureDirExists(dataDir);
        dataFile = new File(dataDir + "todo.json");
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
    public List<Item> loadDataFromFile() {
        try {
            return Files.readLines(dataFile, StandardCharsets.UTF_8)
                .stream()
                .map(line -> gson.fromJson(line, Item.class))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void appendItem(Item item) {
        try {
            Files.asCharSink(dataFile, StandardCharsets.UTF_8, FileWriteMode.APPEND)
                .write(gson.toJson(item) + "\r\n");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void updateAll(List<Item> items) {
        try {
            File tempFile = writeItemsToTempFile(items);
            replaceOldDataFile(tempFile);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private File writeItemsToTempFile(List<Item> items) throws IOException {
        File tempFile = new File(dataFile.getParent() + "/tmp.json");
        RandomAccessFile raf = new RandomAccessFile(tempFile, "rw");
        for (Item eachItem : items) {
            raf.write((gson.toJson(eachItem) + "\r\n").getBytes(StandardCharsets.UTF_8));
        }
        raf.close();
        return tempFile;
    }

    private void replaceOldDataFile(File tempFile) throws IOException {
        java.nio.file.Files.delete(dataFile.toPath());
        java.nio.file.Files.move(tempFile.toPath(), dataFile.toPath());
    }
}
