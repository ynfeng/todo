package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ynfeng.todo.storage.FileStorage;
import com.github.ynfeng.todo.todolist.TodoList;
import com.github.ynfeng.todo.todolist.Item;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoListTest {
    private String dataDir;
    private FileStorage<Item> storage;

    @BeforeEach
    public void setup() {
        dataDir = "/tmp/todo/" + UUID.randomUUID() + '/';
        storage = new FileStorage<Item>(dataDir, Item.class);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void should_save_todo_item_to_file() {
        TodoList todoList = new TodoList(storage);
        todoList.add(Item.newItem("foo"));
        todoList.add(Item.newItem("bar"));

        TodoList newTodoList = new TodoList(storage);
        newTodoList.add(Item.newItem("baz"));
        Item foo = newTodoList.get(0).get();
        Item bar = newTodoList.get(1).get();
        Item baz = newTodoList.get(2).get();

        assertThat(foo.name(), is("foo"));
        assertThat(bar.name(), is("bar"));
        assertThat(baz.name(), is("baz"));
    }

    @Test
    public void should_list_all_todo_items() {
        TodoList todoList = new TodoList(storage);
        todoList.add(Item.newItem("foo"));
        todoList.add(Item.newItem("bar"));

        TodoList newTodoList = new TodoList(storage);
        newTodoList.add(Item.newItem("baz"));

        List<Item> all = newTodoList.all();

        assertThat(all, is(Arrays.asList(Item.newItem("foo"), Item.newItem("bar"), Item.newItem("baz"))));
    }

    @Test
    public void should_list_unfinished_todo_items() {
        TodoList todoList = new TodoList(storage);
        todoList.add(Item.newItem("foo").done());
        todoList.add(Item.newItem("bar"));

        TodoList newTodoList = new TodoList(storage);
        newTodoList.add(Item.newItem("baz"));

        List<Item> all = newTodoList.unFinishedItems();

        assertThat(all, is(Arrays.asList(Item.newItem("bar"), Item.newItem("baz"))));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void should_update_todo_item() {
        TodoList todoList = new TodoList(storage);
        todoList.add(Item.newItem("foo"));
        Item foo = todoList.get(0).get();
        foo.done();
        todoList.update(foo);

        TodoList newTodoList = new TodoList(storage);
        newTodoList.add(Item.newItem("baz"));
        foo = newTodoList.get(0).get();

        assertThat(foo.isDone(), is(true));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void item_name_can_contains_chinese() {
        TodoList todoList = new TodoList(storage);
        todoList.add(Item.newItem("中文测试"));
        todoList.add(Item.newItem("foo"));
        todoList.add(Item.newItem("bar"));
        TodoList newTodoList = new TodoList(storage);
        Item chineseItem = newTodoList.get(0).get();

        assertThat(chineseItem.name(), is("中文测试"));
    }
}
