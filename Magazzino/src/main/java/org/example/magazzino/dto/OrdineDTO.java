package org.example.magazzino.dto;

import java.time.LocalDateTime;

public class OrdineDTO {
    private int id;
    private int quantita;
    private LocalDateTime dataOra;
    private double prezzoTotale;
    private boolean sospeso;


    private ClienteDTO clienteId;

    public OrdineDTO(int id) {
        this.id = id;
    }

    public OrdineDTO() {
    }

    public OrdineDTO(int id, int quantita, LocalDateTime dataOra, double prezzoTotale, boolean sospeso, ClienteDTO clienteId) {
        this.id = id;
        this.quantita = quantita;
        this.dataOra = dataOra;
        this.prezzoTotale = prezzoTotale;
        this.sospeso = sospeso;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public boolean isSospeso() {
        return sospeso;
    }

    public void setSospeso(boolean sospeso) {
        this.sospeso = sospeso;
    }

    public ClienteDTO getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteDTO clienteId) {
        this.clienteId = clienteId;
    }
}
