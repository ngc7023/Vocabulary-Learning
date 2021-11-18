import pack.ListManage;
import pack.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AddListServer")
public class AddListServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String[] list = request.getParameterValues("addlist");

        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String myId = request.getSession().getAttribute("myId").toString();
            for (int i = 0; i < list.length; i++) {
                ListManage lm = new ListManage();
                String listid = lm.getListId(list[i]);
                String listname = list[i];
                String tablename = myId + "_" + listid;
                String sql = "create table `" + tablename + "` as select * from `UID_LID`" + ";";
                String sql2 = "insert into "+tablename+"(word_id,word,meaning) select word_id,word,meaning from summary_wordlist where list_id = '"+listid+"';";
                String sql3 = "insert into `User&List`(user_id,list_id,list_name,table_name,workload_learn,workload_review) values (?,?,?,?,?,?);";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                PreparedStatement pre2 = (PreparedStatement) conn.prepareStatement(sql2);
                PreparedStatement pre3 = (PreparedStatement) conn.prepareStatement(sql3);

                try {
                    pre3.setString(1, myId);
                    pre3.setString(2, listid);
                    pre3.setString(3, listname);
                    pre3.setString(4, tablename);
                    pre3.setInt(5, 20);
                    pre3.setInt(6, 20);
                    int rs = pre.executeUpdate();
                    int rs2 = pre2.executeUpdate();
                    int rs3 = pre3.executeUpdate();
                    out.print("<script>alert('添加成功!将转到Desktop页面！');window.location.href='/ReciteWordHappily/Desktop.jsp'</script>");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //实现具体操作
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("This is my first servlet. doGet.");
        out.println("</body></html>");
    }
}
