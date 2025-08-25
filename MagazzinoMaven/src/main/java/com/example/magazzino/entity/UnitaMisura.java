package com.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "unitamisura")
public class UnitaMisura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "DESCRIZIONE")
    private String descrizione;

    public UnitaMisura() {
    }

    public UnitaMisura(int id) {
        this.id = id;
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
