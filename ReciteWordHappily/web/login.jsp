<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Login-Recite Word Happily </title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script type="text/javascript">
        function doSubmit(){
            var myLoginName = document.getElementById("loginName").value;
            if(myLoginName==""){
                alert("用户名不能为空");
                document.getElementById("loginName").focus();
                return false;
            }
            else{
                return true;
            }
        }
    </script>

    <style type="text/css">
        #main {
            position: fixed;
            width: 400px;
            height: 300px;
            top: 70%;
            left: 60%;
            margin-top: -300px;
            margin-left: -200px;
        }

        input {
            width: 100%;
            height: 30px;
        }
        body{
            background-image: url(images/background.jpg);
            background-size:cover;
        }
        loginName{
            border-radius: 4px;
        }
        password{
            border-radius: 4px;
        }
    </style>

</head>

<body>

<!-- 页面的Form表单 用来和后端建立交互的关键点 -->
<div id="main" >
    <form name="f1" id="f1" action="toCheckLogin" method="post"
          onsubmit="return doSubmit();">
        <table>
            <tr>
                <td><center>
                    <h2 style="color:black;font-family: Arial">Recite Word Happily</h2>
                </center>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td><center>
                    <h3>开心背单词 欢迎你</h3>
                </center>
                </td>
            </tr>
            <tr>
                <td><input type="text" name="loginName" id="loginName" placeholder="用户名">
                </td>
            </tr>
            <tr>
                <td><input type="password" name="loginPwd" id="password" placeholder="密码">
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td align="center"><input type="submit" value="登录">
                </td>
            </tr>

        </table>
    </form>
    <a href="Regist.jsp" style="margin-left: 40px;"><font size="2"><i>没有帐号？点击注册</i></font></a>
    <br>
</div>

</body>
</html>
