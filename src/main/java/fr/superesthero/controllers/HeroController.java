package fr.superesthero.controllers;

import static spark.Spark.get;

import fr.superesthero.models.Hero;

import static fr.superesthero.utils.JsonConverter.*;
import static fr.superesthero.api.Error.*;

public class HeroController {
    
    public static void init() {
    
        get("/hero", (req, res) -> {
            res.status(200);
            return convert.toJson(Hero.list);
        });

        get("/hero/:id", (req, res) -> {
            int id;
            try {
                id = Integer.parseInt(req.params("id"));
            } catch (Exception e) {
                res.status(400);
                return sendError(req, res, "the id should be a valid number");
            }
            var h = Hero.get(id);
            if (h==null) {
                res.status(404);
                return sendError(req, res, "No hero found with id "+id);
            }
            return convert.toJson(h);
        });

        get("/hero/:id/categories", (req, res) -> {
            int id;
            try {
                id = Integer.parseInt(req.params("id"));
            } catch (Exception e) {
                res.status(400);
                return sendError(req, res, "the id should be a valid number");
            }
            try {
                return convert.toJson(Hero.get(id).categories);
            } catch (Exception e) {
                res.status(404);
                return sendError(req, res, "No hero found with id "+id);
            }
        });

    }

}
