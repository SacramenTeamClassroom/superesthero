package fr.superesthero.api;

import spark.Request;
import spark.Response;

public class Success {
    public static String sendSuccess(Request req, Response res, String e) {
        return "{\"success\":\""+e+"\"}";
    }
}