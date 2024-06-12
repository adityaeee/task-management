package com.aditya.management.service.Impl;

import com.aditya.management.dto.req.TaskAssignmentRequest;
import com.aditya.management.entity.Task;
import com.aditya.management.entity.TaskAssignment;
import com.aditya.management.entity.User;
import com.aditya.management.repository.TaskAssignmentRepository;
import com.aditya.management.service.TaskAssignmentService;
import com.aditya.management.service.TaskService;
import com.aditya.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    private final TaskAssignmentRepository taskAssignmentRepository;
    private final TaskService taskService;
    private final UserService userService;


    @Override
    public TaskAssignment createTaskAssignment(TaskAssignmentRequest request) {
        User user = userService.getById(request.getUserId());
        Task task = taskService.getById(request.getTaskId());
        task.setStatus("Assigned");
        taskService.update(task);

        TaskAssignment taskAssignment = TaskAssignment.builder()
                .task(task)
                .user(user)
                .build();

        return taskAssignmentRepository.saveAndFlush(taskAssignment);
    }

    @Override
    public TaskAssignment getById(String id) {
        return findOrElseThrowError(id);
    }

    @Override
    public List<TaskAssignment> getAll() {
        return taskAssignmentRepository.findAll();
    }

    @Override
    public TaskAssignment update(TaskAssignment request) {
        TaskAssignment taskAssignment = findOrElseThrowError(request.getId());
        User user = userService.getById(request.getUser().getId());
        Task task =taskService.getById(request.getTask().getId());

        taskAssignment.setTask(task);
        taskAssignment.setUser(user);
        return taskAssignmentRepository.saveAndFlush(taskAssignment);
    }

    @Override
    public void delete(String id) {
        TaskAssignment taskAssignment = findOrElseThrowError(id);
        taskAssignmentRepository.delete(taskAssignment);
    }

    public TaskAssignment findOrElseThrowError (String id) {
        return taskAssignmentRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Assignment not found"));
    }
}
