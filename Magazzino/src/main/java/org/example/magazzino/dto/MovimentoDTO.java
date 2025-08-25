package org.example.magazzino.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.magazzino.entity.Ordine;

import java.time.LocalDateTime;

public class MovimentoDTO {
    private int id;
    @NotBlank(message = "Il campo tipo non puo essere vuoto")
    private String tipo;
    @NotNull(message = "Il campo dataOra non puo essere null")
    private LocalDateTime dataOra;
    @NotBlank(message = "Il campo descrizione non puo essere vuoto")
    private String descrizione;
    @NotNull(message = "Il campo ordine non puo essere null")
    @Valid
    private OrdineDTO ordine;

    public MovimentoDTO(String descrizione, LocalDateTime dataOra, String tipo, int id,OrdineDTO ordine) {
        this.descrizione = descrizione;
        this.dataOra = dataOra;
        this.tipo = tipo;
        this.id = id;
        this.ordine = ordine;
    }

    public MovimentoDTO() {
    }

    public MovimentoDTO(int id) {
        this.id = id;
    }

    public OrdineDTO getOrdine() {
        return ordine;
    }

    public void setOrdine(OrdineDTO ordine) {
        this.ordine = ordine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
