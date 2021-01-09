package com.github.ynfeng.todo;

import java.sql.SQLException;

public interface SQLFunction<T, R> {
    R apply(T t) throws SQLException;
}
