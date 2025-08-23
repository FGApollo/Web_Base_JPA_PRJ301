/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thinhnhn.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thinhnhn.util.DBHelper;

/**
 *
 * @author FGApollo
 */
//1.cac doi tuong phai duoc khai bao va khoi tao bang null
//2.dong cac doi tuong lai bang moi cach
//3. moi thuc hien xu li
public class RegistrationDAO implements Serializable {
//    public boolean checkLogin(String username, String password)
//        throws SQLException, ClassNotFoundException{

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {

//        boolean result = false;
        RegistrationDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Model connect DataBase
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Model Query DataBase
                //2.1. Create SQL String
                //khi viet cau lenh SQL thi moi cau lenh phai duoc viet treb 1 dong, moi
                //khi xuong dong phai chen them 1 khoang trang truoc khi xuong dong
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "and password = ? ";
                //2.2. Create Statement Object
                //co bao nhieu dua cham hoi la set het bay nhieu
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3. Excute Query
                rs = stm.executeQuery();
                //Model get data from ResultSet, then model sets data to properties of Model
                if (rs.next()) {//
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
                }//user da ton tai trong database
                //3. Model get data from...., then sets data to properties of Model
            }//connection is an available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue) throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Model connect DataBase
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Model Query DataBase
                //2.1. Create SQL String
                //khi viet cau lenh SQL thi moi cau lenh phai duoc viet treb 1 dong, moi
                //khi xuong dong phai chen them 1 khoang trang truoc khi xuong dong
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";

                //2.2. Create Statement Object
                //co bao nhieu dua cham hoi la set het bay nhieu
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //2.3. Excute Query
                rs = stm.executeQuery();

                //3. Model get data from...., then sets data to properties of Model
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    //set
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);

                }//traverse each row in table
            }//connection is an available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException {
        {

            boolean result = false;

            Connection con = null;
            PreparedStatement stm = null;

            try {
                //1. Model connect DataBase
                con = DBHelper.makeConnection();
                if (con != null) {
                    //2. Model Query DataBase
                    //2.1. Create SQL String
                    //khi viet cau lenh SQL thi moi cau lenh phai duoc viet treb 1 dong, moi
                    //khi xuong dong phai chen them 1 khoang trang truoc khi xuong dong
                    String sql = "Delete From Registration "
                            + "Where username = ?";

                    //2.2. Create Statement Object
                    //co bao nhieu dau cham hoi la set het bay nhieu
                    stm = con.prepareStatement(sql);
                    stm.setString(1, username);

                    //2.3. Excute Query
                    int effectRow = stm.executeUpdate();
                    //3.check effectRow
                    //then model sets data to properties of Model
                    if (effectRow > 0) {
                        result = true;
                    }

                }//connection is an available
            } finally {

                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            }

            return result;
        }
    }

    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.connect db
            con = DBHelper.makeConnection();
            //2. model query db
            //2.1 create sql string 
            String sql = "update Registration "
                    + "set password = ?, isAdmin = ? "
                    + "where username = ?";

            //2.3 create sql statement
            stm = con.prepareStatement(sql);
            stm.setString(1, password);
            stm.setBoolean(2, role);
            stm.setString(3, username);

            //3. excute query
            int effectRow = stm.executeUpdate();
            if (effectRow > 0) {
                result = true;
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createAccount(RegistrationDTO account) 
            throws SQLException, ClassNotFoundException {
        
        boolean result = false;
        
        Connection con = null;
        PreparedStatement stm = null;
        

        try {
            //1. Model connect DataBase
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Model Query DataBase
                //2.1. Create SQL String
                //khi viet cau lenh SQL thi moi cau lenh phai duoc viet treb 1 dong, moi
                //khi xuong dong phai chen them 1 khoang trang truoc khi xuong dong
                String sql = "insert into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")" ;
                       
                //2.2. Create Statement Object
                //co bao nhieu dua cham hoi la set het bay nhieu
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                
                //2.3. Excute Query
                int effectRow = stm.executeUpdate();
                //3.Model get data from ResultSet, then model sets data to properties of Model
                if(effectRow > 0){
                    result = true;
                }
                
              
               
                //user da ton tai trong database
                //3. Model get data from...., then sets data to properties of Model
            }//connection is an available
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
}
