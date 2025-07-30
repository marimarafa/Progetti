package org.example.magazzino.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProdottiOrdineDTO {
    OrdineDTO ordineDTO;
    List<ProdottoDTO> prodotti;

    public ProdottiOrdineDTO(OrdineDTO ordineDTO, List<ProdottoDTO> prodotti) {
        this.ordineDTO = ordineDTO;
        this.prodotti = prodotti;
    }

    public ProdottiOrdineDTO() {
    }


    public OrdineDTO getOrdineDTO() {
        return ordineDTO;
    }

    public void setOrdineDTO(OrdineDTO ordineDTO) {
        this.ordineDTO = ordineDTO;
    }

    public List<ProdottoDTO> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<ProdottoDTO> prodotti) {
        this.prodotti = prodotti;
    }
}
