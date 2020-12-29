public interface Command {
    void execute(String[] args, Console console, ItemRepository itemRepository);
}
