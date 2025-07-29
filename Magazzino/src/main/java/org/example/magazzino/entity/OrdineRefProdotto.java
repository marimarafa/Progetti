package org.example.magazzino.entity;


import jakarta.persistence.*;
import org.example.magazzino.dao.OrdineDAO;
import org.example.magazzino.dao.ProdottoDAO;

@Entity(name = "ordinerefprodotto")
public class OrdineRefProdotto {

    @EmbeddedId
    private OrdineRefProdottoID id;

    @ManyToOne
    @MapsId("ordine")
    @JoinColumn(name = "ORDINE_ID")
    private Ordine ordine;

    @ManyToOne
    @MapsId("prodotto")
    @JoinColumn(name = "PRODOTTO_ID")
    private Prodotto prodotto;



    public OrdineRefProdotto() {}

    public OrdineRefProdotto(Ordine ordine, Prodotto prodotto) {
        this.ordine = ordine;
        this.prodotto = prodotto;
        this.id = new OrdineRefProdottoID(ordine.getId(), prodotto.getId());
    }

    public OrdineRefProdotto(int ordine, int prodotto) {
        OrdineDAO ordineDAO = new OrdineDAO();
        ProdottoDAO prodottoDAO = new ProdottoDAO();

        this.ordine = ordineDAO.selectById(ordine);
        this.prodotto = prodottoDAO.selectById(prodotto);
        this.id = new OrdineRefProdottoID(ordine, prodotto);
    }


    public OrdineRefProdottoID getId() {
        return id;
    }

    public void setId(OrdineRefProdottoID id) {
        this.id = id;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
}