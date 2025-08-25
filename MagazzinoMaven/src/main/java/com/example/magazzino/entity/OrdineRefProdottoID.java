package com.example.magazzino.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class OrdineRefProdottoID implements Serializable {
    private int ordine;
    private int prodotto;

    public OrdineRefProdottoID(int ordine, int prodotto) {
        this.ordine = ordine;
        this.prodotto = prodotto;
    }

    public OrdineRefProdottoID() {

    }

    public int getOrdine() {
        return ordine;
    }

    public void setOrdine(int ordine) {
        this.ordine = ordine;
    }

    public int getProdotto() {
        return prodotto;
    }

    public void setProdotto(int prodotto) {
        this.prodotto = prodotto;
    }
}
