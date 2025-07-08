package main.java;

import main.java.config.LetturaFile;
import main.java.entity.Persona;
import main.java.service.EsercizioCorsoException;
import main.java.service.ServiceImpl;


public class Main {
    public static void main(String[] args) throws EsercizioCorsoException {
//        String stringa = "cognome:Ocrammaig, nome:Giammarco, eta:18, comune:Roma;" +
//                " nome:Alessandro, eta:19, cognome:Ordnassela, comune:Bari;" +
//                " nome:Marim, cognome:Miram, comune:Roma, eta:30;" +
//                " nome:Davide, cognome:Edivad, eta:23, comune:Torino";


        String path = "resources/persone.properties";
        LetturaFile lf = new LetturaFile();
        System.out.println(lf.getFile(path));


        ServiceImpl service = new ServiceImpl();
        for(Persona persona: service.getListaPersone(lf.getFile(path))){
           System.out.println(persona);
        }



        System.out.println(service.getMediaEta(lf.getFile(path)));
        System.out.println(" Comune: "+service.getMappaComuni(lf.getFile(path)));
        System.out.println("Persone in comune roma: " + service.getPersoneComune("Roma", lf.getFile(path)));
        System.out.println("Persone con il nome Marim :" + service.getPersoneNome("Marim", lf.getFile(path)));
    }

}