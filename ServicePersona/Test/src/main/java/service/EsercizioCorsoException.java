package main.java.service;

public class EsercizioCorsoException extends Exception {
    private final String codice;

    public EsercizioCorsoException(CodiceErrore errore) {
        super(errore.getMessaggio());
        this.codice = errore.getCodice();
    }

    public String getCodice() {
        return codice;
    }

}
