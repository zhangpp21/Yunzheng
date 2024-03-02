package com.ydsy.pojo;

public class Meeting {
  private int meetingId;
  private String meetingName;
  private int creatorId;
  private String meetingContent;

  // getters and setters

  public Meeting() {
  }

  public int getMeetingId() {
    return meetingId;
  }

  public void setMeetingId(int meetingId) {
    this.meetingId = meetingId;
  }

  public String getMeetingName() {
    return meetingName;
  }

  public void setMeetingName(String meetingName) {
    this.meetingName = meetingName;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }

  public String getMeetingContent() {
    return meetingContent;
  }

  public void setMeetingContent(String meetingContent) {
    this.meetingContent = meetingContent;
  }

  public Meeting(int meetingId, String meetingName, int creatorId, String meetingContent) {
    this.meetingId = meetingId;
    this.meetingName = meetingName;
    this.creatorId = creatorId;
    this.meetingContent = meetingContent;
  }
}
