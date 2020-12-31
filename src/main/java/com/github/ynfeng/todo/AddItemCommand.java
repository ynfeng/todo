package com.github.ynfeng.todo;

public class AddItemCommand implements Command {

    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        Item item = Item.newItem(args[1]);
        itemRepository.add(item);
        Console.printItems(itemRepository.listUnFinishedItem());
        Console.println("Item %s added", item.name());
    }
}
