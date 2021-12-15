package fr.superesthero;

import static spark.Spark.port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.superesthero.controllers.CategoryController;
import fr.superesthero.controllers.HeroController;
import fr.superesthero.models.Category;
import fr.superesthero.models.Hero;

public class App {

    public static SessionFactory factory;
    
    public static void main(String[] args) {

        factory = new Configuration().configure("/fr/superesthero/hibernate.cfg.xml").buildSessionFactory();
        // var sources = new MetadataSources(registry);
        // var metadata = sources.getMetadataBuilder().build();
        // factory = metadata.getSessionFactoryBuilder().build();

        start();
    }

    public static void start() {
        var port = 4567;
        port(port);
        System.out.println("Start on port " + port);
        CategoryController.init();
        HeroController.init();
        Category.add("comic");
        Hero.add("Clark", "superman", new ArrayList<Category>(List.of(Category.get(0))));
    }

}
