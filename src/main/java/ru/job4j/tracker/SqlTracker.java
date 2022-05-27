package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        String sql = "insert into items(name) values(?)";
        try (PreparedStatement statement = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.execute();
            try (ResultSet generatedKeys  = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        String sql = "select * from items where id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        String sql = "delete from items where id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        String sql = "select * from items";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                itemList.add(new Item(
                        resultSet.getString("name"),
                        resultSet.getInt("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        String sql = "select * from items where name = ?";
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            itemList.add(new Item(
                    resultSet.getString("name"),
                    resultSet.getInt("id")
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findById(int id) {
        String sql = "select * from items where id = ?";
        Item item = null;
        try (PreparedStatement statement = cn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            item = new Item(
                    resultSet.getString("name"),
                    resultSet.getInt("id")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
