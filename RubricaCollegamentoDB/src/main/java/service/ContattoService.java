package main.java.service;

import main.java.entity.Contatto;
import main.java.entity.Notifica;

import java.util.List;

public interface ContattoService {
    public String ControlloRegex(String regex,String campo);
    public List<Contatto> selectContatto();
    public boolean insertContatto(Contatto contatto);
    public boolean deleteContatto(int id);
    public boolean updateContatto(int id, String campo , String valore);
    public List<Notifica> selectNotifica();
    public boolean insertNotifica(Notifica notifica);
    public boolean deleteNotifica(int id);
    public boolean updateNotifica(int id, String campo , String valore);
}


