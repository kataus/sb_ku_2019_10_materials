package ru.itvitality.sbrf.cu.library.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BookServlet extends HttpServlet {
    private static final String page = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Библиотека</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<h1>Библиотека</h1>\n" +
            "\t<p>\n" +
            "\t\tИнфа о книге для библиотекаря\n" +
            "\t</p>\n" +
            "</body>\n" +
            "</html>";

    @Override
    public void doGet( HttpServletRequest req, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(page);
        printWriter.flush();
    }
}
