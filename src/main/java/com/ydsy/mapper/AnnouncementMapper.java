package com.ydsy.mapper;


import com.ydsy.pojo.Announcement;

public interface AnnouncementMapper {
    Announcement getAnnouncementById(int id);

    void insertAnnouncement(Announcement announcement);

    // 其他可能的方法，比如更新公告内容、删除公告等

}
