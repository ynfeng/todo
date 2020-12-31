package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Item;
import com.github.ynfeng.todo.persistence.ItemRepository;

public class AddItemCommand implements Command {

    @Override
    public void execute(Args a, String[] args, ItemRepository itemRepository) {
        Item item = Item.newItem(a.get(1));
        itemRepository.add(item);
        printItems(itemRepository.listUnFinishedItem());
        println("Item %s added", item.name());
    }
}
