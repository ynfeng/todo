import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class Console {
    private final PrintStream out;

    public Console(OutputStream out) {
        this.out = new PrintStream(out);
    }

    public void println(String patten, Object... args) {
        out.println(String.format(patten, args));
    }
}
