package G181210058;

import java.sql.*;

public class PostgreSQLDBUser implements IDB {

    private static PostgreSQLDBUser instance;

    private PostgreSQLDBUser() { }

    private Connection connect() {
        Connection conn=null;

        try {
            String userName = "postgres";
            String password = "12345";
            String dbName = "G181210058";
            String dbUrl = "jdbc:postgresql://localhost:5432/" + dbName;
            conn = DriverManager.getConnection(dbUrl, userName, password);
            if (conn != null)
                System.out.println("Connected to " + dbName + " PostgreSQL DB.");
            else
                System.out.println("Connection error to " + dbUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static PostgreSQLDBUser getInstance() {
        if(instance == null) {
            instance = new PostgreSQLDBUser();
        }
        return instance;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        Connection conn = this.connect();
        String sql = "SELECT \"username\",\"password\" FROM \"User\" WHERE \"username\"="+"'"+username+"'"+"AND \"password\"="+"'"+password+"'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            conn.close();

            if (!rs.next()) {
                return false;
            } else {
                System.out.println("User Authenticated!");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}