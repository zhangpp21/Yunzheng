package com.ydsy;

import com.ydsy.mapper.AnnouncementMapper;
import com.ydsy.pojo.Announcement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

@WebServlet("/announcement")
public class AnnouncementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        // 创建 MyBatis 的配置对象
        String resource = "mybatis-config.xml"; // MyBatis 配置文件的路径
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new ServletException("Error initializing MyBatis", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从前端获取通知内容、用户ID和创建时间
        String content = request.getParameter("content");
        int userId = Integer.parseInt(request.getParameter("userId"));
        Timestamp createdAt = new Timestamp(System.currentTimeMillis()); // 创建当前时间戳

        // 创建通知对象
        Announcement announcement = new Announcement();
        announcement.setUserId(userId);
        announcement.setAnnouncementContent(content);
        announcement.setCreatedAt(createdAt);

        // 将通知插入数据库
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AnnouncementMapper mapper = sqlSession.getMapper(AnnouncementMapper.class);
            mapper.addAnnouncement(announcement);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        // 返回成功响应
        response.getWriter().println("Announcement added successfully");
    }
}
