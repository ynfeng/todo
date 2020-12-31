package com.github.ynfeng.todo.command;

import static com.github.ynfeng.todo.Console.printItems;
import static com.github.ynfeng.todo.Console.printSummary;

import com.github.ynfeng.todo.persistence.ItemRepository;

public class ListItemsCommand implements Command {
    @Override
    public void execute(String[] args, ItemRepository itemRepository) {
        if (hasAllFlag(args)) {
            printItems(itemRepository.listAll());
            printSummary(itemRepository.listAll());
        } else {
            printItems(itemRepository.listUnFinishedItem());
        }
    }

    private static boolean hasAllFlag(String[] args) {
        return args.length > 1 && "--all".equals(args[1].trim());
    }
}
