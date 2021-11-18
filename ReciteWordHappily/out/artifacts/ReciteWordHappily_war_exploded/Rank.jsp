<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/18
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page import="pack.JDBCUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>vocabulary</title>
    <style type="text/css">
        table
        {
            border-collapse: collapse;
            margin: 0 auto;
            text-align: center;
            width: 70%;

        }
        table td, table th
        {
            border: 1px solid #cad9ea;
            color: #666;
            height: 30px;
        }
        table thead th
        {
            background-color: #CCE8EB;
            width: 100px;
        }
        table tr:nth-child(odd)
        {
            background: #F5FAFA;

        }
        table tr:nth-child(even)
        {
            background: #fff;
        }
    </style>

</head>
<body>
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<h1 align="center" id="biaotoucolor">用户排名</h1>
<table align="center">
    <tr>
        <td align="center" width="100" listname="title"><font size="4" color="#2f4f4f"><b>排名</b></font></td>
        <td align="center" width="100" word="title"><font size="4" color="#2f4f4f"><b>用户名</b></font></td>
        <td align="center" width="100" meaning="title"><font size="4" color="#2f4f4f"><b>性别</b></font></td>
        <td align="center" width="100" meaning="title"><font size="4" color="#2f4f4f"><b>打卡天数</b></font></td>
        <td align="center" width="100" meaning="title"><font size="4" color="#2f4f4f"><b>地区</b></font></td>
    </tr>
    <%
        Connection conn = (Connection) JDBCUtils.getConnection();
        String initial = request.getParameter("Initial");
        String sql="SELECT * FROM user order by rank";
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery(sql);
        while(rs.next()){
    %>
    <tr>
        <td width="100"><%=rs.getString("rank") %></td>
        <td width="100"><%=rs.getString("name") %></td>
        <td width="100"><%=rs.getString("gender") %></td>
        <td width="100"><%=rs.getString("last_day") %></td>
        <td width="100"><%=rs.getString("location") %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>