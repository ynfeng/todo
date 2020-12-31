package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.Item;
import com.github.ynfeng.todo.persistence.ItemRepository;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(Args args, ItemRepository itemRepository) {
        int itemIdx = Integer.parseInt(args.get(1));
        Item item = itemRepository.getByIndex(itemIdx - 1);
        item.done();
        itemRepository.update(item);
        println("Item %d done", itemIdx);
    }
}
