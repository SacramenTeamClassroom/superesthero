package fr.superesthero.models;

import static fr.superesthero.App.factory;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.superesthero.utils.optionnal;

@Entity
public class Hero {
    
    @optionnal
    @Id
    public Integer id;
    public String name;
    public String slug;
    @optionnal
    @ManyToMany
    public ArrayList<Category> categories;

    public static HashMap<Integer, Hero> list = new HashMap<>();

    public static int i = 0;

    public Hero(String name, String slug, ArrayList<Category> categories) {
        this.name = name;
        this.slug = slug;
        this.categories = categories;
    }

    public static Hero get(int id) {
        return list.get(id);
    }
    
    public static Hero set(int id, Hero h) {
        return list.put(id, h);
    }

    public static void add(String name, String slug, ArrayList<Category> categories) {
        var h = new Hero(name,slug, categories);
        h.id = i++;
        persist(h);
        list.put(h.id,h);
    }

    public static void add(Hero h) {
        persist(h);
        list.put(h.id,h);
    }

    static void persist(Hero h) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(h);
        tx.commit();
        session.close();
    }

}
