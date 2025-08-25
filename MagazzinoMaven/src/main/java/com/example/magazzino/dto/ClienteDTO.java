package com.example.magazzino.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ClienteDTO {
    private int id;
    @Pattern(regexp = "^[A-Z][a-z]+" , message = "Inserire un nome valido")
    @NotBlank(message = "Il campo nome non puo essere vuoto")
    private String nome;
    @Pattern(regexp = "^[A-Z][a-z]+" , message = "Inserire un cognome valido")
    @NotBlank(message = "Il campo cognome non puo essere vuoto")
    private String cognome;
    private String indirizzo;
    @Email(message = "Inserire un email valido")
    private String email;
    @NotBlank(message = "Il campo codiceFiscale non puo essere null")
    @Pattern(regexp = "^[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?$" ,
            message = "Inserire un codice fiscale valido")
    private String codiceFiscale;
    @Pattern(regexp = "^([+-]?(?=\\\\.\\\\d|\\\\d)(?:\\\\d+)?(?:\\\\.?\\\\d*))(?:[Ee]([+-]?\\\\d+))?\\\\s[0-9]+$", message = "Inserire un numero di telefono valido")
    private String telefono;
    @NotBlank(message = "Il campo numeroCarta non puo essere null")
    private String numeroCarta;
    private String partitaIva;

    public ClienteDTO(int id, String nome, String cognome,String indirizzo,String codiceFiscale,String email,String telefono,String numeroCarta,String partitaIva) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.telefono = telefono;
        this.numeroCarta = numeroCarta;
        this.partitaIva = partitaIva;

    }

    public ClienteDTO() {
    }

    public ClienteDTO(int id) {
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }


}
