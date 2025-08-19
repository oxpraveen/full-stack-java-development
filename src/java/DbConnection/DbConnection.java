/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class DbConnection {
    public static Connection getConnectToECOM() throws SQLException, ClassNotFoundException
   {
       Class.forName("com.mysql.jdbc.Driver");
       Connection con=null;
       String url="jdbc:mysql://localhost:3306/";
       String dbName="ECOM";
       String userName="root";
       String password="Jay@2003";
       con=DriverManager.getConnection(url+dbName,userName, password);
       return con; 
}
}
