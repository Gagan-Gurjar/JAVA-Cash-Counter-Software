/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;


/**
 *
 * @author Win7
 */
public class EmpDao {
    public static boolean addEmployee(Emp e) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1, e.getEmpId());
        ps.setString(2, e.getEmpName());
        ps.setString(3, e.getJob());
        ps.setDouble(4, e.getSal());
        int result = ps.executeUpdate();
        return (result==1);
    }
    
    public static String getNewEmpId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Select count(*) from employees");        
        int id = 101;
        String empName;
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {        
            id = id + rs.getInt(1);
        }
        String eid = "E"+id;        
        empName = checkEmpId(eid);        
        while(empName != null) {
            id++;
            eid = "E"+id;            
            empName = checkEmpId(eid);            
        }
        return eid; 
    }
    
    public static String checkEmpId(String eid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select ename from employees where empid=?";
        PreparedStatement ps = conn.prepareStatement(qry);        
        ps.setString(1, eid);
        ResultSet rs = ps.executeQuery();
        String empname = null;
        if(rs.next()) {
            empname = rs.getString(1);
        }
        return empname;
        
    }
    
    public static ArrayList <Emp> getAllEmpData() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "select * from employees";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(qry);
        ArrayList <Emp> empList = new ArrayList<Emp>();
        while(rs.next()) {
            Emp e = new Emp();
            e.setEmpId(rs.getString("empid"));
            e.setEmpName(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));            
            empList.add(e);              
        }       
        return empList;
    }
    
    public static ArrayList <Emp> getSingleEmpData(String empid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select * from employees where empid=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        ArrayList <Emp> empList = new ArrayList <Emp>();
        ps.setString(1, empid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Emp e = new Emp();
            e.setEmpId(rs.getString("empid"));
            e.setEmpName(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));            
            empList.add(e);            
        }
        return empList;
        
        
    }
    
    public static HashMap <String, Emp> getEmpDataByEmpId(String empid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select * from employees where empid=?";
        PreparedStatement ps = conn.prepareStatement(qry);
        HashMap <String, Emp> empList = new HashMap <String, Emp>();
        ps.setString(1, empid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Emp e = new Emp();
            e.setEmpId(rs.getString("empid"));
            e.setEmpName(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));            
            empList.put(e.getEmpId(),e);
        }
        return empList;
        
        
    }
    
    public static String validateEmpId(Emp emp) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select ename from employees where empid=? ";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, emp.getEmpId()); 
       
        ResultSet rs = ps.executeQuery();
        String empname = null;
        if(rs.next()) {
            empname = rs.getString(1);
        }
        return empname;
    }
    
    public static boolean removeEmp(String empId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("delete from employees where empid=?");
        ps.setString(1, empId);
        int x = ps.executeUpdate();
        return x>0;
    }
    
    public static boolean updateEmp(Emp e) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update employees set ename=?, job=?, sal=? where empid=?");
        ps.setString(1, e.getEmpName());
        ps.setString(2, e.getJob());
        ps.setDouble(3, e.getSal());        
        ps.setString(4, e.getEmpId());
       
        
        int x = ps.executeUpdate();
        return (x>0);
        
    }
    public static ArrayList <String> getAllEmpId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select empid from employees");
        ArrayList <String> allEmpId = new ArrayList<>();
        while(rs.next()) {
            String empId = rs.getString(1);            
            allEmpId.add(empId);
        }
        return allEmpId;
    }
    
    public static ArrayList <String> getAllCashierId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select empid from employees where job='Cashier'");
        ArrayList <String> allCashierId = new ArrayList<>();
        while(rs.next()) {
            String empId = rs.getString(1);            
            allCashierId.add(empId);
        }
        return allCashierId;
    }
    
    public static String getEmpNameById(String empid) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String qry = "Select ename from employees where empid=? ";
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1, empid); 
       
        ResultSet rs = ps.executeQuery();
        String empname = null;
        if(rs.next()) {
            empname = rs.getString(1);
        }
        return empname;
    }
    
  
    
}
