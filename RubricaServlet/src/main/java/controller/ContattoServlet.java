package main.java.controller;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import main.java.entity.Contatto;
import main.java.service.ContattoServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/contatto")
public class ContattoServlet extends HttpServlet {
    ContattoServiceImpl service = new ContattoServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String param = request.getParameter("id");

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

            } else {
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
