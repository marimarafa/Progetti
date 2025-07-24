package org.example.magazzino.dto;

import org.example.magazzino.entity.SottoCategoria;
import org.example.magazzino.entity.UnitaMisura;

public class ProdottoDTO {
    private int id;
    private String nome;
    private double prezzo;
    private String descrizione;
    private int quantita;
    private boolean disponibilita;

    private UnitaMisuraDTO unitaMisura ;
    private SottoCategoriaDTO sottoCategoria ;

    public ProdottoDTO(int id, String nome, double prezzo, String descrizione,int quantita,boolean disponibilita, UnitaMisuraDTO unitaMisura, SottoCategoriaDTO sottoCategoria) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.disponibilita = disponibilita;
        this.quantita = quantita;
        this.unitaMisura = unitaMisura;
        this.sottoCategoria = sottoCategoria;
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

    public SottoCategoriaDTO getSottoCategoria() {
        return sottoCategoria;
    }

    public void setSottoCategoria(SottoCategoriaDTO sottoCategoria) {
        this.sottoCategoria = sottoCategoria;
    }

    public UnitaMisuraDTO getUnitaMisura() {
        return unitaMisura;
    }

    public void setUnitaMisura(UnitaMisuraDTO unitaMisura) {
        this.unitaMisura = unitaMisura;
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
}