package com.ydsy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int userId;
    private String account;
    private String password;
    private String email;
    private String name;
    private int jobId;
    private String awards;
    private String personalSignature;
    private String studentId;
    private String majorClass;
    private String stage;
    private int directionId;


}
