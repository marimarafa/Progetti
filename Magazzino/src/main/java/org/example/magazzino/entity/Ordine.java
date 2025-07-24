package org.example.magazzino.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "ordine")
public class Ordine {
    @Id
    @GeneratedValue
    private int id;
    private int quantita;
    private LocalDateTime data_ora;
    private double prezzo_totale;
    private boolean sospeso;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Cliente_id",referencedColumnName = "id")
    private Cliente cliente ;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<Prodotto> prodotti ;




    public Ordine(int id, int quantita, LocalDateTime data_ora, double prezzo_totale,boolean sospeso,Cliente cliente, List<Prodotto> prodotti) {
        this.id = id;
        this.sospeso = sospeso;
        this.data_ora = data_ora;
        this.quantita = quantita;
        this.prezzo_totale = prezzo_totale;
        this.cliente = cliente;
        this.prodotti = prodotti;
    }

    public Ordine() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
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