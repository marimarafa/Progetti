package main.java.thread;


import main.java.dao.DaoContatto;
import main.java.dao.DaoNotifica;
import main.java.entity.Notifica;

import java.time.LocalDateTime;
import java.util.List;

public class ThreadNotifica extends Thread{
    private String tipoNotifica;

    public ThreadNotifica(String tipoNotifica) {
        this.tipoNotifica = tipoNotifica;
    }



    DaoNotifica dao = new DaoNotifica();

    @Override
    public void run(){
         List<Notifica> notifiche = dao.selectNotifica();
         DaoContatto daoC = new DaoContatto();

         for (Notifica notifica : notifiche) {
             if (notifica.getTipo().equalsIgnoreCase(tipoNotifica) && !notifica.isInviata()) {
                 if (!notifica.getData().isAfter(LocalDateTime.now())) {
                     String email = daoC.getEmailById(notifica.getIdContatto());

                     System.out.println("Thread: " + Thread.currentThread().getName() +
                             " Tipo: " + tipoNotifica +
                             " Email: " + email +
                             " Descrizione: " + notifica.getDescrizione());

                     dao.updateNotifica(notifica.getId(), "inviata", "1");
                 }
             }
         }
    }

}
