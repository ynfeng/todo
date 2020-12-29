public class ListUnFinishedItemsCommand implements Command {
    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        console.printItems(itemRepository.findUnFinishedItems());
    }
}
