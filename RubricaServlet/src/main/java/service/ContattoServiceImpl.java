package main.java.service;


import main.java.config.ConnessioneDB;
import main.java.dao.DaoContatto;
import main.java.dao.DaoNotifica;
import main.java.entity.Contatto;
import main.java.entity.Notifica;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContattoServiceImpl implements ContattoService {

    String regexTelefono = "^([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?\\s[0-9]+$";
    String regexEmail = "^[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?$";
    String regexNomeCognome = "^[A-Z][a-z]+";
    String regexData = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    DaoContatto daoC = new DaoContatto();
    DaoNotifica daoN = new DaoNotifica();

    //                     SERVICE CONTATTO
    @Override
    public List<String> ControlloRegexContatto(String regex, String campo) {
        List<String> risultati = new ArrayList<>();
        String sql = "SELECT " + campo + " FROM contatto WHERE " + campo + " REGEXP ?";

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, regex);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    risultati.add(rs.getString(campo));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return risultati;
    }

    public List<Contatto> FiltraContatti(Contatto contatto, String dataInizio, String dataFine) throws SQLException {
        List<Contatto> risultati = new ArrayList<>();
        List<Object> parametri = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM contatto WHERE 1=1 ");

        if (contatto.getNome() != null && contatto.getNome().matches(regexNomeCognome)) {
            sql.append("AND nome LIKE ? ");
            parametri.add(contatto.getNome() + "%");
        }
        if (contatto.getCognome() != null && contatto.getCognome().matches(regexNomeCognome)) {
            sql.append("AND cognome LIKE ? ");
            parametri.add(contatto.getCognome() + "%");
        }
        if (contatto.getEmail() != null && contatto.getEmail().matches(regexEmail)) {
            sql.append("AND email LIKE ? ");
            parametri.add(contatto.getEmail() + "%");
        }
        if (contatto.getTelefono() != null && contatto.getTelefono().matches(regexTelefono)) {
            sql.append("AND telefono LIKE ? ");
            parametri.add(contatto.getTelefono() + "%");
        }
        if (dataInizio != null && dataFine != null) {
            sql.append("AND dataNascita BETWEEN ? AND ? ");
            parametri.add(dataInizio);
            parametri.add(dataFine);
        }

        System.out.println("Query costruita: " + sql.toString());
        System.out.println("Parametri passati: " + parametri);

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametri.size(); i++) {
                Object param = parametri.get(i);
                if (param instanceof String) {
                    ps.setString(i + 1, (String) param);
                } else if (param instanceof Date) {
                    ps.setDate(i + 1, (Date) param);
                }
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contatto c = new Contatto();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCognome(rs.getString("cognome"));
                    c.setEmail(rs.getString("email"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setDataNascita(rs.getDate("dataNascita").toLocalDate());

                    risultati.add(c);
                }
            }
        }
        return risultati;
    }


         @Override
    public Contatto ContattoById(int id) {
        return daoC.ContattoById(id);
    }


    @Override
    public List<Contatto> selectContatto() {
        return daoC.selectContatti();
    }

    @Override
    public boolean insertContatto(Contatto contatto) {
        return daoC.insertContatto(contatto);
    }

    @Override
    public boolean deleteContatto(int id) {
        List<Contatto> contatti = selectContatto();
        for (Contatto contatto : contatti) {
            if (contatto.getId() == id) {
                return daoC.deleteContatto(id);
            }
        }
        System.err.println("Contatto non esistente con ID: " + id);
        return false;
    }

    @Override
    public boolean updateContatto(int id, String campo, String valore) {
        List<Contatto> contatti = selectContatto();
        for (Contatto contatto : contatti) {
            if (contatto.getId() == id) {
                return daoC.updateContatto(id, campo, valore);
            }
        }
        System.err.println("Contatto non esistente con ID: " + id);
        return false;
    }


    //                             SERVICE NOTIFICA


    @Override
    public List<String> ControlloRegexNotifica(String regex, String campo) {
        List<String> risultati = new ArrayList<>();
        String sql = "SELECT " + campo + " FROM notifica WHERE " + campo + " REGEXP ?";

        try (Connection conn = ConnessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, regex);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    risultati.add(rs.getString(campo));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return risultati;
    }



    @Override
    public List<Notifica> notificaByIdContatto(int idContatto) {
        return daoN.notificaByIdContatto(idContatto);
    }

    @Override
    public Notifica notificaById(int id) {
        return daoN.notificaById(id);
    }

    @Override
    public List<Notifica> selectNotifica() {
        return daoN.selectNotifica();
    }

    @Override
    public boolean insertNotifica(Notifica notifica) {
        notifica.setData(LocalDateTime.now());
        return daoN.insertNotifica(notifica);
    }

    @Override
    public boolean deleteNotifica(int id) {
        Notifica notifica = daoN.notificaById(id);
            if (notifica != null && notifica.getId() == id) {
                return daoN.deleteNotifica(id);
            }else {
                System.err.println("Notifica non esistente con ID: " + id);
                return false;
            }
    }

    @Override
    public boolean updateNotifica(int id, String campo, String valore, HttpServletResponse response) throws IOException {
        Notifica notifica = daoN.notificaById(id);
        if (notifica == null) {
            response.getWriter().write("Notifica non esistente con ID: " + id);
            return false;
        }
        if ("inviata".equals(campo)) {
            boolean nuovoValore = Boolean.parseBoolean(valore);
            if (notifica.isInviata() && !nuovoValore) {
                response.getWriter().write("Errore: una notifica già inviata non può essere segnata come non inviata.");
                return false;
            } else {
                return daoN.updateNotifica(id, campo, valore);
            }
        }else{
            return daoN.updateNotifica(id, campo, valore);
        }
    }


}