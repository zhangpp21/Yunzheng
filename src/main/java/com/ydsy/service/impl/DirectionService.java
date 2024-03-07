package com.ydsy.service.impl;

import com.ydsy.mapper.DirectionMapper;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class DirectionService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 更新各方向人数
     */
    public void updateCount() {
        // 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        // DirectionMapper
        DirectionMapper mapper = sqlSession.getMapper(DirectionMapper.class);

        mapper.updateDirectionCount();

        sqlSession.commit();
        sqlSession.close();
    }
}
