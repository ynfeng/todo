package com.github.ynfeng.todo.storage;

import com.github.ynfeng.todo.SQLFunction;
import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.config.DBConfig;
import com.google.common.collect.Lists;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class JDBCSupport {
    private final DBConfig config;

    protected JDBCSupport(DBConfig config) {
        this.config = config;
    }

    protected <T> T doInTransaction(Function<Connection, T> function) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.url(), config.user(), config.password());
            conn.setAutoCommit(false);
            return function.apply(conn);
        } catch (Exception e) {
            throw new TodoApplicationException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.commit();
                    conn.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    protected void executeSql(String sql, Object... params) {
        doInTransaction(conn -> {
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                stmt.execute();
            } catch (SQLException throwables) {
                throw new TodoApplicationException(throwables);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ignored) {
                }
            }
            return null;
        });
    }

    protected <R> List<R> executeQuery(String sql, SQLFunction<ResultSet, R> converter, Object... params) {
        return doInTransaction(conn -> {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = conn.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                rs = stmt.executeQuery();
                List<R> result = Lists.newArrayList();
                while (rs.next()) {
                    result.add(converter.apply(rs));
                }
                return result;
            } catch (SQLException throwables) {
                throw new TodoApplicationException(throwables);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException ignored) {
                }
            }
        });
    }
}
