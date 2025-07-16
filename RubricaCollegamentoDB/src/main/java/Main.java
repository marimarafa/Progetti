package main.java;

import main.java.dao.DaoContatto;
import main.java.entity.Contatto;
import main.java.entity.Notifica;
import main.java.service.ContattoServiceImpl;
import main.java.thread.ThreadNotifica;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        ContattoServiceImpl service = new ContattoServiceImpl();

        String regexTelefono = "^([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?\\s[0-9]+$";
        String regexEmail = "^[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?$";
        String regexNomeCognome = "^[A-Z][a-z]+";
        String regexData = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

        boolean exit = true;
        while (exit) {
            System.out.println("\t😁--- MENU ---😁");
            System.out.println("1) Inserisci contatto");
            System.out.println("2) Ricerca contatti (DB)");
            System.out.println("3) Elimina contatto");
            System.out.println("4) Aggiorna contatto");
            System.out.println("5) Gestisci notifica");
            System.out.println("6) Esci");

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
                    System.out.println("--- INSERIMENTO CONTATTO ---");

                    String nome = service.ControlloRegex(regexNomeCognome, "Nome");
                    String cognome = service.ControlloRegex(regexNomeCognome, "Cognome");
                    String email = service.ControlloRegex(regexEmail, "Email");
                    String telefono = service.ControlloRegex(regexTelefono, "Telefono");
                    String dataStr = service.ControlloRegex(regexData, "Data di nascita (yyyy-MM-dd)");
                    LocalDate dataNascita = LocalDate.parse(dataStr);

                    System.out.print("Indirizzo: ");
                    String indirizzo = scanner.nextLine();

                    Contatto nuovoContatto = new Contatto(nome, cognome, indirizzo, telefono, email, dataNascita);

                    if (service.insertContatto(nuovoContatto)) {
                        System.out.println("Contatto inserito correttamente.");
                    } else {
                        System.out.println("Errore durante l'inserimento del contatto.");
                    }
                    break;

                case 2:
                    System.out.println("--- RICERCA CONTATTI ---");

                    List<Contatto> contatti = service.selectContatto();
                    if (contatti.isEmpty()) {
                        System.out.println("Nessun contatto trovato nel database.");
                    } else {
                        System.out.println("\nContatti trovati:");
                        for (Contatto c : contatti) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.println("--- ELIMINAZIONE CONTATTO ---");

                    System.out.print("Inserisci l'ID del contatto da eliminare: ");
                    int idElimina;
                    try {
                        idElimina = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID non valido.");
                        break;
                    }

                    if (service.deleteContatto(idElimina)) {
                        System.out.println("Contatto eliminato correttamente.");
                    } else {
                        System.out.println("Contatto non trovato.");
                    }
                    break;

                case 4:
                    System.out.println("--- AGGIORNAMENTO CONTATTO ---");

                    System.out.print("Inserisci l'ID del contatto da modificare: ");
                    int idModifica;
                    try {
                        idModifica = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("ID non valido.");
                        break;
                    }

                    System.out.println("Quale campo vuoi modificare?");
                    System.out.println("1) Nome");
                    System.out.println("2) Cognome");
                    System.out.println("3) Email");
                    System.out.println("4) Telefono");
                    System.out.println("5) Indirizzo");
                    System.out.println("6) Data di nascita");

                    System.out.print("Scelta: ");
                    int sceltaCampo;
                    try {
                        sceltaCampo = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Scelta non valida.");
                        break;
                    }

                    String campo = null;
                    String valore = null;

                    switch (sceltaCampo) {
                        case 1:
                            campo = "nome";
                            valore = service.ControlloRegex(regexNomeCognome, "Nuovo nome");
                            break;
                        case 2:
                            campo = "cognome";
                            valore = service.ControlloRegex(regexNomeCognome, "Nuovo cognome");
                            break;
                        case 3:
                            campo = "email";
                            valore = service.ControlloRegex(regexEmail, "Nuova email");
                            break;
                        case 4:
                            campo = "telefono";
                            valore = service.ControlloRegex(regexTelefono, "Nuovo telefono");
                            break;
                        case 5:
                            campo = "indirizzo";
                            System.out.print("Nuovo indirizzo: ");
                            valore = scanner.nextLine();
                            break;
                        case 6:
                            campo = "dataNascita";
                            valore = service.ControlloRegex(regexData, "Nuova data di nascita (yyyy-MM-dd)");
                            break;
                        default:
                            System.out.println("Campo non valido.");
                            break;
                    }

                    if (service.updateContatto(idModifica, campo, valore)) {
                        System.out.println("Contatto modificato correttamente.");
                    } else {
                        System.out.println("Contatto non trovato o errore nella modifica.");
                    }

                    break;


                case 5:

                    System.out.println("--- GESTIONE NOTIFICA ---");

                    System.out.println("1) Inserisci notifica");
                    System.out.println("2) Visualizza notifiche");
                    System.out.println("3) Elimina notifica");
                    System.out.println("4) Aggiorna notifica");
                    System.out.println("5) Invia notifiche ");
                    System.out.print("Scegli un'opzione: ");

                    int sceltaNotifica;
                    try {
                        sceltaNotifica = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Opzione non valida.");
                        break;
                    }

                    switch (sceltaNotifica) {
                        case 1:
                            System.out.println("--- INSERIMENTO NOTIFICA ---");
                            System.out.print("Tipo notifica: ");
                            String tipoNotifica = scanner.nextLine();

                            LocalDateTime dataNotifica = LocalDateTime.now();

                            System.out.print("Descrizione: ");
                            String descrizione = scanner.nextLine();

                            System.out.print("Stato [inviata - non inviata]: ");
                            String statoStr = scanner.nextLine();
                            boolean stato = statoStr.equalsIgnoreCase("inviata");

                            System.out.print("ID Contatto associato: ");
                            int idContatto;
                            try {
                                idContatto = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("ID contatto non valido.");
                                break;
                            }

                            if(!DaoContatto.ContattoById(idContatto)) {
                                System.out.println("Nessun contatto trovato con questo ID.");
                                break;
                            }


                            Notifica nuovaNotifica = new Notifica(tipoNotifica, dataNotifica, descrizione, stato, idContatto);
                            if (service.insertNotifica(nuovaNotifica)) {
                                System.out.println("Notifica inserita correttamente.");
                            } else {
                                System.out.println("Errore durante l'inserimento della notifica.");
                            }
                            break;

                        case 2:
                            System.out.println("--- VISUALIZZA NOTIFICHE ---");
                            List<Notifica> notifiche = service.selectNotifica();
                            if (notifiche.isEmpty()) {
                                System.out.println("Nessuna notifica trovata.");
                            } else {
                                for (Notifica n : notifiche) {
                                    System.out.println(n);
                                }
                            }
                            break;

                        case 3:
                            System.out.println("--- ELIMINAZIONE NOTIFICA ---");
                            System.out.print("Inserisci l'ID della notifica da eliminare: ");
                            int idEliminaNotifica;
                            try {
                                idEliminaNotifica = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("ID non valido.");
                                break;
                            }
                            if (service.deleteNotifica(idEliminaNotifica)) {
                                System.out.println("Notifica eliminata correttamente.");
                            } else {
                                System.out.println("Notifica non trovata.");
                            }
                            break;

                        case 4:
                            System.out.println("--- AGGIORNAMENTO NOTIFICA ---");
                            System.out.print("Inserisci l'ID della notifica da modificare: ");
                            int idModificaNotifica;
                            try {
                                idModificaNotifica = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("ID non valido.");
                                break;
                            }

                            System.out.println("Quale campo vuoi modificare?");
                            System.out.println("1) Tipo");
                            System.out.println("2) Data e ora");
                            System.out.println("3) Descrizione");
                            System.out.println("4) ID Contatto");

                            System.out.print("Scelta: ");
                            int sceltaCampoNotifica;
                            try {
                                sceltaCampoNotifica = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Scelta non valida.");
                                break;
                            }

                            String campoNotifica = null;
                            String valoreNotifica = null;

                            switch (sceltaCampoNotifica) {
                                case 1:
                                    campoNotifica = "tipo";
                                    System.out.print("Nuovo tipo: ");
                                    valoreNotifica = scanner.nextLine();
                                    break;
                                case 2:
                                    campoNotifica = "data";
                                    System.out.print("Nuova data e ora (yyyy-MM-ddTHH:mm:ss): ");
                                    valoreNotifica = scanner.nextLine();
                                    // Potresti aggiungere validazione qui
                                    break;
                                case 3:
                                    campoNotifica = "descrizione";
                                    System.out.print("Nuova descrizione: ");
                                    valoreNotifica = scanner.nextLine();
                                    break;
                                case 4:
                                    campoNotifica = "idContatto";
                                    System.out.print("Nuovo ID contatto: ");
                                    valoreNotifica = scanner.nextLine();
                                    break;
                                default:
                                    System.out.println("Campo non valido.");
                                    break;
                            }

                            if (service.updateNotifica(idModificaNotifica, campoNotifica, valoreNotifica)) {
                                System.out.println("Notifica modificata correttamente.");
                            } else {
                                System.out.println("Notifica non trovata o errore nella modifica.");
                            }
                            break;

                        case 5:
                            System.out.println("--- INVIA NOTIFICA ---");
                            System.out.println("Scegli il tipo di notifica da inviare: ");
                            System.out.println("1)sms");
                            System.out.println("2)email");
                            int tipoNotificaDaInviare =Integer.parseInt(scanner.nextLine());

                            switch(tipoNotificaDaInviare){
                                case 1:
                                    ThreadNotifica t1 = new ThreadNotifica("sms");
                                    t1.start();
                                    break;
                                case 2:
                                    ThreadNotifica t2 = new ThreadNotifica("email");
                                    t2.start();
                            }
                            break;

                        default:
                            System.out.println("Scelta non valida.");
                    }
                    break;



                case 6:
                    System.out.println("Uscita dal programma.");
                    exit = false;
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
        scanner.close();
    }
}

