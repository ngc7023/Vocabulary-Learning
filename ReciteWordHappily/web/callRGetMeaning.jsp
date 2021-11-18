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
    String tablename=request.getParameter("tablename");
    String wordId=request.getParameter("wordid");
    //int a1=5;
    int count1=(Integer)session.getAttribute("count");
    String c2=null;
    String c3=null;
    // String c4=null;
    int q=0;
    double EF=0;
    double I=0;
    int n=0;
    try {
        Connection conn = (Connection) JDBCUtils.getConnection();
        String sql="select `word`,`meaning`,`latest_time`,`EF`,`I`,`n` from `"+tablename+"` where `word_id`="+wordId;
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs=pre.executeQuery();
        while(rs.next()) {
            String s2 = rs.getString("word");
            String s3 = rs.getString("meaning");
            String s4 = rs.getString("latest_time");
            double s6 = rs.getDouble("EF");
            double s7 = rs.getDouble("I");
            int s8 = rs.getInt("n");
            c2= s2;
            c3= s3;
            EF= s6;
            I= s7;
            n= s8;
        }
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    n=n+1;
    if(EF<1.3)
    {
        EF=1.3;
    }
    else
    {
        EF=EF+(0.1-(5-q)*(0.08+(5-q)*0.02));
    }
    if(n==1)
    {
        I=1;
    }
    if(n==2)
    {
        I=6;
    }
    if(n>2)
    {
        I=I*EF;
    }
    try
    {
        Connection conn = (Connection) JDBCUtils.getConnection();
        String sql1="update `"+tablename+"` set latest_time=now(),EF='"+EF+"',I='"+I+"',n='"+I+"' where word_id='"+wordId+"'";
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql1);
        pre.execute();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    // out.println(count1);
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
                        <div  style="height:70px;margin:0 auto;">
                            <label style="align-self: auto;font-size: 30px"><%=c2%></label>
                        </div>
                    </th>
                </tr>
                </thead>
                <tr>
                    <td>
                        <div  style="height:100px;margin:0 auto;">
                            <label style="align-self: auto;font-size: 25px"><%=c3%></label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div style="width:100%;height:30px">
                            <div style="width:50%;float:left">
                                <form action="RGetWord" method="post">
                                    <table border="0">
                                        <tr>
                                            <td><input type="hidden" name="tablename" value="<%=tablename%>">
                                                <input type="hidden" name="wordid" value="<%=wordId%>">
                                                <input type="submit" value="熟悉"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>

                            <div style="width:50%;float:right">
                                <form action="RGetUnknown" method="post">
                                    <table border="0">
                                        <tr>
                                            <td><input type="hidden" name="tablename" value="<%=tablename%>">
                                                <input type="hidden" name="wordid" value="<%=wordId%>">
                                                <input type="submit" value="不熟悉"></td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>


