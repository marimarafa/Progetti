package main.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.entity.Contatto;
import main.entity.Notifica;

public class ConnessioneDB {

    public static List<Contatto> CaricaDaDB() {
        List<Contatto> contatti = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/rubrica";
        String user = "root";
        String password = "MysqlMarim.2004";

        Connection connection = null;
        Statement statement = null;
        ResultSet risultato = null;

        try {
            Class.forName("com.mysql.cj.jdbc.OracleDriver");
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * " +
                    "FROM Contatto c, Notifica n" +
                    " WHERE c.notifica = n.id";

            statement = connection.createStatement();
            risultato = statement.executeQuery(query);

            while (risultato.next()) {
                // Campi per contatto
                int id = risultato.getInt("id");
                String nome = risultato.getString("nome");
                String cognome = risultato.getString("cognome");
                String indirizzo = risultato.getString("indirizzo");
                String telefono = risultato.getString("telefono");
                String email = risultato.getString("email");
                LocalDate dataNascita = risultato.getDate("dataNascita").toLocalDate();

                // Campi per notifica
                int idNotifica = risultato.getInt("id");
                String tipo = risultato.getString("tipo");
                LocalDate dataNotifica = risultato.getDate("dataNotifica").toLocalDate();
                String descrizione = risultato.getString("descrizione");
                boolean inviata = risultato.getBoolean("inviata");

                Notifica notifica = new Notifica(idNotifica, tipo, dataNotifica, descrizione, inviata);
                Contatto contatto = new Contatto(id, nome, cognome, indirizzo, telefono, email, dataNascita, notifica);

                contatti.add(contatto);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trovato: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Errore SQL: " + e.getMessage());
        } finally {
            try {
                if (risultato != null){
                    risultato.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException ex) {
                System.err.println("Errore nella chiusura delle risorse: " + ex.getMessage());
            }
        }

        return contatti;
    }
}
