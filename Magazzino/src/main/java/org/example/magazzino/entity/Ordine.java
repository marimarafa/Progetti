package org.example.magazzino.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "ordine")
public class Ordine {
    @Id
    private int id;
    private int quantita;
    private LocalDateTime data_ora;
    private double prezzo_totale;
    private boolean sospeso;



    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Cliente_id",referencedColumnName = "id")
    private Cliente cliente_id ;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
    private List<Prodotto> prodotto_id ;




    public Ordine(int id, int quantita, LocalDateTime data_ora, double prezzo_totale,boolean sospeso,Cliente cliente_id, List<Prodotto> prodotto_id) {
        this.id = id;
        this.sospeso = sospeso;
        this.data_ora = data_ora;
        this.quantita = quantita;
        this.prezzo_totale = prezzo_totale;
        this.cliente_id = cliente_id;
        this.prodotto_id = prodotto_id;
    }

    public Ordine() {
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }

    public List<Prodotto> getProdotto_id() {
        return prodotto_id;
    }

    public void setProdotto_id(List<Prodotto> prodotto_id) {
        this.prodotto_id = prodotto_id;
    }

    public double getPrezzo_totale() {
        return prezzo_totale;
    }

    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSospeso() {
        return sospeso;
    }

    public void setSospeso(boolean sospeso) {
        this.sospeso = sospeso;
    }

    public LocalDateTime getData_ora() {
        return data_ora;
    }

    public void setData_ora(LocalDateTime data_ora) {
        this.data_ora = data_ora;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}