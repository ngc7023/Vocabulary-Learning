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
  <script type="text/javascript" src="./jquery.min.js"></script>
  <style>
    .submit{
      background-color: #70bb8b; color:#FFF;
    }
  </style>
</head>
<jsp:useBean id="PrintList" scope="application" class="pack.ListManage"/>
<%String username=request.getSession().getAttribute("myName").toString();%>
<%String userid=request.getSession().getAttribute("myId").toString();%>
<body>
<table border="0" width="100%" cellspacing="3" cellpadding="3">
  <tr>
    <td>
      <div style="height:17px"></div>
      <h1 align="center">背诵统计</h1>
    </td>
  </tr>
  <tr>
    <td  align="center" width="100%" style="margin-left: 20%">
      <div style="width:80%;" align="center" >
        <table align="center">
          <tr><h5 align="center">*Tip:选择词表和时长，点击提交按钮</h5></tr>
          <tr>
            <td>
              <div style="width:10%;float:left">
              <select id="Selection">
                <%
                  String myId=request.getSession().getAttribute("myId").toString();
                  Vector v = (Vector)PrintList.getResult(myId);
                  if(v==null)
                  {
                    out.print("<option>None</option>");
                  }
                  else {
                    Enumeration enum1 = v.elements();
                    while (enum1.hasMoreElements()) {
                      String listname = enum1.nextElement().toString();
                      out.print("<option>" + listname + "</option>");
                    }
                    out.print("<option>" + "AllList" + "</option>");
                  }
                %>
              </select>
            </div></td>
            <td>
              <div style="width:10%;float:left; margin-left: 10px">
                <select id="Selection2">
                  <option value="30">1 Month</option>
                 <option value="365">1 Year</option>
                </select>
              </div>
            </td>
            <td>
              <div style="width:10%;float:left;margin-left: 10px">
                <input class="submit" type="submit" onclick="trigServlet()">
              </div>
            </td>
          </tr>
        </table>
      </div>
    </td>
  </tr>
  <tr>
    <td align="center" width="100%">
      <div style="width:90%">
        <div style="width:50%;float:left">
          <div style="width:100%;float:right">
            <img src="images/chart1.png" withth="500px" height="350px" id="PieChart">
          </div>
        </div>
        <div style="width:50%;float:right">
          <div style="width:100%;float:left">
            <img src="images/chart2.png" withth="500px" height="350px" id="BarChart">
          </div>
        </div>
      </div>
    </td>
  </tr>
  <script>
    function trigServlet()
    {
      var obj = document.getElementById("Selection"); //定位id
      var index = obj.selectedIndex; // 选中索引
      var listname = obj.options[index].value; // 选中值
      console.log(listname);
      console.log(index);

      var obj2 = document.getElementById("Selection2"); //定位id
      var index2 = obj2.selectedIndex; // 选中索引
      var timelength = obj2.options[index2].value; // 选中值
      $('#PieChart').attr("src","/ReciteWordHappily/PieChartServlet?listname="+listname);
      $('#BarChart').attr("src","/ReciteWordHappily/BarChartServlet?listname="+listname+"&timelength="+timelength);
    }
  </script>
  </tr>
</table>
</body>
</html>
