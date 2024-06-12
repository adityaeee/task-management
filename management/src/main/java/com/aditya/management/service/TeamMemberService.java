package com.aditya.management.service;

import com.aditya.management.dto.req.TeamMemberRequest;
import com.aditya.management.entity.TeamMember;

import java.util.List;

public interface TeamMemberService {
    TeamMember createTeamMember(TeamMemberRequest request);
    TeamMember getById(String id);
    List<TeamMember> getAll();
    TeamMember update(TeamMember request);
    void delete(String id);
}
