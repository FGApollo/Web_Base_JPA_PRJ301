/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thinhnhn.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FGApollo
 */
public class DBHelper implements Serializable{
    //JDBC API framework hay bat ki thu vien tao 1 lan de ket noi bat ki DBMS, viet
    //code 1 lan su dung bat chap DBMS nao 
    public static Connection makeConnection()
        throws ClassNotFoundException, SQLException{ // loi chi xuat hien trong 2 truong hop driver sai hoac duong dan sai
        // loi SQLException chi xuat hien khi url sai bat ki thanh phan nao hay TCP/IP disable hay sai password
        //1.Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2.Create connection String to connect DB 
        //jdbc:sqlserver://ip:port;databaseName=....
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1819";
        //phai ghi chinh xac in hoa in thuong 
        //dbms khong co chuan chung nen, nen phai chinh chinh xac dbms minh dang xai
        //3.Open connection using DriverManager
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        
        return con;
    }
}
