package org.example.magazzino.dto;

import org.example.magazzino.entity.Ordine;

import java.time.LocalDateTime;

public class MovimentoDTO {
    private int id;
    private String tipo;
    private LocalDateTime data_ora;
    private String descrizione;

    private Ordine ordine;

    public MovimentoDTO(String descrizione, LocalDateTime data_ora, String tipo, int id,Ordine ordine) {
        this.descrizione = descrizione;
        this.data_ora = data_ora;
        this.tipo = tipo;
        this.id = id;
        this.ordine = ordine;
    }

    public MovimentoDTO() {
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
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

    public LocalDateTime getData_ora() {
        return data_ora;
    }

    public void setData_ora(LocalDateTime data_ora) {
        this.data_ora = data_ora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
