package ru.itvitality.sbrf.cu.l13.sws.servlets;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.itvitality.sbrf.cu.l13.sws.Appl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DataTest {
    private static Server server;
    private final static int PORT = 8081;


    @BeforeEach
    void startServer() throws Exception {
        Appl appl = new Appl();
        server = appl.createServer(PORT);
        server.start();
    }

    @AfterAll
    static void stopServer() throws Exception {
        server.stop();
    }

    private URL makeUrl(String part) throws MalformedURLException {
        return new URL("http://localhost:" + PORT + part);
    }

    @Test
    @DisplayName("test /data/testParam/")
    void doGet() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) makeUrl("/data/testParam").openConnection();
        connection.setRequestMethod("GET");
        assertEquals(HttpStatus.OK_200, connection.getResponseCode(), "doGet works");

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        assertEquals("from server:testParam", new Gson().fromJson(stringBuilder.toString(), String.class));
    }
}