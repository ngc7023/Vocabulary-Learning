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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


import com.mysql.jdbc.Connection;
import pack.LoginD;
import pack.Login;


@WebServlet(name = "Servlet")
public class toUserUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("registerName");
        String password = request.getParameter("psw");
        String rpsw = request.getParameter("rpsw");

        String gender=request.getParameter("gender");
        String birthday=request.getParameter("birthday");
        String location=request.getParameter("location");
        String email=request.getParameter("email");
        String id=request.getSession().getAttribute("id").toString();
        String last_day=request.getSession().getAttribute("last_day").toString();
        String rank=request.getSession().getAttribute("rank").toString();
        //得到表单输入的内容
        if(username==null||username.trim().isEmpty()){
            request.setAttribute("msg", "帐号不能为空");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }
        if(password==null||password.trim().isEmpty()){
            request.setAttribute("msg", "密码不能为空");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }
        if(!password.equals(rpsw)){
            request.setAttribute("msg", "两次输入的密码不同");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }
        if(gender==null||gender.trim().isEmpty()){
            request.setAttribute("msg", "性别不能为空");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }
        if(birthday==null||birthday.trim().isEmpty()){
            request.setAttribute("msg", "生日不能为空");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }
        if(location==null||location.trim().isEmpty()){
            request.setAttribute("msg", "地址不能为空");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }


        if(id==null||id.trim().isEmpty()){
            request.setAttribute("msg", "id不能为空");
            request.getRequestDispatcher("Update.jsp").forward(request, response);
            return;
        }
        LoginD update= new LoginD();

        update.UpdateInformation(username,password,last_day,rank,gender,birthday,location,email,id);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('修改成功！将跳转到我的词表！')</script>");
        response.sendRedirect("index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
