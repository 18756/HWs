package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DinamicServlet extends HttpServlet {

    private static List<Message> messages = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        String uri = request.getRequestURI();
        switch (uri) {
            case "/message/auth": {
                Object user = request.getParameter("user");
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                user = request.getSession().getAttribute("user");
                if (user != null) {
                    writer.write(new Gson().toJson(user));
                } else {
                    writer.write(new Gson().toJson(""));
                }
                break;
            }
            case "/message/findAll": {
                writer.print(new Gson().toJson(messages));
                break;
            }
            case "/message/add": {
                String user = (String) request.getSession().getAttribute("user");
                if (user != null) {
                    String text = request.getParameter("text");
                    if (text != null && text.length() > 0) {
                        messages.add(new Message(user, text));
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
                break;
            }
        }
        writer.close();
    }

    public static class Message {
        public Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
        String user;
        String text;
    }
}
