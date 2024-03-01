package com.ydsy.mapper;

import com.ydsy.pojo.DirectionApplication;
import com.ydsy.pojo.User;

import java.util.List;

public interface DirectionApplicationMapper {

    /**
     * 存入新的方向申请到数据库
     * @param directionApplication
     */
    void insertNeoDirectionApplication(DirectionApplication directionApplication);

    List<DirectionApplication> selectAllByApplicantDirection(User manager);

    DirectionApplication selectByApplicationId(int applicationId);

    void updateApproverIdAndStatus(DirectionApplication directionApplication);
}
