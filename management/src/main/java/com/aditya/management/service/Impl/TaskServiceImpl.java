package com.aditya.management.service.Impl;

import com.aditya.management.dto.req.TaskRequest;
import com.aditya.management.entity.Task;
import com.aditya.management.entity.User;
import com.aditya.management.repository.TaskRepository;
import com.aditya.management.service.TaskService;
import com.aditya.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public Task createTask(TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(new Date())
                .dueDate(request.getDueDate())
                .build();
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task getById(String id) {
        return findOrElseThrowError(id);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> searchTaskByKeyword(String keyword) {
        return taskRepository.searchTasksByKeyword(keyword);
    }

    @Override
    public List<Task> serachOverdueTask(Date dueDate) {
        return taskRepository.findOverdueTasks( dueDate);
    }

    @Override
    public List<Task> searchTaskByUserId(String userId) {
        return taskRepository.findTasksByUserId(userId);
    }

    @Override
    public Task update(Task request) {
        Task task = findOrElseThrowError(request.getId());
        task.setTitle(request.getTitle());
        task.setStatus(request.getStatus());
        task.setDescription(request.getDescription());
//        task.setUser(request.getUser());
        task.setDueDate(request.getDueDate());

        return taskRepository.saveAndFlush(task);
    }

    @Override
    public void delete(String id) {
        Task task = findOrElseThrowError(id);
        taskRepository.delete(task);
    }

    public Task findOrElseThrowError (String id) {
        return taskRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }
}
