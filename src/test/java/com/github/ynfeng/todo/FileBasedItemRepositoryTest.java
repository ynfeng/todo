package com.github.ynfeng.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.ynfeng.todo.persistence.FileBasedItemRepository;
import com.github.ynfeng.todo.persistence.ItemRepository;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileBasedItemRepositoryTest {
    private String dataDir;

    @BeforeEach
    public void setup() {
        dataDir = "/tmp/todo/" + UUID.randomUUID() + '/';
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void should_save_todo_item_to_file() {
        ItemRepository itemRepository = new FileBasedItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo"));
        itemRepository.add(Item.newItem("bar"));

        ItemRepository newItemRepository = new FileBasedItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));
        Item foo = newItemRepository.getByIndex(0).get();
        Item bar = newItemRepository.getByIndex(1).get();
        Item baz = newItemRepository.getByIndex(2).get();

        assertThat(foo.name(), is("foo"));
        assertThat(bar.name(), is("bar"));
        assertThat(baz.name(), is("baz"));
    }

    @Test
    public void should_list_all_todo_items() {
        ItemRepository itemRepository = new FileBasedItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo"));
        itemRepository.add(Item.newItem("bar"));

        ItemRepository newItemRepository = new FileBasedItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));

        List<Item> all = newItemRepository.listAll();

        assertThat(all, is(Arrays.asList(Item.newItem("foo"), Item.newItem("bar"), Item.newItem("baz"))));
    }

    @Test
    public void should_list_unfinished_todo_items() {
        ItemRepository itemRepository = new FileBasedItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo").done());
        itemRepository.add(Item.newItem("bar"));

        ItemRepository newItemRepository = new FileBasedItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));

        List<Item> all = newItemRepository.listUnFinishedItem();

        assertThat(all, is(Arrays.asList(Item.newItem("bar"), Item.newItem("baz"))));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void should_update_todo_item() {
        ItemRepository itemRepository = new FileBasedItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo"));
        Item foo = itemRepository.getByIndex(0).get();
        foo.done();
        itemRepository.update(foo);

        ItemRepository newItemRepository = new FileBasedItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));
        foo = newItemRepository.getByIndex(0).get();

        assertThat(foo.isDone(), is(true));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void item_name_can_contains_chinese() {
        ItemRepository itemRepository = new FileBasedItemRepository(dataDir);
        itemRepository.add(Item.newItem("中文测试"));
        itemRepository.add(Item.newItem("foo"));
        itemRepository.add(Item.newItem("bar"));
        ItemRepository newItemRepository = new FileBasedItemRepository(dataDir);
        Item chineseItem = newItemRepository.getByIndex(0).get();

        assertThat(chineseItem.name(), is("中文测试"));
    }
}
