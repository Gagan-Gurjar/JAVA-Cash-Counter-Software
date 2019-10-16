/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.pojo;

/**
 *
 * @author Win7
 */
public class UserProfile {
    private static String username;
    private static String usertype;
    private static String userid;
    private static String empid;
    private static String password;

    public static String getEmpid() {
        return empid;
    }

    public static void setEmpid(String empid) {
        UserProfile.empid = empid;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserProfile.password = password;
    }

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        UserProfile.userid = userid;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserProfile.username = username;
    }

    public static String getUsertype() {
        return usertype;
    }

    public static void setUsertype(String usertype) {
        UserProfile.usertype = usertype;
    }
    
}
