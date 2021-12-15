package fr.superesthero.models;

import static fr.superesthero.App.factory;

import java.util.List;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.superesthero.utils.optionnal;

@Entity
public class Hero {
    
    @optionnal
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String slug;
    @optionnal
    @ManyToMany
    public List<Category> categories;

    public static HashMap<Integer, Hero> list = new HashMap<>();

    public Hero(String name, String slug, List<Category> categories) {
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

    public static void add(String name, String slug, List<Category> categories) {
        var h = new Hero(name,slug, categories);
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
