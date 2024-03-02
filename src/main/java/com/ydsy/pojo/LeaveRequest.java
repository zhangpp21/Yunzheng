package com.ydsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveRequest {
    private int leaveRequestId;
    private int applicantId;
    private int leaveRequestMeeting;
    private String leaveRequestReason;
    private int approveId;
    private int approveStatus;
}
