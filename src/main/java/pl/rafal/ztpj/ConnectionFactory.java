package pl.rafal.ztpj;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/ztpj?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "ztpj";
    private static final String PASS = "ztpj1";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}