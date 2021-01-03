package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
    private ByteArrayOutputStream out;

    @BeforeEach
    public void setup() {
        out = new ByteArrayOutputStream();
        Console.out(new PrintStream(out));
    }

    @Test
    public void should_output_tips_when_not_given_command() {
        Main.main(new String[] {null});

        assertThat(out.toString(), is("command not found.\n"));
    }

    @Test
    public void should_output_tips_when_given_unsupported_command() {
        Main.main(new String[] {"unsupported"});

        assertThat(out.toString(), is("unsupported command.\n"));
    }

    @Test
    public void should_output_tips_when_not_given_args() {
        Main.main(new String[] {});

        assertThat(out.toString(), is("command not found.\n"));
    }

    @AfterEach
    public void tearDown() throws IOException {
        out.close();
    }

    @Test
    public void should_output_tips_when_argument_incorrect() {
        Main.main(new String[] {"adduser", "test"});
        assertThat(out.toString(), is("Usage: adduser -u <username>\n"));
    }
}
