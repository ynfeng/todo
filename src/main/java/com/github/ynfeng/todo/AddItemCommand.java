package com.github.ynfeng.todo;

public class AddItemCommand implements Command {

    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        Item item = Item.newItem(args[1]);
        itemRepository.add(item);
        console.printItems(itemRepository.listUnFinishedItem());
        console.println("Item %s added", item.name());
    }
}
