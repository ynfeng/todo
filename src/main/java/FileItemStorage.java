import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileItemStorage {
    private final File dataFile;
    private final Gson gson = new Gson();
    private final RandomAccessFile raf;

    public FileItemStorage(String dataDir) {
        ensureDirExists(dataDir);
        dataFile = new File(dataDir + "todo.json");
        raf = createDataFileWriter();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void ensureDirExists(String dir) {
        File dataDir = new File(dir);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    private RandomAccessFile createDataFileWriter() {
        try {
            return new RandomAccessFile(dataFile, "rw");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Item> loadDataFromFile() {
        try {
            return Files.readAllLines(dataFile.toPath()).stream()
                .map(line -> gson.fromJson(line, Item.class))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void appendItem(Item item) {
        try {
            raf.writeBytes(gson.toJson(item) + "\r\n");
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
            raf.writeBytes(gson.toJson(eachItem) + "\r\n");
        }
        raf.close();
        return tempFile;
    }

    private void replaceOldDataFile(File tempFile) throws IOException {
        Files.delete(dataFile.toPath());
        Files.move(tempFile.toPath(), dataFile.toPath());
    }
}
