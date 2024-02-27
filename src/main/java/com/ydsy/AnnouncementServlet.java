package com.ydsy;

import com.ydsy.pojo.Announcement;
import com.ydsy.mapper.AnnouncementMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "AnnouncementServlet",value = "/announcement-servlet")
public class AnnouncementServlet extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        // 初始化 SqlSessionFactory
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing SqlSessionFactory. Cause: " + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取 AnnouncementMapper 实例
            AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);

            // 从请求中获取通知内容、方向和期数
            String content = req.getParameter("content");
            String direction = req.getParameter("direction");
            int period = Integer.parseInt(req.getParameter("period"));

            // 创建 Announcement 对象
            Announcement announcement = new Announcement();
            announcement.setContent(content);
            announcement.setDirection(direction);
            announcement.setPeriod(period);

            // 插入通知到数据库
            announcementMapper.insertAnnouncement(announcement);

            // 提交事务
            sqlSession.commit();

            // 返回 JSON 数据给前端
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"message\": \"Announcement sent successfully\"}");
        } catch (IOException e) {
            // 处理 IO 异常
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException e) {
            // 处理数字格式异常
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
