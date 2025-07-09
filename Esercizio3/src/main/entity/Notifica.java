package main.entity;


import java.time.LocalDate;

public class Notifica {
    private String tipo;
    private LocalDate dataNotifica;
    private String descrizione;
    private boolean inviata;

    public Notifica(String tipo, LocalDate dataNotifica, String descrizione, boolean inviata) {
        this.tipo = tipo;
        this.dataNotifica = dataNotifica;
        this.descrizione = descrizione;
        this.inviata = inviata;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataNotifica() {
        return dataNotifica;
    }

    public void setDataNotifica(LocalDate dataNotifica) {
        this.dataNotifica = dataNotifica;
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

    @Override
    public String toString() {
        return "Notifica{" +
                "tipo='" + tipo + '\'' +
                ", dataNotifica=" + dataNotifica +
                ", descrizione='" + descrizione + '\'' +
                ", inviata=" + inviata +
                '}';
    }
}
