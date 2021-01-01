package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.item.Item;
import com.github.ynfeng.todo.item.ItemRepository;

public class AddItemCommand implements Command {

    @Override
    public void execute(Args args) {
        ItemRepository itemRepository = ApplicationContext.itemRepository();
        Item item = Item.newItem(args.getByIndex(1, "Usage: add <item name>"));
        itemRepository.add(item);
        printItems(itemRepository.listUnFinishedItem());
        println("Item %s added", item.name());
    }
}
