package tugasbesarpbo.Database;

import tugasbesarpbo.Config.databaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                databaseConfig.URL,
                databaseConfig.USER,
                databaseConfig.PASSWORD
        );
    }
}
