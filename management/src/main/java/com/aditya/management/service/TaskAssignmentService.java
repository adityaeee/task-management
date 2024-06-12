package com.aditya.management.service;

import com.aditya.management.dto.req.TaskAssignmentRequest;
import com.aditya.management.entity.TaskAssignment;

import java.util.List;

public interface TaskAssignmentService {
    TaskAssignment createTaskAssignment(TaskAssignmentRequest request);
    TaskAssignment getById(String id);
    List<TaskAssignment> getAll();
    TaskAssignment update(TaskAssignment request);
    void delete(String id);
}
