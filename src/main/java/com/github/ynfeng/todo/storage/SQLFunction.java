package com.github.ynfeng.todo.storage;

import java.sql.SQLException;

public interface SQLFunction<T, R> {
    R apply(T t) throws SQLException;
}
