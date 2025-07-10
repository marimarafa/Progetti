package main.entity;


import java.time.LocalDate;
import java.util.Date;

public class Contatto implements  Comparable<Contatto>{
    private int id ;
    private String nome , cognome , telefono ,indirizzo , email;
    private Notifica notifica;
    private LocalDate dataNascita;

    public Contatto() {
    }

    public Contatto(int id ,String nome, String cognome, String indirizzo, String telefono, String email,LocalDate dataNascita, Notifica notifica) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.email = email;
        this.dataNascita = dataNascita;
        this.notifica = notifica;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getDataNascita() {return dataNascita;}

    public void setDataNascita(LocalDate dataNascita) {this.dataNascita = dataNascita;}

    public Notifica getNotifica() {
        return notifica;
    }

    public void setNotifica(Notifica notifica) {
        this.notifica = notifica;
    }

    @Override
    public String toString() {
        return "Contatto{" +
                "id='" + id + '\''+
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", dataNascita=" + dataNascita +
                ", notifica=" + notifica +

                '}';
    }

    public String toCSV() {
        return id + "," + nome + "," + cognome + "," + indirizzo + "," + telefono + "," + email + "," +
                dataNascita + "," + notifica.toCSV();
    }



    @Override
    public int compareTo(Contatto o) {
        return nome.compareTo(o.nome);
    }
}
