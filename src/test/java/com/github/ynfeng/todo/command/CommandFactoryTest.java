package com.github.ynfeng.todo.command;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class CommandFactoryTest {

    @Test
    public void cant_create_unsupported_command() {
        try {
            CommandFactory.createCommand("unsupported");
        } catch (Exception exception) {
            assertThat(exception, instanceOf(IllegalArgumentException.class));
            assertThat(exception.getMessage(), is("unsupported command."));
        }
    }

}
