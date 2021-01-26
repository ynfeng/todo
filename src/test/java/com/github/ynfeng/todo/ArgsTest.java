package com.github.ynfeng.todo;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ArgsTest {

    @Test
    public void must_given_cmd_arg_at_least() {
        try {
            Args args = Args.of(null);
            args.cmd();
            fail();
        } catch (Exception exception) {
            assertThat(exception, instanceOf(TodoApplicationException.class));
            assertThat(exception.getMessage(), is("command not found."));
        }
    }

    @Test
    public void cant_get_not_exist_argument() {
        try {
            Args args = Args.of("add");
            args.getByIndexOrThrowException(1, "Missing argument");
            fail();
        } catch (Exception exception) {
            assertThat(exception, instanceOf(TodoApplicationException.class));
            assertThat(exception.getMessage(), is("Missing argument"));
        }
    }

}
