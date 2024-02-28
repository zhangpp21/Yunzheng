package com.ydsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Participation {
    private int participationId;
    private int participantId;
    private int meetingId;
    private int approveId;
    private String signInStatus;
    private String signOutStatus;
    private String leaveStatus;


}
