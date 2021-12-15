package fr.superesthero;

import static spark.Spark.port;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.superesthero.controllers.CategoryController;
import fr.superesthero.controllers.HeroController;
import fr.superesthero.models.Category;
import fr.superesthero.models.Hero;

public class App {

    public static SessionFactory factory;
    
    public static void main(String[] args) {
        
        Configuration configuration = new Configuration()
            .addAnnotatedClass(Hero.class)
            .addAnnotatedClass(Category.class)
            .setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC")
            .setProperty("hibernate.connection.url", "jdbc:sqlite:app.sqlite")
            .setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect")
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.hdm2ddl.auto", "update");
            // .setProperty("transaction.factory_class","org.hibernate.transaction.JDBCTransactionFactory");
            

        factory = configuration.buildSessionFactory();

        start();
    }

    public static void start() {
        var port = 4567;
        port(port);
        System.out.println("Start on port " + port);
        CategoryController.init();
        HeroController.init();
    }

}
