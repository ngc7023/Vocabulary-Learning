package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import pack.LoginD;
import pack.Login;

import static java.lang.System.out;


@WebServlet(name = "toCheckRegist")
public class toCheckRegist extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("registerName");
        String password = request.getParameter("psw");
        String rpsw = request.getParameter("rpsw");
        String last_day=request.getParameter("last_day");
        String rank=request.getParameter("rank");
        String gender=request.getParameter("gender");
        String birthday=request.getParameter("birthday");
        String location=request.getParameter("location");
        String email=request.getParameter("email");
        //得到表单输入的内容
        if(username==null||username.trim().isEmpty()){
            request.setAttribute("msg", "帐号不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(password==null||password.trim().isEmpty()){
            request.setAttribute("msg", "密码不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(!password.equals(rpsw)){
            request.setAttribute("msg", "两次输入的密码不同");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(gender==null||gender.trim().isEmpty()){
            request.setAttribute("msg", "性别不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(birthday==null||birthday.trim().isEmpty()){
            request.setAttribute("msg", "生日不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(location==null||location.trim().isEmpty()){
            request.setAttribute("msg", "地址不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(last_day==null||last_day.trim().isEmpty()){
            request.setAttribute("msg", "注册日期不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        if(rank==null||rank.trim().isEmpty()){
            request.setAttribute("msg", "排名不能为空");
            request.getRequestDispatcher("Regist.jsp").forward(request, response);
            return;
        }
        LoginD RegistService = new LoginD();
        RegistService.RegistNameAndPwd(username,password,last_day,rank,gender,birthday,location,email);
        out.print("<script language='javascript'>alert('用户注册成功！');window.location.href='login.jsp';</script>");
        response.sendRedirect("login.jsp");





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
