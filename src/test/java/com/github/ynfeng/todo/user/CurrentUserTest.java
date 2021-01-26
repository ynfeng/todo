package com.github.ynfeng.todo.user;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class CurrentUserTest {

    @Test
    public void should_set_current_user() {
        CurrentUser.set(new LoggedUser("testUser"));

        assertThat(CurrentUser.username(), is("testUser"));
    }

}
