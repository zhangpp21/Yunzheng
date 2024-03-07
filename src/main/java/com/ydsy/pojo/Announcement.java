package com.ydsy.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Announcement {
   private int id;
   private int creatorId;
   private String content;
   private String direction;
   private int period;
   private int userId; // 用户ID
   private Date createdAt; // 创建时间
   private int announcementId; // 公告ID
}