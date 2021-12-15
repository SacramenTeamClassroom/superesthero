package fr.superesthero.utils;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

public class JsonConverter {
    public static final GsonBuilder builder = new GsonBuilder();
    public static final Gson convert = builder.create();
    public static <T> T check(T o) throws IllegalAccessException, JsonParseException { 
        for (var f : o.getClass().getDeclaredFields()) {
            if(Arrays.stream(f.getAnnotations()).anyMatch(a->a.annotationType()==optionnal.class)) continue;
            if (f.get(o) == null) throw new JsonParseException("Field " + f.getName() + " was not initialized.");
        }
        return o;
    }

}
