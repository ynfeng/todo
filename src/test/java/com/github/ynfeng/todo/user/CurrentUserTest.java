package com.github.ynfeng.todo.user;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class CurrentUserTest {

    @Test
    public void should_set_current_user() {
        CurrentUser.set(new User("testUser", "12345"));

        User user = CurrentUser.get();
        assertThat(user.name(), is("testUser"));
        assertThat(user.password(), is("12345"));
    }

}