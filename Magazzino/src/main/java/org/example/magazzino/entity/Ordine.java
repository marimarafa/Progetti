package org.example.magazzino.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "ordine")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "QUANTITA")
    private int quantita;
    @Column(name = "DATA_ORA")
    private LocalDateTime dataOra;
    @Column(name = "PREZZO_TOTALE")
    private double prezzoTotale;
    @Column(name = "SOSPESO")
    private boolean sospeso;



    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CLIENTE_ID",referencedColumnName = "ID")
    private Cliente clienteId ;





    public Ordine(int id, int quantita, LocalDateTime dataOra, double prezzoTotale,boolean sospeso,Cliente clienteId) {
        this.id = id;
        this.sospeso = sospeso;
        this.dataOra = dataOra;
        this.quantita = quantita;
        this.prezzoTotale = prezzoTotale;
        this.clienteId = clienteId;

    }

    public Ordine() {
    }

    public Ordine(int id) {
        this.id = id;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
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

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}