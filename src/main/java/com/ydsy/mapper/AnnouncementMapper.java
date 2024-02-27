package com.ydsy.mapper;


import com.ydsy.pojo.Announcement;
import org.apache.ibatis.annotations.Insert;

public interface AnnouncementMapper {
    @Insert("INSERT INTO announcement (content, direction, period) VALUES (#{content}, #{direction}, #{period})")
    void insertAnnouncement(Announcement announcement);
}
