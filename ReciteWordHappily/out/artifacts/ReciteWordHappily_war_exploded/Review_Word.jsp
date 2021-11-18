<%--
  Created by IntelliJ IDEA.
  User: 14312
  Date: 2019/6/12
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*"%>
<%@ page import="pack.JDBCUtils" %>
<html>
<style type="text/css">
    table
    {
        border:0;
        margin: 0 auto;
        text-align: center;
    }
    table td, table th
    {
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
<head>
</head>
<body>
<%
    String tablename=request.getParameter("tablename");//int a1=5;
    // int count1=(Integer)session.getAttribute("count");
    String c1=null;
    String c2=null;
    String c3=null;
    String c4=null;
    int q=0;
    double EF=0;
    double I=0;
    int n=0;
    try
    {
        Connection conn = (Connection) JDBCUtils.getConnection();
        // String sql="select * from `"+tablename+"` where (select timestampdiff(day,latest_time,DATE(now()))>I order by rand() LIMIT 1";
        String sql="select * from `"+tablename+"`where TIMESTAMPDIFF(day,latest_time,Date(now()))>I order by rand() LIMIT 1";
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs=pre.executeQuery(sql);
        while(rs.next()) {
            String s1 = rs.getString("word_id");
            String s2 = rs.getString("word");
            String s3 = rs.getString("meaning");
            String s4 = rs.getString("latest_time");
            int s5=rs.getInt("q");
            double s6 = rs.getDouble("EF");
            double s7 = rs.getDouble("I");
            int s8 = rs.getInt("n");
            c1= s1;
            c2= s2;
            c3= s3;
            c4= s4;
            q= s5;
            EF= s6;
            I= s7;
            n= s8;
        }
        rs.close();
    }catch(Exception e)
    {
        e.printStackTrace();
    }
%>
<table width="100%" border="0">
    <tr style="background-color: white;border:0" >
        <td>
            <div style="height:100px"><label>&nbsp;</label></div>
        </td>
    </tr>
    <tr style="background-color: white;border:0">
        <td>
            <table align="center" width="50%"  class="table">
                <tr>
                    <td>
                        <div style="height:30px"></div>
                    </td>
                </tr>
                <thead>
                <tr>
                    <th>
                        <div style="height:70px;margin:0 auto;">
                            <label style="align-self: auto;font-size: 30px"><%=c2%></label>
                        </div>
                    </th>
                </tr>
                </thead >
                <tr>
                    <td>
                        <div style="height:100px;margin:0 auto;">
                            &nbsp;
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div style="height:30px">
                            <a href="callRGetMeaning.jsp?tablename=<%=tablename%>&wordid=<%=c1%>" >查看释义</a>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>


