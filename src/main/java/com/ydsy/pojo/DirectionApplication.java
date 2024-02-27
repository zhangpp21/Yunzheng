package com.ydsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DirectionApplication {
    private int applicationId;
    private int applicantId;
    private int applicationDirection;
    private int approverId;
    private int approvalStatus;
    private Timestamp createdAt;
}
