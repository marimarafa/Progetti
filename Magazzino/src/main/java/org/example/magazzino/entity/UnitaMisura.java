package org.example.magazzino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "unitamisura")
public class UnitaMisura {
    @Id
    @GeneratedValue
    private int id;
    private String descrizione;

    public UnitaMisura() {
    }

    public UnitaMisura(int id, String descrizione) {
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
