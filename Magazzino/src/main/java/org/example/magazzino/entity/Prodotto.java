package org.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "PREZZO")
    private double prezzo;
    @Column(name = "DESCRIZIONE")
    private String descrizione;
    @Column(name = "DISPONIBILITA")
    private boolean disponibilita;
    @Column(name = "QUANTITA")
    private int quantita;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "UNITAMISURA_ID",referencedColumnName = "ID")
    private UnitaMisura unitaMisura_id ;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SOTTOCATEGORIA_ID",referencedColumnName = "ID")
    private SottoCategoria sottoCategoria_id ;

    public Prodotto(int id, String nome, double prezzo, String descrizione,int quantita,boolean disponibilita, UnitaMisura unitaMisura, SottoCategoria sottoCategoria) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.unitaMisura_id = unitaMisura;
        this.sottoCategoria_id = sottoCategoria;
        this.quantita = quantita;
        this.disponibilita = disponibilita;
    }

    public Prodotto(int id) {
        this.id = id;
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

    public UnitaMisura getUnitaMisura() {
        return unitaMisura_id;
    }

    public void setUnitaMisura(UnitaMisura unitaMisura) {
        this.unitaMisura_id = unitaMisura;
    }

    public SottoCategoria getSottoCategoria() {
        return sottoCategoria_id;
    }

    public void setSottoCategoria(SottoCategoria sottoCategoria) {
        this.sottoCategoria_id = sottoCategoria;
    }

    public Prodotto() {
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