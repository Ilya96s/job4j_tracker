package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {

    private static final String REPLACE = "UPDATE Item SET name = :name, created = :created WHERE id = :id";

    private static final String DELETE = "DELETE Item WHERE id = :id";

    private static final String FIND_BY_NAME = "From Item WHERE name = :name";

    private static final String FIND_BY_ID = "From Item WHERE id = :id";

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(REPLACE);
            query.setParameter("name", item.getName())
                    .setParameter("created", item.getCreated())
                    .setParameter("id", id);
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(DELETE);
            query.setParameter("id", id);
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery("From Item", Item.class);
            items = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(FIND_BY_NAME);
            query.setParameter("name", key);
            items = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Item item = new Item();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(FIND_BY_ID);
            query.setParameter("id", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        sf.close();
    }
}
