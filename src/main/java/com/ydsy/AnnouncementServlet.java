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
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;



@WebServlet("/AnnouncementServlet")
public class AnnouncementServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取 AnnouncementMapper 实例
            AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);


            // 从请求中获取通知内容、方向、期数、用户ID
            String content = req.getParameter("content");
            String direction = req.getParameter("direction");
            int period = Integer.parseInt(req.getParameter("period"));
            int userId = Integer.parseInt(req.getParameter("userId")); // 假设从请求中获取用户ID

            // 创建 Announcement 对象
            Announcement announcement = new Announcement();
            announcement.setContent(content);
            announcement.setDirection(direction);
            announcement.setPeriod(period);
            announcement.setUserId(userId);

            // 设置创建时间为当前时间
            announcement.setCreatedAt(new Date());

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型和字符编码
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // 获取公告列表（这里假设有一个 AnnouncementService 类来提供服务）
        AnnouncementService announcementService = new AnnouncementService();
        List<Announcement> announcements = announcementService.getAllAnnouncements();

        // 输出 HTML 页面内容
        PrintWriter out = response.getWriter();
        out.println("<!doctype html>");
        out.println("<html lang=\"zh\">");
        out.println("<head>");
        out.println("<title>公告列表</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>公告列表</h1>");

        // 输出公告信息列表
        out.println("<ul>");
        for (Announcement announcement : announcements) {
            out.println("<li>" + announcement.getContent() + "</li>");
        }
        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }
}
