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
public class LeaveRequest {
    private int leaveRequestId;
    private int applicantId;
    private Timestamp leaveRequestTime;
    private String leaveRequestReason;
    private int approveId;
    private int approveStatus;
}
