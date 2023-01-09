package ru.job4j.tracker.oop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.HbmTracker;
import ru.job4j.tracker.Item;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class HbmTrackerTest {

    @BeforeEach
    public void clearDataBase() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            for (Item item : session.createQuery("From Item", Item.class).list()) {
                session.delete(item);
            }
            session.getTransaction().commit();
        }
    }

    @Test
    void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("first item");

            tracker.add(item);

            Item result = tracker.findById(item.getId());

            assertThat(result.getName()).isEqualTo(item.getName());
        }

    }

    @Test
    void whenAddItemToTrackerThenChangedIt() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("first item");

            tracker.add(item);

            Item newItem = new Item();
            newItem.setName("new Item");

            boolean result = tracker.replace(item.getId(), newItem);

            Item itemFromDB = tracker.findById(item.getId());

            assertThat(result).isTrue();
            assertThat(itemFromDB.getName()).isEqualTo(newItem.getName());
        }
    }

    @Test
    void whenAddItemToTrackerThenDeleteIt() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("first item");

            tracker.add(item);

            boolean result = tracker.delete(item.getId());

            assertThat(result).isTrue();
        }
    }

    @Test
    void whenAddItemsToTrackerThenFindAll() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item();
            item1.setName("item 1");
            tracker.add(item1);

            Item item2 = new Item();
            item1.setName("item 2");
            tracker.add(item2);

            Item item3 = new Item();
            item1.setName("item 3");
            tracker.add(item3);

            Item item4 = new Item();
            item1.setName("item 4");
            tracker.add(item4);

            Item item5 = new Item();
            item1.setName("item 5");
            tracker.add(item5);

            List<Item> itemsFromDB = tracker.findAll();
            assertThat(itemsFromDB.size()).isEqualTo(5);
        }
    }

    @Test
    void whenAddItemToTrackerThenFindByNameIt() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item();
            item1.setName("first item");

            tracker.add(item1);

            Item item2 = new Item();
            item2.setName("second item");

            tracker.add(item2);

            List<Item> itemFromDB = tracker.findByName(item1.getName());

            assertThat(itemFromDB.size()).isEqualTo(1);
        }
    }

    @Test
    void whenAddItemToTrackerThenFindByIdIt() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("first item");

            tracker.add(item);

            Item itemFromDB = tracker.findById(item.getId());

            assertThat(itemFromDB.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    void close() {
    }
}