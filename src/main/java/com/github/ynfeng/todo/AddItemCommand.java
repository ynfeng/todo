package com.github.ynfeng.todo;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.println;

public class AddItemCommand implements Command {

    @Override
    public void execute(String[] args, ItemRepository itemRepository) {
        Item item = Item.newItem(args[1]);
        itemRepository.add(item);
        printItems(itemRepository.listUnFinishedItem());
        println("Item %s added", item.name());
    }
}
