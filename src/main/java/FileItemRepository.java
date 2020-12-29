import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileItemRepository implements ItemRepository {
    private final String dataDir;
    private List<Item> items = new ArrayList<>();
    private final File dataFile;
    private final Gson gson = new Gson();
    private final RandomAccessFile raf;

    public FileItemRepository(String dataDir) {
        this.dataDir = dataDir;
        dataFile = new File(dataDir + "todo.json");
        ensureDirExists(dataDir);
        raf = createDataFileWriter();
        loadDataFromFile();
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

    private void loadDataFromFile() {
        try {
            items = Files.readAllLines(dataFile.toPath()).stream()
                .map(line -> gson.fromJson(line, Item.class))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void add(Item item) {
        items.add(item);
        appendItemToFile(item);
    }

    private void appendItemToFile(Item item) {
        try {
            raf.seek(raf.length());
            raf.writeBytes(gson.toJson(item) + "\r\n");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item getByIndex(int index) {
        return items.get(index);
    }

    @Override
    public List<Item> listUnFinishedItem() {
        return items.stream().filter(item -> !item.isDone()).collect(Collectors.toList());
    }

    @Override
    public List<Item> listAll() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public void update(Item item) {
        try {
            File tempFile = writeItemsToTempFile();
            replaceDataFile(tempFile);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void replaceDataFile(File tempFile) throws IOException {
        Files.delete(dataFile.toPath());
        Files.move(tempFile.toPath(), dataFile.toPath());
    }

    private File writeItemsToTempFile() throws IOException {
        File tempFile = new File(dataDir + "/tmp.json");
        RandomAccessFile temp = new RandomAccessFile(tempFile, "rw");
        for (Item eachItem : items) {
            temp.writeBytes(gson.toJson(eachItem) + "\r\n");
        }
        temp.close();
        return tempFile;
    }
}
