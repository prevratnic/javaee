package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 15.11.12
 * Time: 12:02
 */
public final class ConnectionManager {

    private static Connection connection;

    public static Connection getConnection(){
        Connection conn = null;
        try {
            if(connection == null){
                connection = initDriverManager();
            }
            conn = connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean close(){
        try{
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

    private static Connection initDriverManager() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@//degas24.com:1521/xe", "tm", "123abc123");
    }

}
