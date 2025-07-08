import main.entity.Contatto;
import main.service.ContattoServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        String file = "Contatti.csv";

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
                    System.out.print("Inserisci nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Inserisci cognome: ");
                    String cognome = scanner.nextLine();
                    System.out.print("Inserisci indirizzo: ");
                    String indirizzo = scanner.nextLine();
                    System.out.print("Inserisci email: ");
                    String email = scanner.nextLine();
                    System.out.print("Inserisci telefono: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Errore: devi inserire un numero!");
                        scanner.nextLine();
                    }
                    int telefono = Integer.parseInt(scanner.nextLine());
                    System.out.print("Inserisci flag (int): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Errore: devi inserire un numero!");
                        scanner.nextLine();
                    }
                    int flag = Integer.parseInt(scanner.nextLine());

                    Contatto contatto = new Contatto(id, nome, cognome, indirizzo ,telefono, email, flag);
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