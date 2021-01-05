package com.github.ynfeng.todo.storage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.todolist.Item;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class H2ItemDBTest {
    private ItemDB itemDb;

    @BeforeEach
    public void setup() {
        String url = "jdbc:h2:/tmp/todo/" + UUID.randomUUID() + "/db";
        DBConfig dbConfig = new DBConfig("h2", url, "root", "root");
        itemDb = ItemDB.create("test", dbConfig);
    }

    @Test
    public void cant_create_unsupported_db_type() {
        DBConfig dbConfig = new DBConfig("mysql", "jdbc:h2:/tmp/db", "root", "root");
        try {
            ItemDB.create("test", dbConfig);
        } catch (TodoApplicationException exception) {
            assertThat(exception.getMessage(), is("unsupported database type."));
        }
    }

    @Test
    public void should_insert_item() {
        Item item = Item.newItem("test's item1");
        itemDb.append(item.done());

        List<Item> items = itemDb.loadAll();
        assertThat(items.get(0), is(Item.newItem("test's item1")));
        assertThat(items.get(0).isDone(), is(true));
    }

    @Test
    public void should_update_all() {
        Item item = Item.newItem("test's item1");
        itemDb.append(item);

        itemDb.updateAll(Lists.newArrayList(Item.newItem("1").done(), Item.newItem("2")));
        List<Item> items = itemDb.loadAll();
        assertThat(items.size(), is(2));
        assertThat(items.get(0), is(Item.newItem("1")));
        assertThat(items.get(1), is(Item.newItem("2")));
        assertThat(items.get(0).isDone(), is(true));
    }

    @Test
    public void should_append_all() {
        Item item = Item.newItem("test's item1");
        itemDb.append(item);

        itemDb.appendAll(Lists.newArrayList(Item.newItem("1"), Item.newItem("2")));
        List<Item> items = itemDb.loadAll();
        assertThat(items.size(), is(3));
        assertThat(items.get(0), is(Item.newItem("test's item1")));
        assertThat(items.get(1), is(Item.newItem("1")));
        assertThat(items.get(2), is(Item.newItem("2")));
    }
}
