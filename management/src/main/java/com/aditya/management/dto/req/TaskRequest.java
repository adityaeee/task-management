package com.aditya.management.dto.req;

import com.aditya.management.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    private String title;
    private String description;
    private Date dueDate;
    private String userId;
}
