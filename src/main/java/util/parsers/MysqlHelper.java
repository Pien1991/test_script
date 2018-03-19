package util.parsers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ShepardPin on 3/3/2018.
 */
public class MysqlHelper {


    private static final String  JDBC_DRIVER = "com.mysql.jdbc.Driver";


    private static int rport = 3306;
    private static String Hostname = "35.197.148.174";


    private static final String dbUrl = "jdbc:mysql://"+Hostname+":" + rport  + "/fiatgateway";



    private  static Connection connection = null;

    private static final String dbUser = "root";
    private static final String dbPassword = "Fintend2018Winner";



    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public  static  synchronized  Connection getConnection(){
        if (connection==null){
            try {
                connection= DriverManager.getConnection(dbUrl,dbUser,dbPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public static synchronized void disconnect(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void prinQueryColumn(String sql , String columnLabel){
        ResultSet resultSet = null;
        try {
            resultSet = getConnection().createStatement().executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getString(columnLabel));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MysqlHelper.disconnect();

        }

    }


    public static synchronized int executeUpdate(String sql){

        int impactedRow = 0;
        try {
            impactedRow=getConnection().createStatement().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MysqlHelper.disconnect();
        }

        return  impactedRow;

    }


}
