package org.example.magazzino.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SottoCategoriaDTO {

    private int id;
    @NotBlank(message = "Il campo nome non puo essere vuoto")
    private String nome;
    @NotNull(message = "Il campo categoria non puo essere null")
    @Valid
    private CategoriaDTO categoriaId ;


    public SottoCategoriaDTO(int id, String nome, CategoriaDTO categoriaId) {
        this.id = id;
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public SottoCategoriaDTO() {
    }

    public SottoCategoriaDTO(int id) {
        this.id = id;
    }

    public CategoriaDTO getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(CategoriaDTO categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
