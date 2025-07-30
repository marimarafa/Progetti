package org.example.magazzino.dto;

public class ClienteDTO {
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String codiceFiscale;
    private String telefono;
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
