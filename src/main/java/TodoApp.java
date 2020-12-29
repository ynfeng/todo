import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
    private final Console console;
    public static final List<String> items = new ArrayList<>();

    public TodoApp(PrintStream out) {
        console = new Console(out);
    }

    public void run(String... args) {
        String item = args[1];
        items.add(item);

        for (int i = 0; i < items.size(); i++) {
            console.println("%d. %s", i + 1, items.get(i));
        }

        console.println("Item %s added", item);
    }
}
