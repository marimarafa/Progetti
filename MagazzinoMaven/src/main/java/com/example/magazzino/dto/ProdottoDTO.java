package com.example.magazzino.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdottoDTO {
    private int id;
    @NotBlank(message = "Il campo nome non puo essere vuoto")
    private String nome;
    @Positive(message = "Il campo prezzo non puo essere vuoto")
    private double prezzo;
    @NotBlank(message = "Il campo descrizione non puo essere vuoto")
    private String descrizione;
    @Positive(message = "Il campo quantita non puo essere vuoto")
    private int quantita;
    private boolean disponibilita;
    @NotNull(message = "Il campo unita misura non puo essere null")
    @Valid
    private UnitaMisuraDTO unitaMisura_id ;
    @NotNull(message = "Il campo sotto categoria non puo essere null")
    @Valid
    private SottoCategoriaDTO sottoCategoria_id ;

    public ProdottoDTO(int id, String nome, double prezzo, String descrizione,int quantita,boolean disponibilita, UnitaMisuraDTO unitaMisura_id, SottoCategoriaDTO sottoCategoria_id) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.disponibilita = disponibilita;
        this.quantita = quantita;
        this.unitaMisura_id = unitaMisura_id;
        this.sottoCategoria_id = sottoCategoria_id;
    }

    public ProdottoDTO(int id) {
        this.id = id;
    }

    public ProdottoDTO() {
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
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

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnitaMisuraDTO getUnitaMisura_id() {
        return unitaMisura_id;
    }

    public void setUnitaMisura_id(UnitaMisuraDTO unitaMisura_id) {
        this.unitaMisura_id = unitaMisura_id;
    }

    public SottoCategoriaDTO getSottoCategoria_id() {
        return sottoCategoria_id;
    }

    public void setSottoCategoria_id(SottoCategoriaDTO sottoCategoria_id) {
        this.sottoCategoria_id = sottoCategoria_id;
    }
}