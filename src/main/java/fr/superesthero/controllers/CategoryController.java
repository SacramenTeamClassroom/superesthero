package fr.superesthero.controllers;

import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.put;
import static fr.superesthero.api.Success.*;
import static fr.superesthero.api.Error.*;
import static fr.superesthero.utils.JsonConverter.*;

import fr.superesthero.models.Category;

public class CategoryController {
    
    public static void init() {
    
        post("/category", (req, res) -> {
            Category c;
            try {
                c = check(convert.fromJson(req.body(), Category.class));
                if (c.id != null) throw new Exception("No id allowed");
                Category.add(c);
            } catch (Exception e) {
                res.status(400);
                return sendError(req, res, "Invalid json");
            }
            res.status(201);
            return sendSuccess(req, res, "Category succesfully created!");
        });

        delete("/category/:id", (req, res) -> {
            int id;
            try {
                id = Integer.parseInt(req.params("id"));
            } catch (Exception e) {
                return sendError(req, res, "the id should be a valid number");
            }

            return Category.list.remove(id) == null ? sendError(req, res, "No category found with id "+id) : sendSuccess(req, res, "Category successfully deleted!");
        });

        put("/category/:id", (req, res) -> {
            int id;
            Category newc;
            try {
                id = Integer.parseInt(req.params("id"));
            } catch (Exception e) {
                return sendError(req, res, "the id should be a valid number");
            }
            try {
                newc = check(convert.fromJson(req.body(), Category.class));
            } catch (Exception e) {
                return sendError(req, res, "Invalid json");
            }
            try {
                Category.set(id, newc); 
            } catch (Exception e) {
                return sendError(req, res, "No category found with id "+id);
            }
            return sendSuccess(req, res, "Item successfully modified!");
        });

    }

}
