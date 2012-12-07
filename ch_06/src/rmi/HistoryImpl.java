package rmi;

import database.ConnectionManager;
import org.jetbrains.annotations.NotNull;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Contact: degas.developer@gmail.com
 * Date: 14.11.12
 * Time: 16:10
 */
public class HistoryImpl implements History {

    @NotNull
    @Override
    public String[] getHistory(int code) throws RemoteException {

            Connection connection = ConnectionManager.getConnection();

            try{
                String sql = "select position, manager, hire, dismiss from employee_history where code = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, code);
                ResultSet resultSet = statement.executeQuery();

                List<String> list = new ArrayList<String>();

                while (resultSet.next()){
                    list.add(resultSet.getString(1) + " " + resultSet.getString(2) + " " +
                             resultSet.getString(3) + " " + resultSet.getString(4));
                }

                if(list.size() > 0){
                    String[] result = new String[list.size()];
                    for(int i = 0; i < list.size(); i++){
                        result[i] = list.get(i);
                    }
                    return result;
                }

            }catch(SQLException e){
                e.printStackTrace();
            }

        return new String[0];
    }

    @Override
    public boolean login(@NotNull String user, @NotNull String password) throws RemoteException {
        Connection connection = ConnectionManager.getConnection();
        try {
            String sql = "select * from employees where login = ? and psw = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean logout() throws RemoteException {
        return false;
    }

}
