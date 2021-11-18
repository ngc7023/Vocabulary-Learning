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
</head>
<jsp:useBean id="PrintList" scope="application" class="pack.ListManage"/>
<jsp:useBean id="PrintWorkLoad" scope="application" class="pack.ListManage"/>
<%String userid=request.getSession().getAttribute("myId").toString();%>
<style>
  li{list-style-type:none;}
</style>
<body>
<table border="0" width="100%" align="center" cellspacing="3" cellpadding="3">
  <tr align="center">
    <div height="30px"></div>
    <h1 align="center" style="margin-top: 50px">我的词表</h1>
  </tr>
  <tr>
    <a href="AddList.jsp" style="float:right;font-size:20px">➕增加词表</a>
  </tr>
  <tr>
    <td width="100%" align="center">
      <%
        String myId=request.getSession().getAttribute("myId").toString();
        Vector v = (Vector)PrintList.getResult(myId);
        if(v==null)
        {
          out.println("You have no list! Try to add one!");
        }
        else{
          Enumeration enum1= v.elements();
          while(enum1.hasMoreElements()){
            String listname = enum1.nextElement().toString();
      %>
      <script>
        function del(data)
        {
          if(window.confirm("您确定要删除吗？")){
            var name = data.name;
            document.location="DeleteListServer?listname="+name;
          }
        }
        function ToSet(data)
        {
          var name = data.name;
          name = "div_"+name;
          $('#'+name).show();
        }
        function Set(name)
        {
          var divname = "div_"+name;
          var $inputs = $('#'+divname).children();
          // for(i=0;i<3;i++){
          //   alert($inputs[i].value);
          // }
          var learn_num = $inputs[0].value;
          var review_num =$inputs[2].value;
          document.location="SetListServlet?learn="+learn_num+"&review="+review_num+"&listname="+name;
        }
        function Cancel(name) {
          var divname = "div_" + name;
          $('#' + divname).hide();
        }
      </script>
      <ul align="center">
        <li align="center">
          <script>var listname ='<%=listname%>' </script>
          <a href="" id='<%=listname%>' style="color: #007aff;font-size:18px;margin-right:500px"><%=listname%></a>
          <input type="button" name='<%=listname%>' align="right" value="设置任务量" onclick="ToSet(this)"></input>
          <input type="button" name='<%=listname%>' align="right"  value="删除" onclick='del(this)'>
          <%
            int res[] = new int[2];
            res= PrintWorkLoad.GetWordload(userid,listname);
            int input1 = res[0];
            int input2 = res[1];
          %>
          <div style="display:none;margin: 10px 0px 0px 600px" class="div_set">
            学习量：<input type="text" width="80px" value=<%=input1%>></input><br>
            复习量：<input type="text" width="80px" value=<%=input2%>></input><br>
            <input type="submit" style="margin-top: 5px" name="<%=listname%>" onclick="Set(this.name)">
            <input type="button" style="margin-top: 5px" name="<%=listname%>" onclick="Cancel(this.name)" value="取消">
          </div>
          <hr/>
          <br/>
          <script>
            var url="ListPage.jsp?listname="+listname
            $("#"+listname).attr("href",url);
            var id = "div_"+listname;
            $("#"+listname).next().next().next().attr("id",id);
          </script>
        </li>
      </ul>
      <%}}%>
    </td>
  </tr>
</table>
</body>
</html>
