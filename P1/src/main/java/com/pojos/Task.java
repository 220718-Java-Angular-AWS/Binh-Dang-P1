package com.pojos;

import java.util.Objects;

public class Task {
    private Integer taskId;
    private String title;
    private String message;
    private Integer userId;
    private String Reimbursed;
    private Double amount;

    public Task() {
    }

    public Task(Integer taskId, String title, String message, Integer userId, Double amount, String Reimbursed) {
        this.taskId = taskId;
        this.title = title;
        this.message = message;
        this.userId = userId;
        this.amount = amount;
        this.Reimbursed = Reimbursed;

    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReimbursed() {
        return Reimbursed;
    }

    public void setReimbursed(String reimbursed) {
        Reimbursed = reimbursed;
    }


    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", message='" + title + '\'' +
                ", userId=" + userId +
                ", completed=" + Reimbursed +
                "}";
    }
    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return Objects.equals(taskId, task.taskId) && Objects.equals(title, task.title) && Objects.equals(message, task.message) && Objects.equals(userId, task.userId) && Objects.equals(Reimbursed, task.Reimbursed);
        }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, title, message, userId, Reimbursed);
    }
}
