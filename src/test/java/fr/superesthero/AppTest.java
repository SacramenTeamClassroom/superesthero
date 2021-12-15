package fr.superesthero;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import fr.superesthero.models.Category;
import fr.superesthero.models.Hero;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Before
    static void  setup() {
        Category.list = new HashMap<>();
        Category.add("comic");
        
        Hero.list = new HashMap<>();
        Hero.add("Clark", "superman", new ArrayList<Category>(List.of(Category.get(0))));
    }

    @Test
    public void heroGetAll() throws ClientProtocolException, IOException {

        HttpGet request = new HttpGet("http://localhost:4567/hero");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            assertEquals("Response status should be 200", 200, response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void heroGetOne() throws ClientProtocolException, IOException {

        HttpGet request1 = new HttpGet("http://localhost:4567/hero/0");
        try (CloseableHttpResponse response = httpClient.execute(request1)) {
            assertEquals("Response status should be 200", 200, response.getStatusLine().getStatusCode());
        }

        HttpGet request2 = new HttpGet("http://localhost:4567/hero/a");
        try (CloseableHttpResponse response = httpClient.execute(request2)) {
            assertEquals("Response status should be 400", 400, response.getStatusLine().getStatusCode());
        }

        HttpGet request3 = new HttpGet("http://localhost:4567/hero/99999");
        try (CloseableHttpResponse response = httpClient.execute(request3)) {
            assertEquals("Response status should be 404", 404, response.getStatusLine().getStatusCode());
        }

    }
}
