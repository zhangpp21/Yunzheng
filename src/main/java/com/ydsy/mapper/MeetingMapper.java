package com.ydsy.mapper;


import com.ydsy.pojo.Meeting;

public interface MeetingMapper {
    Meeting getMeetingById(int id);

    void insertMeeting(Meeting meeting);

    // 其他可能的方法，比如更新会议内容、删除会议等

}
