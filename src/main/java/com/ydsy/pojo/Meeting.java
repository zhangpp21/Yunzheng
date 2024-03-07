package com.ydsy.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meeting {

  private int meetingId;
  private String meetingName;
  private int creatorId;
  private String meetingContent;


 
}
