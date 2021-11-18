<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/10
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta name="GENERATOR" content="Microsoft FrontPage 4.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">
    <title>Home</title>
    <h>
    <%String username=request.getSession().getAttribute("myName").toString();%><%String userid=request.getSession().getAttribute("myId").toString();%>
    </h>
    <script type="text/javascript" src="./jquery.min.js"></script>
 <style>
   table
   {
     border-collapse: collapse;
     margin: 0 auto;
     text-align: center;
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
   .submit{
       width:143px; height:40px;
       font-size: 20px; background-color: #70bb8b; color:#FFF;
   }
 </style>
  </head>
  <jsp:useBean id="PrintUnselectedList" scope="application" class="pack.ListManage"/>
  <body>
  <h1 align="center">添加词表</h1>
                    <table width="100%">
                      <tr>
                        <p align="center">*向<%=username%>的词表库添加词表！</p>
                      </tr>
                      <tr>
                          <form action="/ReciteWordHappily/AddListServer" method="post"><br />
                            <div align="center">
                              <table width="220px">
                              <tr>
                                  <td>选择框</td>
                                <td>词表ID</td>
                                <td>词表名称</td>
                              </tr>
                              <%
                                String myId=request.getSession().getAttribute("myId").toString();
                                Vector v = (Vector) PrintUnselectedList.getUnselected(myId);
                                Enumeration enum1= v.elements();
                                while(enum1.hasMoreElements()){
                              %>
                              <tr>
                                <%String unselectedId = enum1.nextElement().toString();%>
                                <%String unselectedName =enum1.nextElement().toString();%>
                                  <td>
                                      <label><input name="addlist" type="checkbox" value=<%=unselectedName%>></label>
                                  </td>
                                  <td><%=unselectedId%></td>
                                  <td><%=unselectedName%></td>
                              </tr>
                              <%}%>
                            </table>
                            </div>
                              <br/>
                              <div align="center">
                              <input align="center" class="submit" type="submit" value="提交">
                              </div>
                          </form>
                      </tr>
                    </table>

  </body>
</html>
