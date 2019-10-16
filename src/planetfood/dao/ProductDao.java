
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Product;


public class ProductDao {
    public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select count(*) from products");        
        int id = 101;
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {        
            id = id + rs.getInt(1);
        }        
        return "P"+id; 
    }
    public static boolean addProduct(Product p) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into products values(?,?,?,?,?)");
        ps.setString(1, p.getProdId());
        ps.setString(2, p.getCatId());
        ps.setString(3, p.getProdName());
        ps.setDouble(4, p.getProdPrice());
        ps.setString(5, p.getIsActive());
        int x = ps.executeUpdate();
        return (x > 0);
    }
    
    public static HashMap <String, Product> getProductsByCategory(String catid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select * from products where cat_id=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        HashMap <String, Product> productList = new HashMap <String, Product>();
        ps.setString(1, catid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Product p = new Product();
            p.setCatId(catid);
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));            
            productList.put(p.getProdId(),p);
        }
        return productList;
    }
   
    public static ArrayList <Product> getAllData() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "select * from products";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(qry);
        ArrayList <Product> productList = new ArrayList<Product>();
        while(rs.next()) {
            Product p = new Product();
            p.setCatId(rs.getString("cat_id"));
            p.setProdId(rs.getString("prod_id"));
            p.setProdName(rs.getString("prod_name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));
            productList.add(p);
            
            
        }
        
        return productList;
    }
    
    public static boolean updateProduct(Product p) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update products set cat_id=?, prod_name=?, prod_price=?, active=? where prod_id=?");
        ps.setString(1, p.getCatId());
        ps.setString(2, p.getProdName());
        ps.setDouble(3, p.getProdPrice());        
        ps.setString(4, p.getIsActive());
        ps.setString(5, p.getProdId());
        
        int x = ps.executeUpdate();
        return (x>0);
        
    }
    public static boolean removeProduct(String prodId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Update products set active='N' where prod_id=?");
        ps.setString(1, prodId);
        int x = ps.executeUpdate();
        return x>0;
    }
    
    public static HashMap <String, String> getActiveProductsByCategory(String cat_Id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select prod_name, prod_id from products where cat_id=? and active='Y'";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, cat_Id);
        ResultSet rs = ps.executeQuery();
        HashMap <String, String> productList = new HashMap<>();
        while(rs.next()) {
            String prodName = rs.getString("prod_name");
            String prodId = rs.getString("prod_id");
            productList.put(prodName, prodId);
        }
        return productList;
    }
         
    
    
}
