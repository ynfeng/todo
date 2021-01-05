package com.github.ynfeng.todo.storage;

import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.todolist.Item;
import com.google.common.collect.Lists;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class H2ItemDB implements ItemDB {
    private final String owner;
    private final DBConfig config;

    public H2ItemDB(String owner, DBConfig dbConfig) {
        this.owner = owner;
        config = dbConfig;
        loadDriver();
        initTables();
    }

    private void initTables() {
        doInTransaction(conn -> {
            try {
                PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS ITEM(id int auto_increment, owner varchar(255) ,name varchar(255))");
                stmt.execute();
                return null;
            } catch (SQLException e) {
                throw new TodoApplicationException(e);
            }
        });
    }

    private static void loadDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new TodoApplicationException(e);
        }
    }

    @Override
    public List<Item> loadAll() {
        return doInTransaction(connection -> {
            List<Item> result = Lists.newArrayList();
            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ITEM WHERE OWNER = ?");
                stmt.setString(1, owner);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    result.add(Item.newItem(rs.getString("NAME")));
                }
            } catch (SQLException throwables) {
                throw new TodoApplicationException(throwables);
            }
            return result;
        });
    }

    @Override
    public void append(Item item) {
        String sql = "INSERT INTO ITEM(OWNER,NAME) values(?,?)";
        doInTransaction(connection -> {
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, owner);
                stmt.setString(2, item.name());
                stmt.execute();
                return null;
            } catch (SQLException throwables) {
                throw new TodoApplicationException(throwables);
            }
        });
    }

    @Override
    public void updateAll(List<Item> items) {
        deleteAll();
        items.forEach(this::append);
    }

    private void deleteAll() {
        String sql = "DELETE FROM ITEM WHERE OWNER = ?";
        doInTransaction(connection -> {
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, owner);
                stmt.execute();
                return null;
            } catch (SQLException throwables) {
                throw new TodoApplicationException(throwables);
            }
        });
    }

    @Override
    public void appendAll(List<Item> items) {
        items.forEach(this::append);
    }

    private <T> T doInTransaction(Function<Connection, T> function) {
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
}
