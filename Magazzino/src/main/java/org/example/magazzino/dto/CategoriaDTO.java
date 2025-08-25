package org.example.magazzino.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoriaDTO {
    private int id;
    @NotBlank(message = "Il campo nome non puo essere vuoto")
    private String nome;

    public CategoriaDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO() {
    }

    public CategoriaDTO(int id) {
        this.id = id;
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
