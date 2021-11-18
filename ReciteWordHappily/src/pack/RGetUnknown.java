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

@WebServlet(name = "RGetUnknown")
public class RGetUnknown extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String tableName = request.getParameter("tablename");
        String wordId = request.getParameter("wordid");
//        int count1 =(Integer) request.getSession().getAttribute("count");
        String username=request.getSession().getAttribute("myName").toString();
        String userid=request.getSession().getAttribute("myId").toString();
        int q=0;
        double EF=0;
        double I=0;
        int n=0;
        int worklearn=0;
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
            if(q>1)
            {
                q=q-1;
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
            //String sql1="update `"+tableName+"` set `latest_time`=now(),`q`="+q+",`EF`="+EF+",`I`="+I+" where `word_id`="+wordId;
            // String sql1="update `"+tableName+"` set `latest_time`=now(),`q`="+q+",`EF`="+EF+",`I`="+I+" where `word_id`="+wordId;
            //PreparedStatement pre1 = (PreparedStatement) conn.prepareStatement(sql1);
            String sql1="update `"+tableName+"` set latest_time=now(),q=?,EF=?,I=?,n=? where word_id="+wordId;
            PreparedStatement pre1=(PreparedStatement) conn.prepareStatement(sql1);
            pre1.setInt(1, q);
            pre1.setDouble(2, EF);
            pre1.setDouble(3, I);
            pre1.setInt(4, n);
            pre1.executeUpdate();
            //pre1.executeQuery();
            conn.close();

        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.sendRedirect("Review_Word.jsp?tablename="+tableName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
