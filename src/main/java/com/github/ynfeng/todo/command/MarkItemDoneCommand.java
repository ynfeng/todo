package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.println;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.item.Item;
import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.item.ItemRepository;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(Args args) {
        ItemRepository itemRepository = ApplicationContext.itemRepository();
        int itemIdx = Integer.parseInt(args.getByIndex(1));
        Item item = itemRepository.getByIndex(itemIdx - 1).orElseThrow(
            () -> new TodoApplicationException("No such item"));
        item.done();
        itemRepository.update(item);
        println("Item %d done", itemIdx);
    }
}
