package com.Servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojos.Task;
import com.Services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class TaskServlet extends HttpServlet {
    private TaskService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Task servlet initializing...");
        this.service = new TaskService();
        this.mapper = new ObjectMapper();
        System.out.println("Task servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get task based off of user-id
        String param = req.getParameter("task-id");
        String steve = req.getParameter("user-id");
        if(param == null && steve == null) {
            //Return all
            List<Task> userList = service.getAllTasks();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else if (param != null) {
            //return all requests from specific user
            Integer taskId = Integer.parseInt(req.getParameter("task-id"));

            Task task = service.getTask(taskId);
            String json = mapper.writeValueAsString(task);
            resp.getWriter().println(json);
        } else if (steve != null) {
            //Return all with user id
            Integer userId = Integer.parseInt(req.getParameter("user-id"));
            List<Task> userList = service.getReadAllUserId(userId);

            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while (buffer.ready()) {
            builder.append(buffer.readLine());
        }

        String json = builder.toString();
        Task task = mapper.readValue(json, Task.class);
        service.saveTask(task);
        resp.setStatus(200);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //update a task based on the associated user ID
        Integer param = Integer.parseInt(req.getParameter("task-id"));
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while (buffer.ready()) {
            builder.append(buffer.readLine());
        }
        System.out.println("hello world");

        String json = builder.toString();

        Task updateTask = mapper.readValue(json, Task.class);
        updateTask.setTaskId(param);
        service.updateTask(updateTask);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //delete a user based on the task ID
        String param = req.getParameter("task-id");
        if(param == null) {
            System.out.println("invalid");
        } else {

            Integer taskId = Integer.parseInt(req.getParameter("task-id"));

            service.deleteTask(taskId);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}