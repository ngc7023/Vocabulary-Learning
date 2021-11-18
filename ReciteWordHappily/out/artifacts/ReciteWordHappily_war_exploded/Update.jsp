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

    <title>Update</title>

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
    <style type="text/css">
        .submit{
            width:143px; height:40px; background-color: #70bb8b; color:#FFF; font-family:幼圆;

        }
        .input{
            background-color: #F5FAFA;
            height:30px;
        }
        td{
        }
    </style>
</head>

<body>


<div id="main">

    <form action="toUserUpdate" method="post" onsubmit="return doSubmit1()">
        <table align="center">

            <tr>
                <td colspan="2"><center>
                    <h3>更新信息</h3>
                </center></td>
            </tr>
            <tr>
                <td>Id：</td>
                <%String id=request.getSession().getAttribute("id").toString();%>
                <td><input type="text" class="input" name="id" id="id"  value="<%=id %>" readonly></td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text" class="input" name="registerName" id="registerName"
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
                <td>登录天数：</td><%String last_day=request.getSession().getAttribute("last_day").toString();%>
                <td><input type="text" class="input" name="last_day" id="last_day" value="<%=last_day %>" readonly ></td>
            </tr>
            <tr>
                <td>排名：</td><%String rank=request.getSession().getAttribute("rank").toString();%>
                <td><input type="text" class="input" name="rank" id="rank" value="<%=rank %>" readonly></td>
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
                <td><input type="text"  class="input"name="email" id="email"  placeholder="请确认您的邮箱"></td>
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
                <td colspan="2"><center><input type="submit" class="submit" value="更新"></center></td>

            </tr>
        </table>
    </form>
</div>
</body>
</html>
