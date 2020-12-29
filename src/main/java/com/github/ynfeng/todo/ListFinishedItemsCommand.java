package com.github.ynfeng.todo;

public class ListFinishedItemsCommand implements Command {
    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        if (hasAllFlag(args)) {
            console.printItems(itemRepository.listAll());
        } else {
            console.printItems(itemRepository.listUnFinishedItem());
        }
    }

    private static boolean hasAllFlag(String[] args) {
        return args.length > 1 && "--all".equals(args[1].trim());
    }
}
