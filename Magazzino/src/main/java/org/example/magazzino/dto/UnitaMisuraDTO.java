package org.example.magazzino.dto;

public class UnitaMisuraDTO {
    private int id;
    private String descrizione;

    public UnitaMisuraDTO() {
    }

    public UnitaMisuraDTO(int id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
