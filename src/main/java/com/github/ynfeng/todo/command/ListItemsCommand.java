package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.printSummary;

import com.github.ynfeng.todo.ApplicationContext;
import com.github.ynfeng.todo.Args;
import com.github.ynfeng.todo.persistence.ItemRepository;

public class ListItemsCommand implements Command {
    @Override
    public void execute(Args args) {
        ItemRepository itemRepository = ApplicationContext.itemRepository();
        if (args.has("--all")) {
            printItems(itemRepository.listAll());
            printSummary(itemRepository.listAll());
        } else {
            printItems(itemRepository.listUnFinishedItem());
        }
    }
}
