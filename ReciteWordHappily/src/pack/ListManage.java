package pack;

import pack.JDBCUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.Vector;

public class ListManage implements Serializable {
    public Vector result;
    public String listname;
    public String listid;
    public Vector getResult(String myId) {
        Vector v = new Vector();
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "SELECT * FROM `User&List` WHERE user_id=?";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                pre.setString(1, myId);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    v.addElement(rs.getString("list_name"));
//                    v.addElement(rs.getString("workload_learn"));
//                    v.addElement(rs.getString("workload_review"));
                }
                this.result = v;
                return result;
            }
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
    }

    public Vector getUnselected(String myId) {
        Vector v = new Vector();
        Vector v2 = new Vector();
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "SELECT * FROM `User&List` WHERE user_id=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, myId);
            ResultSet rs = pre.executeQuery();

            String sql2 = "SELECT distinct list_id, list_name FROM `Summary_WordList`";
            PreparedStatement pre2 = (PreparedStatement) conn.prepareStatement(sql2);
            ResultSet rs2 = pre2.executeQuery();

            while (rs.next()) {
                v.addElement(rs.getString("list_id"));
                v.addElement(rs.getString("list_name"));
            }
            while (rs2.next()) {
                v2.addElement(rs2.getString("list_id"));
                v2.addElement(rs2.getString("list_name"));
            }
            for (int i = 0; i < v2.size(); i++) {
                for (int j=0; j < v.size(); j++)
                {
                    v2.remove(v.get(j));
                }
            }
            this.result = v2;
            return result;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getListName(String listid) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "SELECT distinct list_name FROM `Summary_WordList` WHERE list_id=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, listid);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                this.listname = rs.getString("list_name");
            }
            return this.listname;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getListId(String listname) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "SELECT distinct list_id FROM `Summary_WordList` WHERE list_name=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, listname);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                this.listid = rs.getString("list_id");
            }
            return this.listid;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteList(String userid, String listid) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "Delete from `User&List` where user_id=? AND list_id=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, userid);
            pre.setString(2, listid);
            int rs = pre.executeUpdate();

            String sql2 = "drop table"+"`"+userid+"_"+listid+"`";
            PreparedStatement pre2 = (PreparedStatement) conn.prepareStatement(sql2);
            int rs2 = pre2.executeUpdate();

            String sql3 = "Delete from `Statistic_Number` where user_id=? AND list_id=?";
            PreparedStatement pre3 = (PreparedStatement) conn.prepareStatement(sql3);
            pre3.setString(1, userid);
            pre3.setString(2, listid);
            int rs3 = pre3.executeUpdate();

            if(rs!=0 && rs2==0)
            {
                return true;
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public String getTableName(String userid, String listid)
    {
        return userid+"_"+listid;
    }
    public Vector getAllTableName(String myId)
    {
        Vector v = new Vector();
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "SELECT * FROM `User&List` WHERE user_id=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1, myId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                v.addElement(rs.getString("table_name"));
//                    v.addElement(rs.getString("workload_learn"));
//                    v.addElement(rs.getString("workload_review"));
            }
            this.result = v;
            return result;
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
    public Boolean SetWordload(int learn, int review, String userid, String listid) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "update `User&List` set workload_learn=?, workload_review=? where user_id=? and list_id=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setInt(1, learn);
            pre.setInt(2,review);
            pre.setString(3,userid);
            pre.setString(4,listid);
            int rs = pre.executeUpdate();
            if(rs!=0)
                return true;
            else
                return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int[] GetWordload(String userid, String listname) {
        try {
            String listid = getListId(listname);
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "select workload_learn,workload_review from `User&List` where user_id=? and list_id=?";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1,userid);
            pre.setString(2,listid);
            ResultSet rs = pre.executeQuery();

            String sql2 = "select learn_number,review_number from `Statistic_Number` where user_id=? and list_id=? and time = date(now())";

            int res[] = new int[4];
            if(rs.next())
            {
                res[0]=rs.getInt("workload_learn");
                res[1]=rs.getInt("workload_review");
            }
            return res;
         }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int[] GetNumber(String userid, String listid) {
        try {
            Connection conn = (Connection) JDBCUtils.getConnection();
            String sql = "select learn_number,review_number from `Statistic_Number` where user_id=? and list_id=? and time = date(now())";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setString(1,userid);
            pre.setString(2,listid);
            ResultSet rs = pre.executeQuery();
            int res[] = new int[2];
            if(rs.next())
            {
                res[0]=rs.getInt("learn_number");
                res[1]=rs.getInt("review_number");
            }
            return res;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
