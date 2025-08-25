package org.example.magazzino.dto;

import jakarta.validation.constraints.NotBlank;

public class UnitaMisuraDTO {
    private int id;
    @NotBlank(message = "Il campo descrizione non puo essere vuoto")
    private String descrizione;

    public UnitaMisuraDTO() {
    }

    public UnitaMisuraDTO(int id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public UnitaMisuraDTO(int id) {
        this.id = id;
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
