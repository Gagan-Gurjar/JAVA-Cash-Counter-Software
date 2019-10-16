/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Order;
import planetfood.pojo.OrderDetails;
import planetfood.pojo.UserProfile;

/**
 *
 * @author Win7
 */
public class OrderDao {
    public static ArrayList <Order> getOrdersByDate(Date startDate, Date endDate) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select * from Orders where ord_date between ? and ?");
        long ms1 = startDate.getTime();
        long ms2 = endDate.getTime();
        java.sql.Date sdate = new java.sql.Date(ms1);
        java.sql.Date edate = new java.sql.Date(ms2);
        ps.setDate(1, sdate);
        ps.setDate(2, edate);
        System.out.println(sdate);
        ResultSet rs = ps.executeQuery();
        ArrayList <Order> orderList = new ArrayList <Order> ();
        while(rs.next()) {
            Order obj = new Order();
            obj.setOrdId(rs.getString("ord_id"));
            java.sql.Date d = rs.getDate("ord_date");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String dateStr = sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setOrdAmount(rs.getDouble("ord_amount"));
            obj.setGst(rs.getDouble("gst"));
            obj.setGstAmount(rs.getDouble("gst_amount"));
            obj.setGrandTotal(rs.getDouble("grand_total"));
            obj.setDiscount(rs.getDouble("discount"));
            obj.setUserId(rs.getString("user_id"));
            orderList.add(obj);          
            
        }
        return orderList;
    }
    
    public static  String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select count (*) from orders");
        int id = 101;
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            id = id + rs.getInt(1);
        }
        return "OD" + id;
        
    }
    
    public static boolean addOrder(Order order, ArrayList <OrderDetails> orderList) throws SQLException, ParseException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps1 = conn.prepareStatement("Insert into orders values (?,?,?,?,?,?,?,?)");
        ps1.setString(1, order.getOrdId());
        String dateStr = order.getOrdDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1 = sdf.parse(dateStr);
        java.sql.Date d2 = new java.sql.Date(d1.getTime());
        ps1.setDate(2, d2);
        ps1.setDouble(3, order.getGst());
        ps1.setDouble(4, order.getGstAmount());
        ps1.setDouble(5, order.getDiscount());
        ps1.setDouble(6, order.getGrandTotal());
        ps1.setString(7, order.getUserId());
        ps1.setDouble(8, order.getOrdAmount());
        
        int x = ps1.executeUpdate();
        PreparedStatement ps2 = conn.prepareStatement("Insert into order_details values (?,?,?,?)");
        int count = 0,y;
        for(OrderDetails detail: orderList) {
            ps2.setString(1, detail.getOrdId());
            ps2.setString(2, detail.getProdId());
            ps2.setDouble(3, detail.getQuantity());
            ps2.setDouble(4, detail.getCost());
            y = ps2.executeUpdate();
            count = count + y;
        }
        if(x > 0 && count == orderList.size())
            return true;
        else
            return false;
    }
    
    public static ArrayList <Order> getAllData() throws SQLException {
        Connection conn = DBConnection.getConnection();        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from orders");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList <Order> orderList = new ArrayList <Order>();
        while(rs.next()) {
            Order ord = new Order();
            ord.setOrdId(rs.getString("ord_id"));
            Date d1 = rs.getDate("ord_date");         
            ord.setOrdDate(sdf.format(d1));
            ord.setGst(rs.getDouble("gst"));
            ord.setGstAmount(rs.getDouble("gst_amount"));
            ord.setDiscount(rs.getDouble("discount"));
            ord.setGrandTotal(rs.getDouble("grand_total"));
            ord.setOrdAmount(rs.getDouble("ord_amount"));
            orderList.add(ord);
            
            
        }
        
        return orderList;
    }
    public static ArrayList <Order> getTransactionData(String userid) throws SQLException {
        Connection conn = DBConnection.getConnection();  
        PreparedStatement ps = conn.prepareStatement("select * from orders where user_id=?");
        ps.setString(1, userid);     
        
        ResultSet rs = ps.executeQuery();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList <Order> orderList = new ArrayList <Order>();
        while(rs.next()) {
            Order ord = new Order();
            ord.setOrdId(rs.getString("ord_id"));
            Date d1 = rs.getDate("ord_date");         
            ord.setOrdDate(sdf.format(d1));
            ord.setGst(rs.getDouble("gst"));
            ord.setGstAmount(rs.getDouble("gst_amount"));
            ord.setDiscount(rs.getDouble("discount"));
            ord.setGrandTotal(rs.getDouble("grand_total"));
            ord.setOrdAmount(rs.getDouble("ord_amount"));
            orderList.add(ord);
            
            
        }
        
        return orderList;
    }
    
}







 






