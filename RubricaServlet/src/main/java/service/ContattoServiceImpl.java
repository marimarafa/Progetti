package main.java.service;


import main.java.dao.DaoContatto;
import main.java.dao.DaoNotifica;
import main.java.entity.Contatto;
import main.java.entity.Notifica;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContattoServiceImpl implements ContattoService {
    //                     SERVICE CONTATTO
    DaoContatto daoC = new DaoContatto();
    DaoNotifica daoN = new DaoNotifica();

    public String ControlloRegex(String regex, String campoLabel) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(campoLabel + ": ");
            String campo = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(campo);
            if (!matcher.matches()) {
                System.out.println("Valore non valido, riprovare.");
            } else {
                return campo;
            }
        }
    }

    @Override
    public Contatto ContattoById(int id) {
        return daoC.ContattoById(id);
    }


    @Override
    public List<Contatto> selectContatto(){
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
                return daoC.updateContatto(id,campo,valore);
            }
        }
        System.err.println("Contatto non esistente con ID: " + id);
        return false;
    }




    //                             SERVICE NOTIFICA



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
        List<Notifica> notifiche = selectNotifica();
        for (Notifica notifica : notifiche) {
            if (notifica.getId() == id) {
                return daoN.deleteNotifica(id);
            }
        }
        System.err.println("Notifica non esistente con ID: " + id);
        return false;
    }

    @Override
    public boolean updateNotifica(int id, String campo, String valore) {
        List<Notifica> notifiche = selectNotifica();
        for (Notifica notifica : notifiche) {
            if (notifica.getId() == id) {
                return daoN.updateNotifica(id,campo,valore);
            }
        }
        System.err.println("Contatto non esistente con ID: " + id);
        return false;
    }

}
