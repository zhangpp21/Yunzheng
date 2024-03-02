package com.ydsy.mapper;

import com.ydsy.pojo.Announcement;
import org.apache.ibatis.annotations.Insert;

public interface AnnouncementMapper {
    @Insert("INSERT INTO announcements (announcement_content, direction, period, user_id, created_at) " +
            "VALUES (#{content}, #{direction}, #{period}, #{userId}, #{createdAt})")
    void insertAnnouncement(Announcement announcement);
}
