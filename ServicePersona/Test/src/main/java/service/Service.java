package main.java.service;

import main.java.entity.Persona;

import java.util.ArrayList;
import java.util.Map;

public interface Service {
    public ArrayList<Persona> getListaPersone(String stringa) throws EsercizioCorsoException;
    public int getMediaEta(String stringa) throws EsercizioCorsoException;
    public Map<String,Integer> getMappaComuni(String stringa) throws EsercizioCorsoException;
    public ArrayList<Persona> getPersoneComune(String comune,String stringa) throws EsercizioCorsoException;
    public ArrayList<Persona> getPersoneNome(String nome,String stringa) throws EsercizioCorsoException;

}
