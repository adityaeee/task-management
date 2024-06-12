package com.aditya.management.service.Impl;

import com.aditya.management.dto.req.TeamMemberRequest;
import com.aditya.management.entity.Team;
import com.aditya.management.entity.TeamMember;
import com.aditya.management.entity.User;
import com.aditya.management.repository.TeamMemberRepository;
import com.aditya.management.service.TeamMemberService;
import com.aditya.management.service.TeamService;
import com.aditya.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final UserService userService;
    private final TeamService teamService;

    @Override
    public TeamMember createTeamMember(TeamMemberRequest request) {
        User user = userService.getById(request.getUserId());
        Team team = teamService.getById(request.getTeamId());

        TeamMember teamMember = TeamMember.builder()
                .user(user)
                .team(team)
                .build();
        return teamMemberRepository.saveAndFlush(teamMember);
    }

    @Override
    public TeamMember getById(String id) {
        return findOrElseThrowError(id);
    }

    @Override
    public List<TeamMember> getAll() {
        return teamMemberRepository.findAll();
    }

    @Override
    public TeamMember update(TeamMember request) {
        TeamMember teamMember = findOrElseThrowError(request.getId());
        User user = userService.getById(request.getUser().getId());
        Team team = teamService.getById(request.getTeam().getId());

        teamMember.setTeam(team);
        teamMember.setUser(user);
        return teamMemberRepository.saveAndFlush(teamMember);
    }

    @Override
    public void delete(String id) {
        TeamMember teamMember = findOrElseThrowError(id);
        teamMemberRepository.delete(teamMember);

    }

    public TeamMember findOrElseThrowError (String id) {
        return teamMemberRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team member not found"));
    }
}
