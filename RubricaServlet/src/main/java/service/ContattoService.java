package main.java.service;

import main.java.entity.Contatto;
import main.java.entity.Notifica;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ContattoService {

    // metodi contatto

    public List<String>  ControlloRegexContatto(String regex,String campo);
    public List<Contatto> FiltraContatti(Contatto contatto, String dataInizio, String dataFine) throws SQLException;
    public Contatto ContattoById(int id);
    public List<Contatto> selectContatto();
    public boolean insertContatto(Contatto contatto);
    public boolean deleteContatto(int id);
    public boolean updateContatto(int id, String campo , String valore);

    // metodi notifica
    public List<String>  ControlloRegexNotifica(String regex,String campo);
    public List<Notifica> notificaByIdContatto(int idContatto);
    public Notifica notificaById(int id);
    public List<Notifica> selectNotifica();
    public boolean insertNotifica(Notifica notifica);
    public boolean deleteNotifica(int id);
    public boolean updateNotifica(int id, String campo , String valore, HttpServletResponse response) throws IOException;
}


