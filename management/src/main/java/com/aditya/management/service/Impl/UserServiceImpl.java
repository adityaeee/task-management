package com.aditya.management.service.Impl;

import com.aditya.management.entity.User;
import com.aditya.management.repository.UserRepository;
import com.aditya.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .createdAt(new Date())
                .build();
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getById(String id) {
        return findOrElseThrowError(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findByIsDeletedFalse();
    }

    @Override
    public User update(User request) {
        User user = findOrElseThrowError(request.getId());
       user.setName(request.getName());
       user.setEmail(request.getEmail());
       user.setPassword(request.getPassword());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(String id) {
        User user = findOrElseThrowError(id);
        user.setDeleted(true);
        userRepository.saveAndFlush(user);
    }

    public User findOrElseThrowError (String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (user.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return user;
    }
}
