package org.example.magazzino.dto;

import org.example.magazzino.entity.Cliente;
import org.example.magazzino.entity.Prodotto;

import java.time.LocalDateTime;
import java.util.List;

public class OrdineDTO {
    private int id;
    private int quantita;
    private LocalDateTime data_ora;
    private boolean sospeso;
    private double prezzo_totale;

    private ClienteDTO cliente ;
    private List<ProdottoDTO> prodotti ;

    public OrdineDTO(int id, int quantita, LocalDateTime data_ora, double prezzo_totale,boolean sospeso,ClienteDTO cliente, List<ProdottoDTO> prodotti) {
        this.id = id;
        this.sospeso = sospeso;
        this.data_ora = data_ora;
        this.quantita = quantita;
        this.prezzo_totale = prezzo_totale;
        this.cliente = cliente;
        this.prodotti = prodotti;
    }

    public OrdineDTO() {
    }

    public double getPrezzo_totale() {
        return prezzo_totale;
    }

    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProdottoDTO> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<ProdottoDTO> prodotti) {
        this.prodotti = prodotti;
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