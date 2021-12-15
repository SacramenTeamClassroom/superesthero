package fr.superesthero.models;

import static fr.superesthero.App.factory;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.superesthero.utils.optionnal;

@Entity
public class Category {
    
    @optionnal
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;
    public String name;

    public static HashMap<Integer, Category> list = new HashMap<>();

    public Category(String name) {
        this.name = name;
    }
    
    public static Category get(int id) {
        return list.get(id);
    }
    
    public static Category set(int id, Category c) {
        return list.put(id, c);
    }

    public static void add(String name) {
        var c = new Category(name);
        persist(c);
        list.put(c.id,c);
    }

    public static void add(Category c) {
        persist(c);
        list.put(c.id,c);
    }

    static void persist(Category c) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(c);
        tx.commit();
        session.close();
    }


}
