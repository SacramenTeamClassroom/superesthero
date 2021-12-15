package fr.superesthero.models;

import java.util.HashMap;

import fr.superesthero.utils.optionnal;

public class Category {
    
    @optionnal
    public Integer id;
    public String name;

    public static HashMap<Integer, Category> list = new HashMap<>();

    public static int i = 0;

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
        c.id = i++;
        list.put(c.id,c);
    }

    public static void add(Category c) {
        list.put(c.id,c);
    }


}
