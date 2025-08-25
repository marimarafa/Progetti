package org.example.magazzino.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.example.magazzino.dao.OrdineDAO;
import org.example.magazzino.dao.ProdottoDAO;
import org.example.magazzino.entity.Ordine;
import org.example.magazzino.entity.OrdineRefProdottoID;
import org.example.magazzino.entity.Prodotto;


public class OrdineRefProdottoDTO {
    @Positive(message = "Il campo ordine non puo essere null")
    private int ordine;
    @Positive(message = "Il campo prodotto non puo essere null")
    private int prodotto;

    public OrdineRefProdottoDTO() {}

    public OrdineRefProdottoDTO(int ordine, int prodotto) {
        this.ordine = ordine;
        this.prodotto = prodotto;

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