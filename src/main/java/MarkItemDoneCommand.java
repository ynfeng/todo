public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        int itemIdx = Integer.valueOf(args[1]);
        Item item = itemRepository.getByIndex(itemIdx - 1);
        item.done();
        console.println("Item %d done", itemIdx);
    }
}
