package com.ydsy.pojo;


import java.util.Date;

public class Announcement {
   private int id;
   private int creatorId;
   private String content;
   private String direction;
   private int period;
   private int userId; // 用户ID
   private Date createdAt; // 创建时间
   private int announcementId; // 公告ID

   // Getters and setters

   public Announcement() {
   }

   public Announcement(int id, String content, String direction, int period, int userId, Date createdAt, int announcementId) {
      this.id = id;
      this.creatorId = creatorId;
      this.content = content;
      this.direction = direction;
      this.period = period;
      this.userId = userId;
      this.createdAt = createdAt;
      this.announcementId = announcementId;
   }

   public Announcement(String 公告标题1, String 公告内容1) {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getDirection() {
      return direction;
   }

   public void setDirection(String direction) {
      this.direction = direction;
   }

   public int getPeriod() {
      return period;
   }

   public void setPeriod(int period) {
      this.period = period;
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   public int getAnnouncementId() {
      return announcementId;
   }

   public int getCreatorId() {
      return creatorId;
   }

   public void setCreatorId(int creatorId) {
      this.creatorId = creatorId;
   }

   public void setAnnouncementId(int announcementId) {
      this.announcementId = announcementId;
   }
}
