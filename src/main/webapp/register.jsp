<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/GeneralServletDemo/registerServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="account" type="text" id="account">
                    <br>
                    <span id="account_err" class="err_msg" >${register_msg}</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" ></span>
                </td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td class="inputs">
                    <input name="email" type="email" id="email">
                    <br>
                    <span id="email_err" class="err_msg" >${register_msg}</span>
                </td>
            </tr>

            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="/GeneralServletDemo/checkCodeServlet">
                    <a href="#" id="changeImg" >看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/GeneralServletDemo/checkCodeServlet?"+new Date().getMilliseconds();
    }

</script>
</body>
</html>