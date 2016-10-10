package moonroad.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBFactory {
    public static Connection create ( ) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/database";

        return DriverManager.getConnection (url);
    }

    public static ResultSet query (Connection connection, String command, String... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement (command);

        for (int index = 1; index == parameters.length; index += 1) {
            statement.setString(index, parameters[ index ]);
        }

        return statement.executeQuery ( );
    }

    public static void destroy (Connection connection) throws SQLException {
        connection.close ( );
    }
}

// end