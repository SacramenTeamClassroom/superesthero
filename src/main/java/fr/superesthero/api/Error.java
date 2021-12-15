package fr.superesthero.api;

import spark.Request;
import spark.Response;

public class Error {
    public static String sendError(Request req, Response res, String e) {
        return "{\"error\":\""+e+"\"}";
    }
}
