package pack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JDBCUtils {

    public static Connection getConnection()
    {
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/recitewordhappily?useUnicode=true&characterEncoding=UTF-8", "root", "pwd");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeAll(Connection conn,PreparedStatement pre,ResultSet rs)
    {
        try {
            if(rs!=null)
            {
                rs.close();
            }
            if(pre!=null)
            {
                pre.close();
            }
            if(conn!=null)
            {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closeBoth(Connection conn,PreparedStatement pre)
    {
        try {

            if(pre!=null)
            {
                pre.close();
            }
            if(conn!=null)
            {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}