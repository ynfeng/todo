package com.github.ynfeng.todo.command;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ynfeng.todo.TodoApplicationException;
import org.junit.jupiter.api.Test;

class CommandFactoryTest {

    @Test
    public void cant_create_unsupported_command() {
        try {
            CommandFactory.getCommand("unsupported");
        } catch (Exception exception) {
            assertThat(exception, instanceOf(TodoApplicationException.class));
            assertThat(exception.getMessage(), is("unsupported command."));
        }
    }

}
