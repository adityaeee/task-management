package com.aditya.management.service;

import com.aditya.management.entity.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team request);
    Team getById(String id);
    List<Team> getAll();
    Team update(Team request);
    void delete(String id);
}
