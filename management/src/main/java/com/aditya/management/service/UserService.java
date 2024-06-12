package com.aditya.management.service;

import com.aditya.management.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User request);
    User getById(String id);
    List<User> getAll();
    User update(User request);
    void delete(String id);

}
