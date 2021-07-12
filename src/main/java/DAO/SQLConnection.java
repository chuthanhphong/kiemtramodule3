package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
public static final String ROOT = "root";
public static final String PASSWORD="123456";
public static final String JDBC_URL ="jdbc:mysql://localhost:3306/KiemtraModule3";

public static Connection  getConnection(){
    Connection connection = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection= DriverManager.getConnection(JDBC_URL,ROOT,PASSWORD);
    }catch (ClassNotFoundException| SQLException exception){
        exception.printStackTrace();
    }
    return connection;
}
}
