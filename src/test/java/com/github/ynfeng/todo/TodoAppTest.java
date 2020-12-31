package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ynfeng.todo.config.AppConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoAppTest {
    private TodoApp app;
    private ByteArrayOutputStream out;

    @BeforeEach
    public void setup() {
        out = new ByteArrayOutputStream();
        Console.out(new PrintStream(out));

        AppConfig config = new AppConfig() {
            @SuppressWarnings("unchecked")
            @Override
            public <T> T getConfigOrDefault(String key, T defaultValue) {
                return (T) ("/tmp/todo/" + UUID.randomUUID() + '/');
            }
        };
        new ApplicationContext(config);
        app = new TodoApp();
    }

    @Test
    public void should_add_item_and_output_tips() {
        app.run(Args.of("add", "foo"));
        assertThat(out.toString(), is("1. foo\nItem foo added\n"));

        out.reset();

        app.run(Args.of("add", "bar"));
        assertThat(out.toString(), is("1. foo\n2. bar\nItem bar added\n"));
    }

    @Test
    public void should_mark_todo_item_done_and_output_tips() {
        app.run(Args.of("add", "foo"));

        out.reset();

        app.run(Args.of("done", "1"));
        assertThat(out.toString(), is("Item 1 done\n"));
    }

    @Test
    public void should_list_unfinished_todo_item() {
        app.run(Args.of("add", "foo"));
        app.run(Args.of("add", "bar"));
        app.run(Args.of("add", "baz"));
        app.run(Args.of("done", "1"));
        out.reset();

        app.run(Args.of("list"));
        assertThat(out.toString(), is("1. bar\n2. baz\n"));
    }

    @Test
    public void should_list_all_items() {
        app.run(Args.of("add", "foo"));
        app.run(Args.of("add", "bar"));
        app.run(Args.of("add", "baz"));
        app.run(Args.of("done", "1"));
        out.reset();

        app.run(Args.of("list", "--all"));
        assertThat(out.toString(), is("1. [Done] foo\n2. bar\n3. baz\nTotal 3 items, 1 item done\n"));
    }

    @Test
    public void should_mark_item_done_with_different_processes() {
        String dataDir = UUID.randomUUID().toString();
        newContext(dataDir);
        app.run(Args.of("add", "中文测试"));
        newContext(dataDir);
        app.run(Args.of("add", "foo"));
        newContext(dataDir);
        app.run(Args.of("done", "1"));
        out.reset();

        newContext(dataDir);
        app.run(Args.of("list"));
        assertThat(out.toString(), is("1. foo\n"));
    }

    @Test
    public void should_login_with_password() {
        Console.passwordReader(() -> "12345");
        app.run(Args.of("login", "-u", "user"));

        assertThat(out.toString(), is("Password:\nLogin success!\n"));
    }

    @Test
    public void should_login_failed_when_password_incorrect() {
        Console.passwordReader(() -> "123");
        app.run(Args.of("login", "-u", "user"));

        assertThat(out.toString(), is("Password:\nLogin falied!\n"));
    }

    private static void newContext(String dataDir) {
        AppConfig config = new AppConfig() {
            @SuppressWarnings("unchecked")
            @Override
            public <T> T getConfigOrDefault(String key, T defaultValue) {
                return (T) ("/tmp/todo/test/" + dataDir + '/');
            }
        };
        new ApplicationContext(config);
    }

    @AfterEach
    public void tearDown() throws IOException {
        out.close();
    }
}
