package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RGetWord")
public class RGetWord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String tableName = request.getParameter("tablename");
        String wordId = request.getParameter("wordid");
        //int rcount1 =(Integer) request.getSession().getAttribute("rcount");
        String username=request.getSession().getAttribute("myName").toString();
        String userid=request.getSession().getAttribute("myId").toString();
        int q=0;
        double EF=0;
        double I=0;
        int n=0;
        int workreview=0;
        String listid=null;
        try
        {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql="select `q`,`EF`,`I`,`n` from `"+tableName+"` where `word_id`="+wordId;
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while(rs.next())
            {
                q=rs.getInt("q");
                EF=rs.getDouble("EF");
                I=rs.getDouble("I");
                n=rs.getInt("n");
            }
            n=n+1;
            if(q<5)
            {
                q=q+1;
            }
            if(EF<1.3)
            {
                EF=1.3;
            }
            else
            {
                EF=EF+(0.1-(5-q)*(0.08+(5-q)*0.02));
            }
            if(n==1)
            {
                I=1;
            }
            if(n==2)
            {
                I=6;
            }
            if(n>2)
            {
                I=I*EF;
            }
            String sql1="update `"+tableName+"` set latest_time=now(),q=?,EF=?,I=?,n=? where word_id="+wordId;
            PreparedStatement pre1=(PreparedStatement) conn.prepareStatement(sql1);
            pre1.setInt(1, q);
            pre1.setDouble(2, EF);
            pre1.setDouble(3, I);
            pre1.setInt(4, n);
            pre1.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //response.sendRedirect("Learn_Word.jsp?tablename="+tableName);
        try
        {
            Connection conn1 = (Connection) JDBCUtils.getConnection();
            String sql="select list_id,workload_review from `user&list` where table_name='"+tableName+"'and user_id="+userid;
            PreparedStatement pre2 = (PreparedStatement) conn1.prepareStatement(sql);
            ResultSet rs1= pre2.executeQuery();
            while(rs1.next())
            {
                listid=rs1.getString("list_id");
                workreview=rs1.getInt("workload_review");
            }
            conn1.close();
            int rcount2=0;
            Connection conn2 = (Connection) JDBCUtils.getConnection();
            String sql4="select review_number from `Statistic_Number` where TIMESTAMPDIFF(day,time,Date(now()))=0 and user_id="+userid+" and list_id="+listid;
            PreparedStatement pre3 = (PreparedStatement) conn2.prepareStatement(sql4);
            ResultSet rs2= pre3.executeQuery();
            while(rs2.next())
            {
                rcount2=rs2.getInt("review_number");
            }
            conn1.close();
            if(rcount2 ==0)
            {
                // rcount2=1;
            }
            // rcount2=rcount1;
            //rcount2=rcount2+1;
            rcount2=rcount2+1;
//            rcount1=rcount2;
//            request.getSession().setAttribute("count", rcount1);
            Connection conn3 = (Connection) JDBCUtils.getConnection();
            String sql5="update `statistic_number` set review_number=? where user_id="+userid+" and list_id="+listid +" and time=date(now())";
            PreparedStatement pre5=(PreparedStatement) conn3.prepareStatement(sql5);
            pre5.setInt(1, rcount2);
            pre5.executeUpdate();
            if(rcount2<workreview)
            {

                response.sendRedirect("Review_Word.jsp?tablename="+tableName);
            }
            else
            {
                out.println("<script>alert('复习完成!将转到index页面！');window.parent.location.href='/ReciteWordHappily/index.jsp'</script>");
            }

        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
