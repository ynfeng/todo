import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void should_output_tips() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        TodoApp app = new TodoApp(new PrintStream(out));

        app.run("add", "foo");
        assertThat(out.toString(), is("1. foo\nItem foo added\n"));

        out.reset();

        app.run("add", "bar");
        assertThat(out.toString(), is("1. foo\n2. bar\nItem bar added\n"));
    }

}
