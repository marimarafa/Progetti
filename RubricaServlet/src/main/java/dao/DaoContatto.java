package main.java.dao;

import main.java.config.ConnessioneDB;
import main.java.entity.Contatto;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoContatto {


    public Contatto ContattoById(int id) {
        String sql = "SELECT nome, cognome, indirizzo, telefono, email, dataNascita FROM contatto WHERE id = ?";
        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String cognome = rs.getString("cognome");
                    String indirizzo = rs.getString("indirizzo");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    LocalDate dataNascita = rs.getDate("dataNascita").toLocalDate();

                    return new Contatto(nome, cognome, indirizzo, telefono, email, dataNascita);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean insertContatto(Contatto contatto) {

        String query = """
                INSERT INTO contatto (nome, cognome, indirizzo, telefono, email, dataNascita)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contatto.getNome());
            ps.setString(2, contatto.getCognome());
            ps.setString(3, contatto.getIndirizzo());
            ps.setString(4, contatto.getTelefono());
            ps.setString(5, contatto.getEmail());
            ps.setDate(6, Date.valueOf(contatto.getDataNascita()));;

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Contatto> selectContatti() {
        List<Contatto> contatti = new ArrayList<>();

        String query = """
                SELECT *
                FROM contatto
                """;

        try (Connection conn = ConnessioneDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String indirizzo = rs.getString("indirizzo");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                LocalDate dataNascita = rs.getDate("dataNascita").toLocalDate();



                Contatto contatto = new Contatto(id, nome, cognome, indirizzo, telefono, email, dataNascita);

                contatti.add(contatto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contatti;
    }

    public boolean deleteContatto(int id){

        String query = """
               DELETE FROM contatto 
               WHERE id = ?
            """;

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContatto(int id, String campo , String valore){
            String query = "UPDATE contatto SET " + campo + " = ? WHERE id = ?";

            try (Connection conn = ConnessioneDB.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, valore);
                ps.setInt(2, id);

                int rows = ps.executeUpdate();
                return rows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    public String getEmailById(int id) {
        String email = null;
        String query = "SELECT email FROM contatto WHERE id = ?";

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email;
    }


}
