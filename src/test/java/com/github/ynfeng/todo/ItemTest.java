package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ynfeng.todo.Item;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    public void should_to_string_by_item_index() {
        Item foo = Item.newItem("foo");
        assertThat(foo.toString(1), is("1. foo"));

        Item bar = Item.newItem("bar");
        bar.done();
        assertThat(bar.toString(2), is("2. [Done] bar"));
    }

}
