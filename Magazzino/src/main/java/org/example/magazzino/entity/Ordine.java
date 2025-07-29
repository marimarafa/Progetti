package org.example.magazzino.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "ordine")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "QUANTITA")
    private int quantita;
    @Column(name = "DATA_ORA")
    private LocalDateTime data_ora;
    @Column(name = "PREZZO_TOTALE")
    private double prezzo_totale;
    @Column(name = "SOSPESO")
    private boolean sospeso;



    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CLIENTE_ID",referencedColumnName = "ID")
    private Cliente clienteId ;





    public Ordine(int id, int quantita, LocalDateTime data_ora, double prezzo_totale,boolean sospeso,Cliente clienteId) {
        this.id = id;
        this.sospeso = sospeso;
        this.data_ora = data_ora;
        this.quantita = quantita;
        this.prezzo_totale = prezzo_totale;
        this.clienteId = clienteId;

    }

    public Ordine() {
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
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