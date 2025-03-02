package ca.cal.tp1.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryParentJDBC {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:tp1-h25;DB_CLOSE_DELAY=-1";
    static final String USER = "sa";
    static final String PASS = "";
    static Connection conn;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            try (Statement statement = conn.createStatement()) {
                String sql = "CREATE TABLE Emprunteur (" +
                        " userID INT PRIMARY KEY AUTO_INCREMENT, " +
                        " name VARCHAR(100), " +
                        " email VARCHAR(255), " +
                        " phoneNumber VARCHAR(50))";
                statement.executeUpdate(sql);
            }

            System.out.println("Tables créées avec succès !");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
