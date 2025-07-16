package main.java.dao;

import main.java.config.ConnessioneDB;
import main.java.entity.Contatto;
import main.java.entity.Notifica;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DaoNotifica {

    public List<Notifica> notificaByIdContatto(int idContatto) {
        List<Notifica> notifiche = new ArrayList<>();
        String sql = "SELECT * FROM notifica WHERE idContatto = ?";
        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idContatto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    Timestamp ts = rs.getTimestamp("data");
                    LocalDateTime data = ts.toLocalDateTime();
                    String descrizione = rs.getString("descrizione");
                    boolean inviata = Boolean.parseBoolean(rs.getString("inviata"));

                    notifiche.add(new Notifica(tipo,data,descrizione,inviata,idContatto));

                }
                return notifiche;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Notifica notificaById(int id) {
        String sql = "SELECT * FROM notifica WHERE id = ?";
        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    Timestamp ts = rs.getTimestamp("data");
                    LocalDateTime data = ts.toLocalDateTime();
                    String descrizione = rs.getString("descrizione");
                    boolean inviata = Boolean.parseBoolean(rs.getString("inviata"));
                    int idContatto = Integer.parseInt(rs.getString("idContatto"));

                   return new Notifica(tipo,data,descrizione,inviata,idContatto);
                } else{
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }


    public List<Notifica> selectNotifica() {
        List<Notifica> notifiche = new ArrayList<>();
        String query = "SELECT * " +
                "FROM notifica";

        try (Connection conn = ConnessioneDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                Timestamp ts = rs.getTimestamp("data");
                LocalDateTime data = ts.toLocalDateTime();
                String descrizione = rs.getString("descrizione");
                boolean inviata = rs.getBoolean("inviata");
                int idContatto = rs.getInt("idContatto");

                notifiche.add(new Notifica(id, tipo, data, descrizione, inviata, idContatto));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifiche;
    }

    public boolean insertNotifica(Notifica notifica) {
        String query = "INSERT INTO notifica (tipo, data, descrizione, inviata, idContatto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, notifica.getTipo());
            ps.setTimestamp(2, Timestamp.valueOf(notifica.getData()));
            ps.setString(3, notifica.getDescrizione());
            ps.setBoolean(4, notifica.isInviata());
            ps.setInt(5, notifica.getIdContatto());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNotifica(int id) {
        String query = "DELETE FROM notifica WHERE id = ?";

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

    public boolean updateNotifica(int id, String campo, String valore){
            String query = "UPDATE notifica  SET " + campo + " = ? WHERE id = ?";

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
}

