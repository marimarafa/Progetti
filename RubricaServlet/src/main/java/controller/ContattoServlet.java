package main.java.controller;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import main.java.config.ValidazioneCampo;
import main.java.entity.Contatto;
import main.java.service.ContattoServiceImpl;

import javax.ejb.Local;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/contatto")
public class ContattoServlet extends HttpServlet {

    String regexTelefono = "^([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?\\s[0-9]+$";
    String regexEmail = "^[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?$";
    String regexNomeCognome = "^[A-Z][a-z]+";
    String regexData = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    ContattoServiceImpl service = new ContattoServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String param = request.getParameter("id");
        String regex =  request.getParameter("regex");
        String campo = request.getParameter("campo");

        String data_inizio = request.getParameter("data_inizio");
        String data_fine = request.getParameter("data_fine");


        try {
            if (param != null) {
                //               SELECT BY ID
                int id = Integer.parseInt(param);
                Contatto contatto = service.ContattoById(id);
                if (contatto != null) {
                    String json = mapper.writeValueAsString(contatto);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(json);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"errore\": \"Contatto non trovato\"}");
                }

            } else if (regex != null && campo != null) {
                //              SELECT BY CAMPO CON REGEX
                List<String> risultati = service.ControlloRegexContatto(regex, campo);
                String json = mapper.writeValueAsString(risultati);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(json);

            } else if(data_inizio != null && data_fine != null) {
                //              FILTRA CONTATTO
                Contatto contatto = new Contatto();
                if (request.getParameter("nome") != null) contatto.setNome(request.getParameter("nome"));
                if (request.getParameter("cognome") != null) contatto.setCognome(request.getParameter("cognome"));
                if (request.getParameter("email") != null) contatto.setEmail(request.getParameter("email"));
                if (request.getParameter("telefono") != null) contatto.setTelefono(request.getParameter("telefono"));
                if (request.getParameter("dataNascita") != null) contatto.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));

                List<Contatto> risultati = service.FiltraContatti(contatto, data_inizio,data_fine);
                String json = mapper.writeValueAsString(risultati);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(json);

            }else{
                //                     SELECT *
                List<Contatto> contatti = service.selectContatto();
                String json = mapper.writeValueAsString(contatti);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            Contatto contatto = mapper.readValue(request.getReader(), Contatto.class);
            //        REGEX
            if(!ValidazioneCampo.validaCampo(contatto.getNome(),regexNomeCognome,"nome" , response))return;
            if(!ValidazioneCampo.validaCampo(contatto.getCognome(),regexNomeCognome,"cognome" , response))return;
            if(!ValidazioneCampo.validaCampo(contatto.getTelefono(),regexTelefono,"telefono" , response))return;
            if(!ValidazioneCampo.validaCampo(contatto.getEmail(),regexEmail,"email" , response))return;
            if(!ValidazioneCampo.validaCampo(String.valueOf(contatto.getDataNascita()),regexData,"dataNascita" , response))return;

            service.insertContatto(contatto);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Contatto inserito\"}" + contatto.toString());
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String param = request.getParameter("param");
            String valore = request.getParameter("valore");
            Contatto contatto = service.ContattoById(id);
            if(contatto != null){
                boolean update = service.updateContatto(id,param,valore);
                if(update) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"message\": \"Contatto aggiornato\"}" + contatto.toString());
                }else{
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Contatto non trovato\"}");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Contatto contatto = service.ContattoById(id);
            if (contatto != null) {
                boolean delete = service.deleteContatto(id);
                if (delete) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"messaggio\": \"Contatto eliminato\"}" + contatto.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"errore\": \"Contatto non trovato\"}");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
