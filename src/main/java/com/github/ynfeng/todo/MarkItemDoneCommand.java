package com.github.ynfeng.todo;

public class MarkItemDoneCommand implements Command {
    @Override
    public void execute(String[] args, Console console, ItemRepository itemRepository) {
        int itemIdx = Integer.parseInt(args[1]);
        Item item = itemRepository.getByIndex(itemIdx - 1);
        item.done();
        itemRepository.update(item);
        Console.println("Item %d done", itemIdx);
    }
}
