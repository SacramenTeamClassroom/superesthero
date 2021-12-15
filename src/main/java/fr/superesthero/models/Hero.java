package fr.superesthero.models;

import java.util.ArrayList;
import java.util.HashMap;

import fr.superesthero.utils.optionnal;

public class Hero {
    
    @optionnal
    public Integer id;
    public String name;
    public String slug;
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
        list.put(h.id,h);
    }

    public static void add(Hero h) {
        list.put(h.id,h);
    }

}
