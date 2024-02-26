package com.ydsy.pojo;

import cn.hutool.core.date.DateTime;
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
    private int userId;
    private DateTime leaveRequestTime;
    private String leaveRequestReason;
    private int approveId;
    private int approveStatus;
}
