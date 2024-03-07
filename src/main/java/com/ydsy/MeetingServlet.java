
package com.ydsy;

import com.ydsy.pojo.Meeting;
import com.ydsy.mapper.MeetingMapper;
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


@WebServlet("/MeetingServlet")
public class MeetingServlet extends HttpServlet {
    private SqlSessionFactory sqlSessionFactory;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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
            // Set CORS headers
            resp.setHeader("Access-Control-Allow-Origin", "localhost:8080"); // Allow requests from any origin
            resp.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS"); // Allow POST and OPTIONS requests
            resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow Content-Type header

            // Check if this is a preflight request
            if ("OPTIONS".equals(req.getMethod())) {
                // This is a preflight request, so just return OK status
                resp.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            // 获取 MeetingMapper 实例
            MeetingMapper meetingMapper = sqlSession.getMapper(MeetingMapper.class);

            // 从请求中获取会议通知信息
            String meetingName = req.getParameter("meetingName");
            String meetingContent = req.getParameter("meetingContent");
            int creatorId = Integer.parseInt(req.getParameter("creatorId")); // 假设从请求中获取管理员ID

            // 创建 Meeting 对象
            Meeting meeting = new Meeting();
            meeting.setMeetingName(meetingName);
            meeting.setCreatorId(creatorId);
            meeting.setMeetingContent(meetingContent);

            // 插入会议通知到数据库
            meetingMapper.insertMeeting(meeting);

            // 提交事务
            sqlSession.commit();

            // 返回 JSON 数据给前端
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"message\": \"Meeting sent successfully\"}");
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
