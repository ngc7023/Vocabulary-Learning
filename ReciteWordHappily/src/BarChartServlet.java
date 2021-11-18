import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import pack.JDBCUtils;
import pack.ListManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

@WebServlet(name = "BarChartServlet")
public class BarChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getSession().getAttribute("myId").toString();
        String listname = request.getParameter("listname");
        int timelength = Integer.valueOf(request.getParameter("timelength"));

        ListManage lm = new ListManage();
        String listid = lm.getListId(listname);
        String tablename = lm.getTableName(userid,listid);

        if (listname.equals("AllList")) {
            OutputStream outputStream = response.getOutputStream();
            JFreeChart chart = getChart2(userid, listid, timelength);
            int width = 500;
            int height = 350;
            ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
        }
        else {
            OutputStream outputStream = response.getOutputStream();
            JFreeChart chart = getChart(userid, listid, timelength);
            int width = 500;
            int height = 350;
            ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
        }
    }

    public JFreeChart getChart(String userid, String listid,int timelength){
        final String learn = "learn_number";
        final String review = "review_number";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        Vector v = Statistic_Number(userid, listid,timelength);

        for(int i=0;i<v.size();)
        {
            String date = v.get(i+2).toString();
            dataset.addValue( (Integer)v.get(i) , learn, date);
            dataset.addValue( (Integer)v.get(i+1) , review, date);
            i=i+3;
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "单词学习与复习",
                "日期", "数量",
                dataset,PlotOrientation.VERTICAL,
                true, true, false);
        chart.setBorderPaint(Color.lightGray);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);
        return chart;
    }
    public JFreeChart getChart2(String userid, String listid,int timelength){
        final String learn = "learn_number";
        final String review = "review_number";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        Vector v = Statistic_Number2(userid, listid,timelength);

        for(int i=0;i<v.size();)
        {
            String date = v.get(i+2).toString();
            dataset.addValue( (Integer)v.get(i) , learn, date);
            dataset.addValue( (Integer)v.get(i+1) , review, date);
            i=i+3;
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "单词学习与复习",
                "日期", "数量",
                dataset,PlotOrientation.VERTICAL,
                true, true, false);
        chart.setBorderPaint(Color.lightGray);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);
        return chart;
    }

    public Vector Statistic_Number(String userid, String listid,int timelength) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql ="select learn_number,review_number,datediff(`time`,curdate()) as time from `Statistic_Number` where `user_id` = ? and `list_id` =? and datediff(curdate(),`time`)<= ? order by datediff(curdate(),`time`) desc";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1,userid);
            pre.setString(2,listid);
            pre.setInt(3,timelength);
            ResultSet rs = pre.executeQuery();
            int i = 0;
            Vector v = new Vector();
            while (rs.next()) {
                v.addElement(rs.getInt("learn_number"));
                v.addElement(rs.getInt("review_number"));
                v.addElement(rs.getString("time") + "d");
            }
            return v;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Vector Statistic_Number2(String userid, String listid,int timelength) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql ="select sum(learn_number) as learn_number,sum(review_number) as review_number,datediff(`time`,curdate()) as time from `Statistic_Number` where `user_id` = ? and datediff(curdate(),`time`)<= ? group by `time` order by datediff(curdate(),`time`) desc ";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1,userid);
            pre.setInt(2,timelength);
            ResultSet rs = pre.executeQuery();
            int i = 0;
            Vector v = new Vector();
            while (rs.next()) {
                v.addElement(rs.getInt("learn_number"));
                v.addElement(rs.getInt("review_number"));
                v.addElement(rs.getString("time") + "d");
            }
            return v;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
