package Client;

import javax.sql.rowset.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class DBUtilis {

    public static void changeScene(ActionEvent event,String fxmlFile, String title, String username){
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtilis.class.getResource(fxmlFile));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(DBUtilis.class.getResource(fxmlFile)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
        assert root != null;
        stage.setScene(new Scene(root));
            stage.show();
        }

    private static Connection connection = null;



    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/univers", "root", "Gleb210719762001");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        }


    public static void dbDisconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;
        try {
            dbConnect();
            stmt = connection.createStatement();

            resultSet = stmt.executeQuery(queryStmt);
            //CachedRowSet имплементация
            //Чтобы предотвратить ошибку "java.sql.SQLRecoverableException: Closed Connection: next"
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Возникла проблема при executeQuery операции: " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Возникла проблема при executeUpdate операции: " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }


     public static boolean logInAdmin (String username, String password){
         PreparedStatement preparedStatement = null;
         ResultSet resultSet = null;
         boolean log = false;
         try{
             dbConnect();
             preparedStatement = connection.prepareStatement("SELECT password FROM administrator where username = ?");
             preparedStatement.setString(1, username);
             resultSet = preparedStatement.executeQuery();


             if (!resultSet.isBeforeFirst()){
                 log = false;
             } else{
                 while (resultSet.next()){
                     String retrievedPassword = resultSet.getString("password");
                     if (retrievedPassword.equals(password))
                         log = true;
                 }
             }
         } catch (SQLException | ClassNotFoundException e){
             e.printStackTrace();
         } finally {
             try_catch(preparedStatement, resultSet);
         }
         return log;
     }

    private static void try_catch(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean logInTeacher (String username, String password){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean log = false;
        try{
            dbConnect();
            preparedStatement = connection.prepareStatement("SELECT password FROM teacher where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);

            if (!resultSet.isBeforeFirst()){
                log = false;
            } else{
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password))
                        log = true;
                }
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try_catch(preparedStatement, resultSet);
        }
        return log;
    }


    public static boolean logInStudent (String username, String password){

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean log = false;
        try{
            dbConnect();
            preparedStatement = connection.prepareStatement("SELECT password FROM student where id_student = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
              log = false;
            } else{
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password))
                       log = true;
                }
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try_catch(preparedStatement, resultSet);
        }
        return log;
    }
    }

