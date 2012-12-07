import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 28.10.12
 * Time: 21:01
 */

public final class ConnectionManager {

    private ConnectionManager(){
    }

    private static DataSource dataSource = null;

    public static Connection getConnection(){

        Connection connection = null;

        try{
            if( dataSource == null )
                dataSource = createDataSource("jdbc/javaeePool");
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private static DataSource createDataSource(String id){
        try{
            Context initContext = new InitialContext();
            dataSource = (DataSource)initContext.lookup(id);
        }catch (NamingException e){
            e.printStackTrace();
        }
        return dataSource;
    }

}
