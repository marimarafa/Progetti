package main.java.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Notifica {
    private int id;
    private String tipo;
    private LocalDateTime data;
    private String descrizione;
    private boolean inviata;
    private int idContatto;

    public Notifica(int id,String tipo, LocalDateTime data, String descrizione, boolean inviata,int idContatto) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.descrizione = descrizione;
        this.inviata = inviata;
        this.idContatto = idContatto;
    }

    public Notifica(String tipo, LocalDateTime data, String descrizione, boolean inviata, int idContatto) {
        this.tipo = tipo;
        this.data = data;
        this.descrizione = descrizione;
        this.inviata = inviata;
        this.idContatto = idContatto;
    }

    public int getIdContatto() {
        return idContatto;
    }

    public void setIdContatto(int idContatto) {
        this.idContatto = idContatto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isInviata() {
        return inviata;
    }

    public void setInviata(boolean inviata) {
        this.inviata = inviata;
    }

//    public String toCSV() {
//        return tipo + "-" + data + "-" + descrizione + "-" + inviata;
//    }


    @Override
    public String toString() {
        return "Notifica{" +
                "tipo='" + tipo + '\'' +
                ", data=" + data +
                ", descrizione='" + descrizione + '\'' +
                ", inviata=" + inviata +
                '}';
    }
}
