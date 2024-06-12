package com.aditya.management.controller;

import com.aditya.management.constant.PathAPI;
import com.aditya.management.constant.ResponseMessage;
import com.aditya.management.dto.res.CommonResponse;
import com.aditya.management.entity.Team;
import com.aditya.management.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathAPI.TEAM)
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<CommonResponse<Team>> createNewTeam (@RequestBody Team request) {
        Team team = teamService.createTeam(request);

        CommonResponse<Team> response = CommonResponse.<Team>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(team)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Team>>> getAllTeams () {
        List<Team> teams = teamService.getAll();

        CommonResponse<List<Team>> response = CommonResponse.<List<Team>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(teams)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<Team>> getTeamById (@PathVariable String id) {
        Team team = teamService.getById(id);

        CommonResponse<Team> response = CommonResponse.<Team>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(team)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Team>> updateTeam (@RequestBody Team request) {
        Team team = teamService.update(request);

        CommonResponse<Team> response = CommonResponse.<Team>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(team)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = PathAPI.ID)
    public ResponseEntity<CommonResponse<String>> DeleteTeam (@PathVariable String id) {
        teamService.delete(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .data("Deleted data team")
                .build();

        return ResponseEntity.ok(response);
    }

}
