package com.Services;

import com.DAOS.TaskDAO;
import com.pojos.Task;

import java.util.List;

public class TaskService {
    private TaskDAO dao;

    public TaskService() {
        this.dao = new TaskDAO();
    }

    public void saveTask (Task task) {
        dao.create(task);
    }

    public Task getTask(int id) {
        return dao.read(id);
    }

    public List<Task> getAllTasks() {
        return dao.readAll();
    }

    public List<Task> getTaskForUser(Integer userId) {
        List<Task> taskList = dao.readAll();
        for(Task task : taskList) {
            if(!task.getUserId().equals(userId)) {
                taskList.remove(task);
            }
        }
        return taskList;
    }

    public List<Task> getReadAllUserId(int userId) {
        return dao.readAllUserId(userId);
    }

    public void updateTask(Task task) {
        dao.update(task);
    }

    public void deleteTask(int id) {
        dao.delete(id);
    }
}
