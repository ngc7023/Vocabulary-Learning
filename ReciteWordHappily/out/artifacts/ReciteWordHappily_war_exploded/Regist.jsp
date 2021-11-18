<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Regist</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            type="text/javascript"></script>
    <style type="text/css">
        body{
            background-image: url(image/background.jpg);
            background-size:cover;
        }
        .submit{
            width:143px; height:40px; background-color: #70bb8b; color:#FFF;

        }
        .input{

            height: 30px;
        }
        td{
            height: 10px;
        }

    </style>

    <script type="text/javascript">
        function doSubmit1(){
            var registerName = document.getElementById("registerName").value;
            var psw = document.getElementById("psw").value;
            if(registerName==""){
                alert("用户名不能为空");
                document.getElementById("registerName").focus();
                return false;
            }
            if(registerName!=""&&psw==""){
                alert("密码名不能为空");
                document.getElementById("psw").focus();
                return false;
            }
            else{
                return true;
            }
        }
    </script>

</head>

<body>


<div id="main">
    <form action="toCheckRegist" method="post" onsubmit="return doSubmit1()">
        <table align="center">
            <tr>
                <td colspan="2"><center>
                    <h1>注册</h1>
                </center></td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text"  class="input" name="registerName" id="registerName"
                           placeholder="设置您的用户名"> <span id="tishi1"></span></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" class="input" name="psw" id="psw" placeholder="设置您的密码"></td>

            </tr>
            <tr>
                <td>确认密码：</td>
                <td><input type="password" class="input" name="rpsw" id="rpsw" placeholder="请确认您的密码"></td>
                <td><font color="red" size="2"> ${MSG1}</font></td>
            </tr>
            <tr>
                <td>登录天数：</td>
                <td><input type="text" class="input" name="last_day" id="last_day" value="0" readonly ></td>
            </tr>
            <tr>
                <td>排名：</td>
                <td><input type="text" class="input" name="rank" id="rank" value="0" readonly></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="text" class="input" name="gender" id="gender"  placeholder="请确认您的性别"></td>
            </tr>
            <tr>
                <td>生日：</td>
                <td><input type="text" class="input" name="birthday" id="birthday"  placeholder="请确认您的生日"></td>
            </tr>
            <tr>
                <td>地址：</td>
                <td><input type="text" class="input" name="location" id="location"  placeholder="请确认您的地址"></td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td><input type="text" class="input" name="email" id="email"  placeholder="请确认您的邮箱"></td>
            </tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>
            <tr>&nbsp;</tr>

            <tr>
                <td colspan="2"><center><input type="submit" class="submit" value="注册"></center></td>
            </tr>
        </table>
    </form>
    <a href="login.jsp" style="margin-left: 70px;"><center><font size="2"><i>返回登录</i>
    </font></center> </a>
</div>
</body>
</html>
