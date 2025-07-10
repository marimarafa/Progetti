package main.entity;


import java.time.LocalDate;

public class Notifica {
    private int id;
    private String tipo;
    private LocalDate data;
    private String descrizione;
    private boolean inviata;

    public Notifica(int id,String tipo, LocalDate data, String descrizione, boolean inviata) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.descrizione = descrizione;
        this.inviata = inviata;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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

    public String toCSV() {
        return tipo + "-" + data + "-" + descrizione + "-" + inviata;
    }


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
