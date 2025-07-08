package main.java.entity;

public class Persona implements  Comparable<Persona>{
    String nome;
    String cognome;
    int eta;
    String comune;

    public Persona(String nome, String cognome, int eta, String comune) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.comune = comune;
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

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                ", comune='" + comune + '\'' +
                '}';
    }

    @Override
    public int compareTo(Persona o) {
        return getNome().compareTo(o.getNome());
    }
}
