import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileItemRepositoryTest {
    private String dataDir;

    @BeforeEach
    public void setup() {
        dataDir = "/tmp/todo/" + UUID.randomUUID() + '/';
    }

    @Test
    public void should_save_todo_item_to_file() {
        ItemRepository itemRepository = new FileItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo"));
        itemRepository.add(Item.newItem("bar"));

        ItemRepository newItemRepository = new FileItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));
        Item foo = newItemRepository.getByIndex(0);
        Item bar = newItemRepository.getByIndex(1);
        Item baz = newItemRepository.getByIndex(2);

        assertThat(foo.name(), is("foo"));
        assertThat(bar.name(), is("bar"));
        assertThat(baz.name(), is("baz"));
    }

    @Test
    public void should_list_all_todo_items() {
        ItemRepository itemRepository = new FileItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo"));
        itemRepository.add(Item.newItem("bar"));

        ItemRepository newItemRepository = new FileItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));

        List<Item> all = newItemRepository.listAll();

        assertThat(all, is(Arrays.asList(Item.newItem("foo"), Item.newItem("bar"), Item.newItem("baz"))));
    }

    @Test
    public void should_list_unfinished_todo_items() {
        ItemRepository itemRepository = new FileItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo").done());
        itemRepository.add(Item.newItem("bar"));

        ItemRepository newItemRepository = new FileItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));

        List<Item> all = newItemRepository.listUnFinishedItem();

        assertThat(all, is(Arrays.asList(Item.newItem("bar"), Item.newItem("baz"))));
    }

    @Test
    public void should_update_todo_item() {
        ItemRepository itemRepository = new FileItemRepository(dataDir);
        itemRepository.add(Item.newItem("foo"));
        Item foo = itemRepository.getByIndex(0);
        foo.done();
        itemRepository.update(foo);

        ItemRepository newItemRepository = new FileItemRepository(dataDir);
        newItemRepository.add(Item.newItem("baz"));
        foo = newItemRepository.getByIndex(0);

        assertThat(foo.isDone(), is(true));
    }
}