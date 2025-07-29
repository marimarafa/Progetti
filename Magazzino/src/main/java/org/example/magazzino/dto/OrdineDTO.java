package org.example.magazzino.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdineDTO {
    private int id;
    private int quantita;
    private LocalDateTime data_ora;
    private double prezzo_totale;
    private boolean sospeso;


    private ClienteDTO clienteId;

    public OrdineDTO(int id) {
        this.id = id;
    }

    public OrdineDTO() {
    }

    public OrdineDTO(int id, int quantita, LocalDateTime data_ora, double prezzo_totale, boolean sospeso, ClienteDTO clienteId) {
        this.id = id;
        this.quantita = quantita;
        this.data_ora = data_ora;
        this.prezzo_totale = prezzo_totale;
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

    public LocalDateTime getData_ora() {
        return data_ora;
    }

    public void setData_ora(LocalDateTime data_ora) {
        this.data_ora = data_ora;
    }

    public double getPrezzo_totale() {
        return prezzo_totale;
    }

    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
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
