package com.github.ynfeng.todo.storage;

import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.todolist.Item;
import java.util.Arrays;

public interface ItemDB extends Storage<Item> {
    static ItemDB create(String owner, DBConfig dbConfig) {
        return Type.of(dbConfig.type()).createDB(owner, dbConfig);
    }

    enum Type {
        H2("h2") {
            @Override
            ItemDB createDB(String owner, DBConfig dbConfig) {
                return new H2ItemDB(owner, dbConfig);
            }
        };
        private final String name;

        Type(String name) {
            this.name = name;
        }

        abstract ItemDB createDB(String owner, DBConfig dbConfig);

        public static Type of(String name) {
            return Arrays.stream(values())
                .filter(each -> each.name.equals(name))
                .findAny()
                .orElseThrow(() -> new TodoApplicationException("unsupported database type."));
        }
    }
}
