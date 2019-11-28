package ru.itvitality.sbrf.cu.l13.sws.servlets;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Data extends HttpServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] requestParams = request.getPathInfo().split("/");
        String dataParam = "defaultValue";
        if (requestParams.length == 2) {
            dataParam = requestParams[1];
        }

        System.out.println("request params:" + dataParam);

        String resultAsString = gson.toJson("from server:" + dataParam);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultAsString);
        printWriter.flush();
    }
}
