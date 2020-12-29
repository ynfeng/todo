package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private TodoApp app;
    private ByteArrayOutputStream out;

    @BeforeEach
    public void setup() {
        out = new ByteArrayOutputStream();
        app = new TodoApp(new AppConfig() {
            @SuppressWarnings("unchecked")
            @Override
            public <T> T getConfig(String key) {
                return (T) ("/tmp/todo/" + UUID.randomUUID() + '/');
            }
        }, new PrintStream(out));
    }

    @Test
    public void should_add_item_and_output_tips() {
        app.run("add", "foo");
        assertThat(out.toString(), is("1. foo\nItem foo added\n"));

        out.reset();

        app.run("add", "bar");
        assertThat(out.toString(), is("1. foo\n2. bar\nItem bar added\n"));
    }

    @Test
    public void should_mark_todo_item_done_and_output_tips() {
        app.run("add", "foo");

        out.reset();
        app.run("done", "1");

        assertThat(out.toString(), is("Item 1 done\n"));
    }

    @Test
    public void should_list_unfinished_todo_item() {
        app.run("add", "foo");
        app.run("add", "bar");
        app.run("add", "baz");
        app.run("done", "1");
        out.reset();

        app.run("list");
        assertThat(out.toString(), is("1. bar\n2. baz\n"));
    }

    @Test
    public void should_list_all_items() {
        app.run("add", "foo");
        app.run("add", "bar");
        app.run("add", "baz");
        app.run("done", "1");
        out.reset();

        app.run("list", "--all");
        assertThat(out.toString(), is("1. [Done] foo\n2. bar\n3. baz\n"));
    }
}
