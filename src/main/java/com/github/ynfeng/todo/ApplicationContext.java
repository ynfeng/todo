package com.github.ynfeng.todo;

import com.github.ynfeng.todo.item.ItemRepository;
import com.github.ynfeng.todo.user.UserRepository;

public class ApplicationContext {
    private final static ApplicationContext INSTANCE = new ApplicationContext();
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    private ApplicationContext() {

    }

    public static ItemRepository itemRepository() {
        return INSTANCE.itemRepository;
    }

    public static UserRepository userRepository() {
        return INSTANCE.userRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        INSTANCE.userRepository = userRepository;
    }

    public static void setItemRepository(ItemRepository itemRepository) {
        INSTANCE.itemRepository = itemRepository;
    }
}
