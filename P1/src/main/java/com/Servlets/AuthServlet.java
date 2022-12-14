package com.Servlets;

import com.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    ObjectMapper mapper;
    UserService service;

    @Override
    public void init() throws ServletException {
        this.mapper = new ObjectMapper();
        this.service = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        User user = mapper.readValue(json, User.class);
        User authUser = service.authenticate(user.getUsername(), user.getPassword());

        if(authUser != null) {
            resp.setStatus(200);
            resp.getWriter().write(mapper.writeValueAsString(authUser));
            resp.addHeader("JWT", String.valueOf(authUser.getUserId()));
        } else {
            resp.setStatus(403);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ows = req.getHeader("JWT");
        System.out.println("JWT header: " + ows);
    }

}
