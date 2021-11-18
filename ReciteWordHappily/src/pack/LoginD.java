package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

//import com.mysql.jConnection;
//import com.mysql.jdbc.PreparedStatement;

public class LoginD {

    //登录时，进行数据库判断，账号和密码是否正确
    public boolean searchNameAndPwd(String loginName, String loginPwd) {
        //连接数据库
        Connection conn = (Connection) JDBCUtils.getConnection();
        String sql="SELECT name,pwd FROM user WHERE name=? AND pwd=?";
        try {
            PreparedStatement pre=(PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, loginName);
            pre.setString(2, loginPwd);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    //注册，在数据库中插入账号和密码、邮箱
    public void RegistNameAndPwd(String loginName, String loginPwd,String last_day,String rank,String gender,String birthday,String location,String email) {
        // TODO Auto-generated method stub
        Connection conn = (Connection) JDBCUtils.getConnection();
        String sql="insert into user(name,pwd,last_day,rank,gender,birthday,location,email) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre=(PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, loginName);
            pre.setString(2, loginPwd);
            pre.setString(3, last_day);
            pre.setString(4, rank);
            pre.setString(5, gender);
            pre.setString(6, birthday);
            pre.setString(7, location);
            pre.setString(8, email);

            int rst=pre.executeUpdate();

            if (rst!= 0){
                out.print("<script language='javascript'>alert('用户注册成功！');window.location.href='login.jsp';</script>");


            }else{
                out.println("<script language='javascript'>alert('用户注册失败！');window.location.href='register.jsp';</script>");
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void UpdateInformation(String loginName, String loginPwd,String last_day,String rank,String gender,String birthday,String location,String email,String id){
        Connection conn = (Connection) JDBCUtils.getConnection();
        Login login=new Login();
        String sql="update `user` set name=?,pwd=?,last_day=?,rank=?,gender=?,birthday=?,location=?,email=? where user_id=?";
        try {
            PreparedStatement pre=(PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, loginName);
            pre.setString(2, loginPwd);
            pre.setString(3, last_day);
            pre.setString(4, rank);
            pre.setString(5, gender);
            pre.setString(6, birthday);
            pre.setString(7, location);
            pre.setString(8, email);
            pre.setString(9,id);
            int rst=pre.executeUpdate();
            if (rst!= 0){
                out.println("<script language='javascript'>alert('用户更新成功！'");
            }else{
                out.println("<script language='javascript'>alert('用户更新失败！');window.location.href='register.jsp';</script>");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
