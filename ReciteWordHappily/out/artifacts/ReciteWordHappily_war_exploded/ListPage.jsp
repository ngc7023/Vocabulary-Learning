<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/10
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*"%>
<%@ page import="pack.JDBCUtils" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="Content-Language" content="zh-cn">
  <meta name="GENERATOR" content="Microsoft FrontPage 4.0">
  <meta name="ProgId" content="FrontPage.Editor.Document">
  <h>
    <%String username=request.getSession().getAttribute("myName").toString();%>
    <%String userid=request.getSession().getAttribute("myId").toString();%>
  </h>
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
      var divname = "div_set";
      $('#'+divname).show();
    }
    function Set(name)
    {
      var divname = "div_set";
      var $inputs = $('#'+divname).children();
      var learn_num = $inputs[0].value;
      var review_num =$inputs[2].value;
      document.location="SetListServlet?learn="+learn_num+"&review="+review_num+"&listname="+name;
    }
    function Cancel(name) {
      var divname = "div_" + name;
      $('#' + divname).hide();
    }
  </script>
  <%
    String tablename=null;
    int workloadlearn=0;
    int workloadreview=0;
    String listid=null;
    int learnnumber=0;
    int reviewnumber=0;
    Connection conn = (Connection) JDBCUtils.getConnection();
    String listname=request.getParameter("listname");
    String sql="select list_id,table_name from `user&list` where `user_id`="+userid+" and `list_name`='"+listname+"'";
    PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
    ResultSet rs=pre.executeQuery();
    while(rs.next()) {
      String s1=rs.getString("list_id");
      String s2 = rs.getString("table_name");
      listid=s1;
      tablename=s2;
    }
    rs.close();
    Connection conn1 = (Connection) JDBCUtils.getConnection();
    String sql1="select learn_number,review_number from `statistic_number` where time=date(now()) and `user_id`="+userid+" and `list_id`='"+listid+"'";
    PreparedStatement pre1 = (PreparedStatement) conn1.prepareStatement(sql1);
    ResultSet rs1=pre1.executeQuery();
    while(rs1.next()) {
      int s1 = rs1.getInt("learn_number");
      int s2 = rs1.getInt("review_number");
      learnnumber=s1;
      reviewnumber=s2;
    }
    rs1.close();
    Connection conn2 = (Connection) JDBCUtils.getConnection();
    String sql2="select `workload_learn`, `workload_review` from `User&List` where user_id='"+userid+"' and list_id="+listid;
    PreparedStatement pre2 = (PreparedStatement) conn1.prepareStatement(sql2);
    ResultSet rs2=pre2.executeQuery();
    while(rs2.next()) {
      int s1 = rs2.getInt("workload_learn");
      int s2 = rs2.getInt("workload_review");
      workloadlearn=s1;
      workloadreview=s2;
    }
    rs2.close();
  %>
</head>
<jsp:useBean id="PrintWorkLoad" scope="application" class="pack.ListManage"/>
<body>
<table border="0" width="100%">
  <tr>
    <td width="10%">
      <div style="width:90%;float:left"></div>
    </td>
    <td width="90%">
      <div style="width:90%;float:left">
        <table>
          <tr width="100%">
            <td>
              <label style="font-size:65px"><%=request.getParameter("listname")%></label></tr>
          <%
            int res[] = new int[2];
            res= PrintWorkLoad.GetWordload(userid,request.getParameter("listname"));
            int input1 = res[0];
            int input2 = res[1];
          %>
          </td>
          </tr>
          <tr>
            <td>
              <div style="height:30px;width: 100%"></div>
              <div style="width:100%" >
                <table>
                  <tr>
                    <td>
                      <label style="font-size:20px">今日学习量：</label>
                    </td>
                    <td width="100px">&nbsp;</td>
                    <td>
                      <label style="font-size:20px"><%=input1%></label>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <label style="font-size:20px">已完成：</label>
                    </td>
                    <td width="100px">&nbsp;</td>
                    <td>
                      <label style="font-size:20px"><%=learnnumber%></label>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <label style="font-size:20px">今日复习量：</label>
                    </td>
                    <td width="100px">&nbsp;</td>
                    <td>
                      <label style="font-size:20px"><%=input2%></label>
                    </td>
                  </tr>
                  <tr>
                  <tr>
                    <td>
                      <label style="font-size:20px">已完成：</label>
                    </td>
                    <td width="100px">&nbsp;</td>
                    <td>
                      <label style="font-size:20px"><%=reviewnumber%></label>
                    </td>
                  </tr>
                </table>
              </div>
              <div style="height:60px;width: 100%">
                &nbsp;
                &nbsp;
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <div  style="width:100%;float:left">
                <script>var tablename ='<%=tablename%>'</script>
                <div  style="width:100%;float:left">
                  <%
                    if(learnnumber<workloadlearn)
                    {
                  %>
                    <input type="button" value="学习" style="width:200px;height:60px;font-size:32px;color:#fffaf5;background-color: #5390ee " onclick="learn(tablename)">
                  <%
                    }
                    else{
                      out.println("今日学习完毕");
                    }
                  %>
                </div>
                <div style="width:100%">
                  <label style="height:1px">&nbsp;</label>
                </div>
                <div  style="width:100%;float:left">
                  <%
                    if(reviewnumber<workloadreview)
                    {
                  %>
                  <input type="button" value="复习" style="width:200px;height:60px;font-size:32px;color:#fffaf5;background-color: #5390ee " onclick="review(tablename)">
                  <%
                    }
                    else{
                      out.println("今日复习完毕");
                    }
                  %>

                </div>
                <div style="width:100%">
                  <label style="height:2px">&nbsp;</label>
                </div>
                <div style="width:100%">
                  <table width="80%">
                    <tr>
                      <td>
                        <input type="button" value="设置" style="width:90px;height:35px;font-size:13px;color:#fffaf5;background-color: #5390ee " name='<%=listid%>' onclick="ToSet(this.name)">
                      </td>
                      <td>
                        <div>
                          <label style="width:20px">&nbsp;</label>
                        </div>
                      </td>
                      <td>
                        <input type="button" value="删除" style="width:90px;height:35px;font-size:13px;color:#fffaf5;background-color: #5390ee ">
                      </td>
                    </tr>
                    <tr>
                      <div style="display:none;margin: 10px 0px 0px 600px" id="div_set">
                        学习量：<input type="text" width="80px" value=<%=input1%>></input><br>
                        复习量：<input type="text" width="80px" value=<%=input2%>></input><br>
                        <input type="submit" style="margin-top: 5px" name="<%=listname%>" onclick="Set(this.name)">
                        <input type="button" style="margin-top: 5px" name="<%=listname%>" onclick="Cancel(this.name)" value="取消">
                      </div>
                    </tr>
                  </table>
                </div>
              </div>
            </td>
          </tr>
        </table>
      </div>
    </td>
  </tr>

</table>
<script>
  function learn(tablename)
  {
    if(window.confirm("开始学习！"))
    {
      document.location="Learn_Word.jsp?tablename="+tablename;
    }
  }
  function review(tablename)
  {
    if(window.confirm("开始复习！"))
    {
      document.location="Review_Word.jsp?tablename="+tablename;
    }
  }
</script>
</body>
</html>
