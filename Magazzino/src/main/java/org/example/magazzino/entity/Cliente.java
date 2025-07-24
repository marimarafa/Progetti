package org.example.magazzino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "cliente")
public class Cliente {
    @Id
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String codice_fiscale;
    private String telefono;
    private String numero_carta;
    private String partita_iva;

    public Cliente(int id, String nome,String cognome,String indirizzo,String codice_fiscale,String email,String telefono,String numero_carta,String partita_iva) {
        this.id = id;
        this.partita_iva = partita_iva;
        this.numero_carta = numero_carta;
        this.telefono = telefono;
        this.codice_fiscale = codice_fiscale;
        this.email = email;
        this.indirizzo = indirizzo;
        this.cognome = cognome;
        this.nome = nome;
    }

    public Cliente() {
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

    public String getCodice_fiscale() {
        return codice_fiscale;
    }

    public void setCodice_fiscale(String codice_fiscale) {
        this.codice_fiscale = codice_fiscale;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumero_carta() {
        return numero_carta;
    }

    public void setNumero_carta(String numero_carta) {
        this.numero_carta = numero_carta;
    }

    public String getPartita_iva() {
        return partita_iva;
    }

    public void setPartita_iva(String partita_iva) {
        this.partita_iva = partita_iva;
    }
}
