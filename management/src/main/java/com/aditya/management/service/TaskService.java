package com.aditya.management.service;

import com.aditya.management.dto.req.TaskRequest;
import com.aditya.management.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest request);
    Task getById(String id);
    List<Task> getAll();
    List<Task> searchTaskByKeyword(String keyword);
    List<Task> serachOverdueTask(Date dueDate);
    List<Task> searchTaskByUserId(String userId);
    Task update(Task request);
    void delete(String id);
}
