
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import static oracle.sql.NUMBER.e;
import planetfood.dbutil.DBConnection;
//import planetfood.pojo.Emp;
import planetfood.pojo.User;
import planetfood.pojo.UserProfile;


public class UserDao {
    public static String validateUser(User user) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select username from Users where userid=? and password=? and usertype=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, user.getUserId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs = ps.executeQuery();
        String username = null;
        if(rs.next()) {
            username = rs.getString(1);
        }
        return username;
        
    }
    
    public static String validateUserId(String userid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select username from Users where userid=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, userid);        
        ResultSet rs = ps.executeQuery();
        String username = null;
        if(rs.next()) {
            username = rs.getString(1);
        }
        return username;
        
    }
    
    public static boolean registerCashier(UserProfile up) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "insert into users values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(qry);        
        ps.setString(1, up.getUserid());
        ps.setString(2, up.getUsername());
        ps.setString(3, up.getEmpid());
        ps.setString(4, up.getPassword());
        ps.setString(5, up.getUsertype());
        int result = ps.executeUpdate();
        return (result==1);
    }
    public static ArrayList <UserProfile> getSingleUserData(String userid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select * from users where userid=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ArrayList <UserProfile> userList = new ArrayList <UserProfile>();
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            UserProfile up = new UserProfile();
            up.setUserid(rs.getString("userid"));
            up.setUsername(rs.getString("username"));
            up.setEmpid(rs.getString("empid"));                        
            userList.add(up);            
        }
        return userList;       
        
    }
    
    public static boolean removeCashier(String userId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from users where userid=?");
        ps.setString(1, userId);
        int x = ps.executeUpdate();
        return x>0;
    }
    
    public static String checkUser(String empId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select username from users where empid=?");
        ps.setString(1, empId);
        ResultSet rs = ps.executeQuery();
        String username = null;
        if(rs.next()) {
            username = rs.getString(1);
        }
        return username;
        
    }
    public static boolean removeCashierFromEmp(String empId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from users where empid=?");
        ps.setString(1, empId);
        int x = ps.executeUpdate();
        return x>0;
    }
    
    
}
