package com.aditya.management.controller;

import com.aditya.management.constant.PathAPI;
import com.aditya.management.constant.ResponseMessage;
import com.aditya.management.dto.req.TaskRequest;
import com.aditya.management.dto.res.CommonResponse;
import com.aditya.management.entity.Task;
import com.aditya.management.entity.Task;
import com.aditya.management.entity.Task;
import com.aditya.management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathAPI.TASK)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<CommonResponse<Task>> createNewTask (@RequestBody TaskRequest request) {
        Task task = taskService.createTask(request);

        CommonResponse<Task> response = CommonResponse.<Task>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(task)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Task>>> getAllTasks () {
        List<Task> tasks = taskService.getAll();

        CommonResponse<List<Task>> response = CommonResponse.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(tasks)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<CommonResponse<List<Task>>> searchTaskByKeyword (@RequestParam(name = "keyword") String keyword) {
        List<Task> tasks = taskService.searchTaskByKeyword(keyword);

        CommonResponse<List<Task>> response = CommonResponse.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(tasks)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/over-due")
    public ResponseEntity<CommonResponse<List<Task>>> searchTaskOverDue () {
        List<Task> tasks = taskService.serachOverdueTask(new Date());

        CommonResponse<List<Task>> response = CommonResponse.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(tasks)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/search/{userId}")
    public ResponseEntity<CommonResponse<List<Task>>> searchTaskByUserId (@PathVariable String userId) {
        List<Task> tasks = taskService.searchTaskByUserId(userId);

        CommonResponse<List<Task>> response = CommonResponse.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(tasks)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<Task>> getTaskById (@PathVariable String id) {
        Task task = taskService.getById(id);

        CommonResponse<Task> response = CommonResponse.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(task)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Task>> updateTask (@RequestBody Task request) {
        Task task = taskService.update(request);

        CommonResponse<Task> response = CommonResponse.<Task>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(task)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<String>> DeleteTask (@PathVariable String id) {
        taskService.delete(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data("Deleted data task")
                .build();

        return ResponseEntity.ok(response);
    }

}
