package com.ydsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Participant {
    private int participantId;
    private int meetingId;
    private int userId;
    private String signInStatus;
    private String signOutStatus;
    private String leaveStatus;


}
