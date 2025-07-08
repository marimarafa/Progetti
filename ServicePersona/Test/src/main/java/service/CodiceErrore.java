package main.java.service;

public enum CodiceErrore {
    EX001 ("EX001","file not found"),
    EX002 ("EX002","property not found");

    private final String messaggio;
    private final String codice;

    CodiceErrore(String codice, String messaggio) {
        this.codice = codice;
        this.messaggio = messaggio;
    }

    public String getCodice() {
        return codice;
    }
    public String getMessaggio() {
        return messaggio;
    }
}
