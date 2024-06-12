package com.aditya.management.controller;

import com.aditya.management.constant.PathAPI;
import com.aditya.management.constant.ResponseMessage;
import com.aditya.management.dto.req.TaskAssignmentRequest;
import com.aditya.management.dto.res.CommonResponse;
import com.aditya.management.entity.TaskAssignment;
import com.aditya.management.service.TaskAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathAPI.TASK_ASSIGNMENT)
public class TaskAssignmentController {

    private final TaskAssignmentService taskAssignmentService;

    @PostMapping
    public ResponseEntity<CommonResponse<TaskAssignment>> createNewTaskAssignment (@RequestBody TaskAssignmentRequest request) {
        TaskAssignment taskAssignment = taskAssignmentService.createTaskAssignment(request);

        CommonResponse<TaskAssignment> response = CommonResponse.<TaskAssignment>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(taskAssignment)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TaskAssignment>>> getAllTaskAssignments () {
        List<TaskAssignment> taskAssignments = taskAssignmentService.getAll();

        CommonResponse<List<TaskAssignment>> response = CommonResponse.<List<TaskAssignment>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(taskAssignments)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<TaskAssignment>> getTaskAssignmentById (@PathVariable String id) {
        TaskAssignment taskAssignment = taskAssignmentService.getById(id);

        CommonResponse<TaskAssignment> response = CommonResponse.<TaskAssignment>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(taskAssignment)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TaskAssignment>> updateTaskAssignment (@RequestBody TaskAssignment request) {
        TaskAssignment taskAssignment = taskAssignmentService.update(request);

        CommonResponse<TaskAssignment> response = CommonResponse.<TaskAssignment>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(taskAssignment)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<String>> DeleteTaskAssignment (@PathVariable String id) {
        taskAssignmentService.delete(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data("Deleted data taskAssignment")
                .build();

        return ResponseEntity.ok(response);
    }
}
