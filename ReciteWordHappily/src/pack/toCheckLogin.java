package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


import pack.JDBCUtils;
import static java.lang.System.out;
import javax.servlet.http.HttpSession;
import pack.LoginD;
import pack.Login;


@WebServlet(name = "toCheckLogin")
public class toCheckLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取表单传过来的数据
        String myName = request.getParameter("loginName");
        String myPwd = request.getParameter("loginPwd");
        //把获取到的数据传到数据库，进行判断
        LoginD loginService = new LoginD();
        boolean	isHave=loginService.searchNameAndPwd(myName, myPwd);

        if(isHave){
            request.getSession().setAttribute("myName", myName);
            Connection conn=(Connection) JDBCUtils.getConnection();
            try {
                String sql = "select user_id,last_day,rank from user where name='" + myName + "'";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = pre.executeQuery(sql);
                while (rs.next()) {
                    String id = rs.getString("user_id");
                    request.getSession().setAttribute("id", id);
                    String last_day = rs.getString("last_day");
                    String rank = rs.getString("rank");
                    request.getSession().setAttribute("last_day", last_day);
                    request.getSession().setAttribute("rank", rank);
                    request.getSession().setAttribute("myId", id);
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }


            //如果账号密码正确，登录成功
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else{
            //如果失败，报告错误
            request.getSession().setAttribute("MSG", "账号或密码错误");
            response.sendRedirect("login.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
