package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item1");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenAddItemThenDeleteItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item1");
        tracker.add(item);
        assertTrue(tracker.delete(item.getId()));
    }

    @Test
    public void whenAddItemsThenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));
        List<Item> rsl = tracker.findByName("item1");
        for (Item items :rsl) {
            assertThat(items.getName(), is("item1"));
        }
    }

    @Test
    public void whenAddItemsThenFindById() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item rsl = tracker.findById(item.getId());
        assertThat(rsl.getName(), is(item.getName()));
    }

    @Test
    public void whenAddItemsThenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        Item item4 = new Item("item4");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        List<Item> rsl = tracker.findAll();
        for (int i = 0; i < rsl.size(); i++) {
            assertThat(String.format("item%d", (i + 1)), is(rsl.get(i).getName()));
        }
    }

    @Test
    public void whenAddItemsThenReplace() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.replace(item2.getId(), item1), is(true));
    }

    @Test
    public void whenAddItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        assertThat(tracker.add(item1), is(item1));
    }
}