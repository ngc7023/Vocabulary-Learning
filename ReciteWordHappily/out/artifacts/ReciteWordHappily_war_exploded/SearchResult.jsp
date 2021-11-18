<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/14
  Time: 10:34
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
<h1 align="center" id="biaotoucolor">检索结果</h1>

<table align="center">
    <tr>
        <td align="center" width="100" class="title" listname="title"><font size="4" color="#2f4f4f"><B>表名</B></font></td>
        <td align="center" width="100" class="title" word="title"><font size="4" color="#2f4f4f"><B>单词</B></font></td>
        <td align="center" width="100" class="title" meaning="title"><font size="4" color="#2f4f4f"><B>中文释义</B></font></td>
    </tr>
    <%
        Connection conn= JDBCUtils.getConnection();
        Statement stmt = conn.createStatement();
        String initial = request.getParameter("Initial");
        String sql="SELECT * FROM summary_wordlist WHERE word like '%"+initial+"%'" ;
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
    %>
    <tr>
        <td width="100"><%=rs.getString("list_name") %></td>
        <td width="100"><%=rs.getString("word") %></td>
        <td width="100"><%=rs.getString("meaning") %></td>
    </tr>
    <%
        }
    %>
</table>
<a href="SearchWord.jsp" style="margin-left: 40px;"><center><font size="2"><i>返回搜索</i></font></center></a>
</body>
</html>