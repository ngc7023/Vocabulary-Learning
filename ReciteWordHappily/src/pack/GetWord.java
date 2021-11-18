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

@WebServlet(name = "GetWord")
public class GetWord extends HttpServlet {
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
            pre1.setInt(4,n);
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
            String sql="select list_id,workload_learn from `user&list` where table_name='"+tableName+"' and user_id="+userid;
            PreparedStatement pre2 = (PreparedStatement) conn1.prepareStatement(sql);
            ResultSet rs1= pre2.executeQuery();
            while(rs1.next())
            {
                listid=rs1.getString("list_id");
                worklearn=rs1.getInt("workload_learn");
            }
            rs1.close();
            int count2=0;
            Connection conn2 = (Connection) JDBCUtils.getConnection();
            String sql2="select learn_number from `statistic_number` where time=Date(now()) and user_id="+userid+" and list_id="+listid;
            PreparedStatement pre3 = (PreparedStatement) conn2.prepareStatement(sql2);
            ResultSet rs2= pre3.executeQuery();
            while(rs2.next())
            {
                count2=rs2.getInt("learn_number");
            }
            if(count2==0)
            {
                //count1=0;
                count2=0;
                //count1=count2;
                String sql3="insert into `statistic_number` values(?,?,0,0,now())";
                PreparedStatement pre4 = (PreparedStatement) conn1.prepareStatement(sql3);
                pre4.setString(1,userid);
                pre4.setString(2,listid);
                // pre4.setInt(3,count2);
                pre4.executeUpdate();
                // pre4.executeQuery();
            }
            conn1.close();
            //count2=count2+1;
            count2=count2+1;
//            count1=count2;
//            request.getSession().setAttribute("count", count1);
            Connection conn3 = (Connection) JDBCUtils.getConnection();
            String sql5="update `statistic_number` set learn_number=? where user_id="+userid+" and list_id="+listid +" and time=date(now())";
            PreparedStatement pre5=(PreparedStatement) conn3.prepareStatement(sql5);
            pre5.setInt(1, count2);
            pre5.executeUpdate();
            if(count2<worklearn)
            {


                response.sendRedirect("Learn_Word.jsp?tablename="+tableName);
            }
            else
            {
                out.println("<script>alert('学习完成!将转到index页面！');window.parent.location.href='/ReciteWordHappily/index.jsp'</script>");
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
