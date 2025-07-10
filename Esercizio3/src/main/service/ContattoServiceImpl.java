package main.service;


import main.dao.ConnessioneDB;
import main.entity.Contatto;
import main.entity.Notifica;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContattoServiceImpl implements ContattoService {

//    @Override
//    public List<Contatto> caricaDaCSV(String file) {
//        List<Contatto> contatti = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String riga;
//            while ((riga = br.readLine()) != null) {
//                String[] dati = riga.split(",");
//                int id = Integer.parseInt(dati[0]);
//                String nome = dati[1];
//                String cognome = dati[2];
//                String indirizzo = dati[3];
//                String telefono = dati[4];
//                String email = dati[5];
//                LocalDate dataNascita = LocalDate.parse(dati[6]);
//                Notifica notifica = new Notifica(dati[7],LocalDate.parse(dati[8]),dati[9],Boolean.parseBoolean(dati[10]));
//
//
//                contatti.add(new Contatto(id,nome,cognome,indirizzo,telefono,email,dataNascita,notifica));
//                Collections.sort(contatti);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return contatti;
//    }

    @Override
    public boolean addContatto (Contatto contatto,String file) {
        List<Contatto> contatti = ConnessioneDB.CaricaDaDB();
        for (Contatto c : contatti) {
            if (c.getId() == contatto.getId()) {
                System.out.println("Errore: ID già esistente.");
                return false;
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file,true))) {
            writer.println(contatto.toCSV());
            return true;
        } catch (IOException e) {
            System.err.println("Errore durante l'aggiunta del contatto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Contatto> cercaContattoNome (String nome,String file){
        List<Contatto> risultati = new ArrayList<>();
        for (Contatto contatto : ConnessioneDB.CaricaDaDB()) {
            if (contatto.getNome().equalsIgnoreCase(nome)) {
                risultati.add(contatto);
            }
        }
        return risultati;
    }

    @Override
    public List<Contatto> cercaContattoPattern (String regex,String file){
        List<Contatto> risultati = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for (Contatto contatto : ConnessioneDB.CaricaDaDB()) {
            Matcher matcher = pattern.matcher(contatto.getNome());
            if (matcher.find()) {
                risultati.add(contatto);
            }
        }
        return risultati;
    }

    @Override
    public boolean ControlloId(int id, String file) {
        List<Contatto> contatti = ConnessioneDB.CaricaDaDB();
        for (Contatto contatto : contatti) {
            if (id == contatto.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean ControlloRegex(String regex,String campo){
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(campo);
                return matcher.matches();
    }



    }
