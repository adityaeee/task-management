package com.aditya.management.controller;

import com.aditya.management.constant.PathAPI;
import com.aditya.management.constant.ResponseMessage;
import com.aditya.management.dto.req.TeamMemberRequest;
import com.aditya.management.dto.res.CommonResponse;
import com.aditya.management.entity.TeamMember;
import com.aditya.management.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathAPI.TEAM_MEMBER)
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @PostMapping
    public ResponseEntity<CommonResponse<TeamMember>> createNewTeamMember (@RequestBody TeamMemberRequest request) {
        TeamMember teamMember = teamMemberService.createTeamMember(request);

        CommonResponse<TeamMember> response = CommonResponse.<TeamMember>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(teamMember)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<TeamMember>>> getAllTeamMembers () {
        List<TeamMember> teamMembers = teamMemberService.getAll();

        CommonResponse<List<TeamMember>> response = CommonResponse.<List<TeamMember>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(teamMembers)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<TeamMember>> getTeamMemberById (@PathVariable String id) {
        TeamMember team = teamMemberService.getById(id);

        CommonResponse<TeamMember> response = CommonResponse.<TeamMember>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(team)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TeamMember>> updateTeamMember (@RequestBody TeamMember request) {
        TeamMember teamMember = teamMemberService.update(request);

        CommonResponse<TeamMember> response = CommonResponse.<TeamMember>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(teamMember)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<String>> DeleteTeamMember (@PathVariable String id) {
        teamMemberService.delete(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data("Deleted data team")
                .build();

        return ResponseEntity.ok(response);
    }
}
