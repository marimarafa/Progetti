package org.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "COGNOME")
    private String cognome;
    @Column(name = "INDIRIZZO")
    private String indirizzo;
    @Column(name = "CODICE_FISCALE")
    private String codiceFiscale;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "NUMERO_CARTA")
    private String numeroCarta;
    @Column(name = "PARTITA_IVA")
    private String partitaIva;




    public Cliente(int id, String nome,String cognome,String indirizzo,String codiceFiscale,String email,String telefono,String numeroCarta,String partitaIva) {
        this.id = id;
        this.partitaIva = partitaIva;
        this.numeroCarta = numeroCarta;
        this.telefono = telefono;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.indirizzo = indirizzo;
        this.cognome = cognome;
        this.nome = nome;
    }

    public Cliente() {
    }


    public Cliente(int id) {
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
