package com.aditya.management.service.Impl;

import com.aditya.management.entity.Team;
import com.aditya.management.entity.Team;
import com.aditya.management.repository.TeamRepository;
import com.aditya.management.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team createTeam(Team request) {
        return teamRepository.saveAndFlush(request);
    }

    @Override
    public Team getById(String id) {
        return findOrElseThrowError(id);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team update(Team request) {
        Team team = findOrElseThrowError(request.getId());
        team.setName(request.getName());
        return teamRepository.saveAndFlush(team);
    }

    @Override
    public void delete(String id) {
        Team team = findOrElseThrowError(id);
        teamRepository.delete(team);
    }

    public Team findOrElseThrowError (String id) {
        return teamRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
    }
}
