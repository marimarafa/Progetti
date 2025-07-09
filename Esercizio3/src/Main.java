import main.entity.Contatto;
import main.entity.Notifica;
import main.service.ContattoServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String file = "Rubrica.csv";
        String email,telefono, nome,cognome;
        LocalDate dataNascita;
        String regexTelefono = "^([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?\\s[0-9]+$";
        String regexEmail ="^[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?$";
        String regexNomeCognome = "^[A-Z][a-z]+";
        String regexData = "[0-9]{4}-[0-9]{2}-[0-9]{2}";


        Scanner scanner = new Scanner(System.in);
        ContattoServiceImpl service = new ContattoServiceImpl();

        boolean exit = true;
        while (exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Inserimento contatto");
            System.out.println("2) Ricerca contatti");
            System.out.println("3) Carica contatti dal CSV");
            System.out.println("4) Esci");


            System.out.print("Scegli un'opzione: ");
            String input = scanner.next();

            int scelta;
            try {
                scelta = Integer.parseInt(input);
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Opzione non valida, riprova.");
                continue;
            }

            switch (scelta) {
                case 1:
                    int id;
                    while (true) {
                        System.out.print("Inserisci ID: ");
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            if (service.ControlloId(id, file)) {
                                System.out.println("ID già esistente, riprova con un altro.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Inserisci un numero intero valido per l'ID.");
                        }
                    }
                    while (true) {
                        System.out.print("Inserisci nome: ");
                        nome = scanner.nextLine();
                        if(!service.ControlloRegex(regexNomeCognome,nome)){
                            System.out.println("Nome non valido riprovare");
                        } else{
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Inserisci cognome: ");
                        cognome = scanner.nextLine();
                        if(!service.ControlloRegex(regexNomeCognome,cognome)){
                            System.out.println("Cognome non valido riprovare");
                        } else{
                            break;
                        }
                    }
                    System.out.print("Inserisci indirizzo: ");
                    String indirizzo = scanner.nextLine();
                    while (true) {
                        System.out.print("Inserisci email: ");
                        email = scanner.nextLine();
                        if(!service.ControlloRegex(regexEmail,email)){
                            System.out.println("Email non valida riprovare");
                        } else{
                            break;
                        }
                    }
                    while (true) {
                        System.out.print("Inserisci telefono: ");
                        telefono = scanner.nextLine();
                        if(!service.ControlloRegex(regexTelefono, telefono)){
                            System.out.println("Numero telefono non valido riprovare");
                        } else{
                            break;
                        }
                    }

                    System.out.print("Inserisci una notifica: ");

                    while (true) {
                        System.out.print("Inserisci data nascita: ");
                        dataNascita = LocalDate.parse(scanner.nextLine());
                        if(!service.ControlloRegex(regexData,String.valueOf(dataNascita))){
                            System.out.println("Data non valida riprovare");
                        } else{
                            break;
                        }
                    }

                    Contatto contatto = new Contatto(id, nome, cognome, indirizzo ,telefono, email, notifica,dataNascita);
                    if (service.addContatto(contatto, file)) {
                        System.out.println("Contatto aggiunto con successo.");
                    } else {
                        System.out.println("Errore: contatto già presente.");
                    }
                    break;

                case 2:
                    System.out.print("Inserisci nome o pattern da cercare: ");
                    String pattern = scanner.nextLine();
                    List<Contatto> risultati = service.cercaContattoPattern(pattern, file);
                    if (risultati.isEmpty()) {
                        System.out.println("Nessun contatto trovato.");
                    } else {
                        System.out.println("Contatti trovati:");
                        for (Contatto c : risultati) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.println(service.caricaDaCSV(file));
                    break;

                case 4:
                    exit = false;
                    System.out.println("Uscita dal programma.");
                    break;

                default:
                    System.out.println("Opzione non valida, riprova.");
                    break;
            }
        }

        scanner.close();
    }
}