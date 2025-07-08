package main.java.service;

import main.java.entity.Persona;
import main.java.entity.PersonaEnum;

import java.util.*;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    //  public String stringa = "Giammarco, Cognome1, 18, Roma;Alessandro, Cognome2, 19, Bari;Marim, Cognome3, 30, Roma;Davide, Cognome4, 23, Torino";

//    public String[] listaPersone(){
//        String [] persone = stringa.split(";");
//        return persone;
//    }
//
//
//    public int mediaEtà() {
//        String [] persone = listaPersone();
//        int totEtà = 0;
//        for (String persona : persone) {
//            String[] dati = persona.split(", ");
//            totEtà += Integer.parseInt(dati[2]);
//
//        }
//        int media = totEtà / persone.length;
//        return media;
//
//    }
//
//    public String[] OrdinaLista() {
//        String[] persone = listaPersone();
//        Arrays.sort(persone, String::compareTo);
//        return persone;
//    }
//
//    public Map<String,Integer> ComuniPersone(){
//        Map<String,Integer> mappa = new HashMap<>();
//        String[] persone = listaPersone();
//        for(String persona : persone){
//            String[] dati = persona.split(", ");
//            mappa.put(dati[3], mappa.getOrDefault(dati[3], 0) + 1);
//        }
//        return mappa;
//
//    }

//----------------------------------------------------------------------------------------------------------------------


//    @Override
//    public ArrayList<Persona> getListaPersone(){
//        ArrayList<Persona> persone = new ArrayList<>();
//        String [] stringhe = stringa.split(";");
//        for(String stringa : stringhe){
//            String[] dati = stringa.split(", ");
//            String nome = dati[0];
//            String cognome = dati[1];
//            int eta = Integer.parseInt(dati[2]);
//            String comune = dati[3];
//            persone.add(new Persona(nome,cognome,eta,comune));
//        }
//        Collections.sort(persone);
//        return persone;
//    }

    /**
     * Questo metodo calcola la media dell' età delle persone passate in una lista.
     *
     * @return media  La divisione dell 'eta totale delle persone e il numero di persone(la lunghezza della lista).
     * @input persone  Una lista di persone con nome, cognome, età, comune.
     */

    @Override
    public int getMediaEta(String stringa) throws EsercizioCorsoException {
        try {
            ArrayList<Persona> persone = getListaPersone(stringa);
            int totEta = 0;
            for (Persona persona : persone) {
                totEta += persona.getEta();
            }
            int media = totEta / persone.toArray().length;
            return media;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo restituisce una mappa che ha come chiave il nome del comune e come valore il numero di persone appartenenti a quel comune.
     *
     * @return mappaOrdinata  Una mappa ordinata dei comuni e il numero di persone appartenenti al comune.
     * @input persone  Una lista di persone con nome, cognome, età, comune.
     */
    @Override
    public Map<String, Integer> getMappaComuni(String stringa) throws EsercizioCorsoException {
        try {
            ArrayList<Persona> persone = getListaPersone(stringa);
            Map<String, Integer> comuni = new HashMap<>();
            for (Persona persona : persone) {
                if (comuni.containsKey(persona.getComune())) {
                    comuni.put(persona.getComune(), comuni.get(persona.getComune()) + 1);
                } else {
                    comuni.put(persona.getComune(), 1);
                }
            }
            Map<String, Integer> mappaOrdinata = comuni.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));

            return mappaOrdinata;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo restituisce una lista di persone appartenenti a un certo comune.
     *
     * @param comune il comune a cui appartengono una lista di persone.
     * @return personePerComune  La lista di persone appartenenti a quel comune.
     */
    @Override
    public ArrayList<Persona> getPersoneComune(String comune, String stringa) throws EsercizioCorsoException {
        try {
            ArrayList<Persona> persone = getListaPersone(stringa);
            ArrayList<Persona> personePerComune = new ArrayList<>();
            for (Persona persona : persone) {
                if (comune.equals(persona.getComune())) {
                    personePerComune.add(persona);
                }
            }
            return personePerComune;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo prende in ingresso un nome di persona e cerca se e presente in una lista di persone quindi restituisce tutte le persone che hanno quel nome.
     *
     * @param nome Nome persona da cercare
     * @return personePerNome Lista persona trovate con quel nome
     */

    @Override
    public ArrayList<Persona> getPersoneNome(String nome, String stringa) throws EsercizioCorsoException {
        try {
            ArrayList<Persona> persone = getListaPersone(stringa);
            ArrayList<Persona> personePerNome = new ArrayList<>();
            for (Persona persona : persone) {
                if (nome.equals(persona.getNome())) {
                    personePerNome.add(persona);
                }
            }
            return personePerNome;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo aggiorna la stringa dichiarata all inizio e la trasforma in una lista di persone utilizzando gli ENUM di persona
     * Con lo switch si trasforma la stringa in una lista di persone con i seguenti attributi:
     * nome, cognome, eta, commune.
     *
     * @return persone Una lista di persone con il seguente ordine di attributi: nome, cognome, eta, comune.
     */
    @Override
    public ArrayList<Persona> getListaPersone(String stringa) throws EsercizioCorsoException {
        ArrayList<Persona> persone = new ArrayList<>();
        String[] stringhe = stringa.split(";");

        for (String s : stringhe) {
            String nome = null, cognome = null, comune = null;
            int eta = 0;
            String[] dati = s.split(", ");

            try {
                for (String dato : dati) {
                    String[] attributo = dato.split(":");
                    if (attributo.length < 2) {
                        System.err.println("Valore mancante del parametro :" + attributo[0]);
                        continue;
                    }
                    String param = attributo[0].replace("\"", "").trim().toUpperCase();
                    String valore = attributo[1].trim();
                    try {
                        PersonaEnum.valueOf(param);
                    } catch (IllegalArgumentException ex) {
                        System.err.println("Parametro non valido: " + param);
                        continue;
                    }
                    switch (PersonaEnum.valueOf(param)) {
                        case NOME:
                            nome = valore;
                            break;
                        case COGNOME:
                            cognome = valore;
                            break;
                        case ETA:
                            try {
                                eta = Integer.parseInt(valore);
                            } catch (NumberFormatException e) {
                                System.err.println("Errore: valore di: " + param + " non valido");
                                eta = 0;
                            }
                            break;
                        case COMUNE:
                            comune = valore;
                            break;
                    }
                }
                persone.add(new Persona(nome, cognome, eta, comune));
            } catch (RuntimeException e) {
                System.err.println("Errore " + e.getMessage());
            }

        }
        return persone;
    }
}




