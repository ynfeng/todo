package com.github.ynfeng.todo.storage;

import com.github.ynfeng.todo.TodoApplicationException;
import com.github.ynfeng.todo.config.DBConfig;
import com.github.ynfeng.todo.todolist.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class H2ItemDB extends JDBCSupport implements ItemDB {
    private final String owner;

    public H2ItemDB(String owner, DBConfig dbConfig) {
        super(dbConfig);
        this.owner = owner;
        loadDriver();
        initTables();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new TodoApplicationException(e);
        }
    }

    private void initTables() {
        String sql = "CREATE TABLE IF NOT EXISTS ITEM(id int auto_increment, owner varchar(255) ,name varchar(255), status varchar(10))";
        executeSql(sql);
    }

    @Override
    public List<Item> loadAll() {
        String sql = "SELECT * FROM ITEM WHERE OWNER = ?";
        return executeQuery(sql, H2ItemDB::resulteSetToItem, owner);

    }

    private static Item resulteSetToItem(ResultSet rs) throws SQLException {
        Item item = Item.newItem(rs.getString("NAME"));
        if (Item.Status.Done.toString().equals(rs.getString("STATUS"))) {
            item.done();
        }
        return item;
    }

    @Override
    public void append(Item item) {
        String sql = "INSERT INTO ITEM(OWNER,NAME,STATUS) values(?,?,?)";
        executeSql(sql, owner, item.name(), item.status().toString());
    }

    @Override
    public void updateAll(List<Item> items) {
        deleteAll();
        items.forEach(this::append);
    }

    private void deleteAll() {
        String sql = "DELETE FROM ITEM WHERE OWNER = ?";
        executeSql(sql, owner);
    }

    @Override
    public void appendAll(List<Item> items) {
        items.forEach(this::append);
    }
}
