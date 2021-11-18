<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <link href="css/public.css" type="text/css" rel="stylesheet">
  <link href="css/houtai.css" type="text/css" rel="stylesheet">
  <link href="css/smartMenu.css" type="text/css" rel="stylesheet">
  <title>开心背单词</title>
  <script type="text/javascript" src="./jquery.min.js"></script>
</head>
<body>
<div id="admin">
  <div class="ad-menu" id="ad-menu" style="margin-top: 50px">
    <div class="ad-logo"> <h1 style="color: white;border-left: 3px; margin-top: 10px;margin-left: 5px;font-size: 20px;border: white;border-width: 1px;">^_^开心背单词</h1></div>
    <div class="ad-list">
      <ul>
        <li>
          <div class="li-item"><em class="scm li-ico ic3"></em><a href="SearchWord.jsp" target="frame0" style="color:white;font-size: 18px;">单词查询<span class="scm dd-ar"></span></a>
          </div>
        </li>
        <li>
          <div class="li-item"><em class="scm li-ico ic1"></em><a href="Desktop.jsp" target="frame0" style="font-size: 18px;color:white">我的词表<span class="scm dd-ar"></span></a>
          </div>
        </li>
        <li>
          <div class="li-item"><em class="scm li-ico ic7"></em><a href="Statistic.jsp" target="frame0" style="font-size: 18px;color:white">背诵统计<span class="scm dd-ar"></span></a>
          </div>
        </li>
        <li>
          <div class="li-item"><em class="scm li-ico ic4"></em><a href="Update.jsp" target="frame0" style="font-size: 18px;color:white">个人信息<span class="scm dd-ar"></span></a>
          </div>
        </li>
        <li>
          <div class="li-item"><em class="scm li-ico ic5"></em><a href="Rank.jsp" target="frame0" style="font-size: 18px;color:white">用户排名<span class="scm dd-ar"></span></a>
            </div>
        </li>
      </ul>
    </div>
  </div>
  <div class="ad-comment-box" id="ad-comment">
    <div class="ad-top-comment">
      <div class="ad-message">

        <div class="ad-top-right">

          <div class="ad-welcom">
            <div class="ad-wel-text">
              <div class="font-wel">
                <tr>
                  <%String id=request.getSession().getAttribute("id").toString();%>
                </tr>
                <tr><%String username=request.getSession().getAttribute("myName").toString();%>
                  Hello,<%=username%>!  </tr>
                <tr><a href="login.jsp">                                              <strong>【退出】</strong>   </a></tr></div> </tr>
            </div>
          </div>
        </div>
      </div>

      <div class="ad-main-comment J_mainContent" id="ad-iframe">
        <iframe class="J_iframe" name="frame0" width="100%" height="100%" src="Desktop.jsp" frameborder="0" seamless></iframe>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/contabs.js"></script>
  <script type="text/javascript" src="js/maintabs.js"></script>
  <script type="text/javascript" src="js/jquery-smartMenu-min.js"></script>
  <script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
  <script type="text/javascript">
    $(function(){
      $(".ad-menu").niceScroll({cursorborder:"0 none",cursorcolor:"#1a1a19",cursoropacitymin:"0",boxzoom:false});
    })
  </script>
</div>
</body>
</html>
