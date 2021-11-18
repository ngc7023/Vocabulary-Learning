import pack.ListManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SetListServer")
public class SetListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //实现具体操作
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("This is my first servlet. doPost.");
        String listname = request.getParameter("listname");
        out.println(listname);
        out.println("</body></html>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //实现具体操作
        PrintWriter out = response.getWriter();
        int learn_num = Integer.valueOf(request.getParameter("learn"));
        int review_num = Integer.valueOf(request.getParameter("review"));
        String listname = request.getParameter("listname");
        String userid = request.getSession().getAttribute("myId").toString();
        ListManage gli = new ListManage();
        String listid = gli.getListId(listname);
        Boolean rs = gli.SetWordload(learn_num,review_num,userid,listid);
        if(rs.equals(true))
            out.print("<script>alert('设置成功!将转到Desktop页面！');window.location.href='/ReciteWordHappily/Desktop.jsp'</script>");
        else
            out.print("<script>alert('设置失败')</script>");
    }
}
