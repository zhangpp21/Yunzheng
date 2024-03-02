package com.ydsy.web.servlet;
import com.ydsy.mapper.UserMapper;
import com.ydsy.pojo.User;
import com.ydsy.service.UserService;
import com.ydsy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 接收用户数据
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // 封装用户对象
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setEmail(email);

        // 获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        // 程序生成的验证码，从Session获取
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        if(checkCodeGen != null) {
            // 比对验证码
            if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
                request.setAttribute("register_msg", "验证码错误");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取Mapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            // 检查用户名是否重复
            User existingAccount = userMapper.selectByAccount(account);
            if (existingAccount != null) {
                request.setAttribute("register_msg", "用户名已存在");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

            // 检查邮箱是否重复
            User existingEmail = userMapper.selectByEmail(email);
            if (existingEmail != null) {
                request.setAttribute("register_msg", "邮箱已被注册");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

            // 注册新用户
            userMapper.add(user);
            sqlSession.commit();

            // 注册成功，跳转到登录页面
            request.setAttribute("register_msg", "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }}
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}}