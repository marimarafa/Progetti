package org.example.magazzino.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrdineDTO {
    private int id;
    private int quantita;
    private LocalDateTime data_ora;
    private boolean sospeso;
    private double prezzo_totale;

    private ClienteDTO cliente_id ;
    private List<ProdottoDTO> prodotto_id;

    public OrdineDTO(int id, int quantita, LocalDateTime data_ora, double prezzo_totale,boolean sospeso,ClienteDTO cliente_id, List<ProdottoDTO> prodotto_id) {
        this.id = id;
        this.sospeso = sospeso;
        this.data_ora = data_ora;
        this.quantita = quantita;
        this.prezzo_totale = prezzo_totale;
        this.cliente_id = cliente_id;
        this.prodotto_id = prodotto_id;
    }

    public OrdineDTO() {
    }

    public double getPrezzo_totale() {
        return prezzo_totale;
    }

    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }

    public ClienteDTO getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(ClienteDTO cliente_id) {
        this.cliente_id = cliente_id;
    }

    public List<ProdottoDTO> getProdotto_id() {
        return prodotto_id;
    }

    public void setProdotto_id(List<ProdottoDTO> prodotto_id) {
        this.prodotto_id = prodotto_id;
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