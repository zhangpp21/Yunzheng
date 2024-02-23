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
public class Announcement {
   private int announcementId      ;
   private int userId              ;
   private String announcementContent ;
   private Timestamp createdAt           ;


}
