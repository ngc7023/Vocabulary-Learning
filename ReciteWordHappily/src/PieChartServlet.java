import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

@WebServlet(name = "PieChartServlet")
public class PieChartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getSession().getAttribute("myId").toString();
        String listname = request.getParameter("listname");
        ListManage lm = new ListManage();
        OutputStream outputStream = response.getOutputStream();
        if (listname.equals("AllList")) {
            int res[] = new int[6];
            Vector v = lm.getAllTableName(userid);
            Enumeration enum1 = v.elements();
            while (enum1.hasMoreElements()) {
                int temp[] = Statistic_Familiarity(enum1.nextElement().toString());
                for (int i = 0; i < 6; i++)
                    res[i] = res[i] + temp[i];
            }

            JFreeChart chart = getChart2(res);
            int width = 500;
            int height = 350;
            ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
        }
        else
        {
            String listid = lm.getListId(listname);
            String tablename = lm.getTableName(userid, listid);
            JFreeChart chart = getChart(tablename);
            int width = 500;
            int height = 350;
            ChartUtilities.writeChartAsPNG(outputStream, chart, width, height);
        }
}

    public JFreeChart getChart(String tablename){
        int res[] = Statistic_Familiarity(tablename);

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("较为熟练", new Double( res[5] ) );
        dataset.setValue("正确回忆", new Double( res[4]+res[3] ) );
        dataset.setValue("点滴印象", new Double( res[1]+res[2]) );
        dataset.setValue("有待学习", new Double( res[0] ) );

        boolean legend = true;
        boolean tooltips = false;
        boolean urls = false;

        JFreeChart chart = ChartFactory.createPieChart("单词类型",dataset,legend,tooltips,urls);

        chart.setBorderPaint(Color.lightGray);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);
        return chart;
    }
    public JFreeChart getChart2(int res[]) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("较为熟练", new Double( res[5] ) );
        dataset.setValue("正确回忆", new Double( res[3]+res[4] ) );
        dataset.setValue("点滴印象", new Double( res[1]+res[2]) );
        dataset.setValue("有待学习", new Double( res[0] ) );

        boolean legend = true;
        boolean tooltips = false;
        boolean urls = false;

        JFreeChart chart = ChartFactory.createPieChart("单词类型",dataset,legend,tooltips,urls);

        chart.setBorderPaint(Color.lightGray);
        chart.setBorderStroke(new BasicStroke(5.0f));
        chart.setBorderVisible(true);
        return chart;
    }

        public int[] Statistic_Familiarity(String tablename){
        int a[] = new int[6];
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
//            select q,count(word_id) from `1_2` group by q
            String sql = "select count(word_id) as num from "+ tablename+" group by q";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            int i = 0;
            while (rs.next()) {
                a[i] = rs.getInt("num");
                i++;
            }
            return a;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
