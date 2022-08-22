package com.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.User;
import com.Services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    UserService service;
    ObjectMapper mapper;


    @Override
    public void init() {
        System.out.println("User servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter("user-id");
        if(param == null) {
            //Return all
            List<User> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else {
            //return the one the request wants
            Integer userId = Integer.parseInt(req.getParameter("user-id"));

            User user = service.getUser(userId);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        User newUser = mapper.readValue(json, User.class);
        service.saveUser(newUser);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer param = Integer.parseInt(req.getParameter("user-id"));
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while (buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String json = builder.toString();

        User updateUser = mapper.readValue(json, User.class);
        updateUser.setUserId(param);
        service.updateUser(updateUser);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String param = req.getParameter("user-id");
        if(param == null) {
            System.out.println("invalid");
        } else {
            //return the one the request wants
            Integer userId = Integer.parseInt(req.getParameter("user-id"));

            service.deleteUser(userId);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}