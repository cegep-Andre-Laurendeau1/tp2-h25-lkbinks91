package ca.cal.tp1.persistence;

import ca.cal.tp1.modele.Emprunteur;
import java.sql.*;

public class EmprunteurDAO extends RepositoryParentJDBC {

    public void save(Emprunteur emprunteur) {
        String sql = """
                INSERT INTO Emprunteur (name, email, phoneNumber) VALUES (?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, emprunteur.getName());
            preparedStatement.setString(2, emprunteur.getEmail());
            preparedStatement.setString(3, emprunteur.getPhoneNumber());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emprunteur.setUserID(generatedKeys.getInt(1));
                }
            }

            System.out.println("Emprunteur ajouté avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Emprunteur getById(int id) {
        Emprunteur emprunteur = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Emprunteur WHERE userID = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                emprunteur = new Emprunteur();
                emprunteur.setUserID(resultSet.getInt("userID"));
                emprunteur.setName(resultSet.getString("name"));
                emprunteur.setEmail(resultSet.getString("email"));
                emprunteur.setPhoneNumber(resultSet.getString("phoneNumber"));
            }
            return emprunteur;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
