package fr.superesthero;

import static spark.Spark.port;

import java.util.ArrayList;
import java.util.List;

import fr.superesthero.controllers.CategoryController;
import fr.superesthero.controllers.HeroController;
import fr.superesthero.models.Category;
import fr.superesthero.models.Hero;

public class App {
    
    public static void main(String[] args) {
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
