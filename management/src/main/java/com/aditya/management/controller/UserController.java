package com.aditya.management.controller;

import com.aditya.management.constant.PathAPI;
import com.aditya.management.constant.ResponseMessage;
import com.aditya.management.dto.res.CommonResponse;
import com.aditya.management.entity.User;
import com.aditya.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathAPI.USER)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CommonResponse<User>> createNewUser (@RequestBody User request) {
        User user = userService.createUser(request);

        CommonResponse<User> response = CommonResponse.<User>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<User>>> getAllUsers () {
        List<User> users = userService.getAll();

        CommonResponse<List<User>> response = CommonResponse.<List<User>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(users)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<User>> getUserById (@PathVariable String id) {
        User user = userService.getById(id);

        CommonResponse<User> response = CommonResponse.<User>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<User>> updateUser (@RequestBody User request) {
        User user = userService.update(request);

        CommonResponse<User> response = CommonResponse.<User>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(user)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<String>> DeleteUser (@PathVariable String id) {
        userService.delete(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data("Deleted data user")
                .build();

        return ResponseEntity.ok(response);
    }

}
