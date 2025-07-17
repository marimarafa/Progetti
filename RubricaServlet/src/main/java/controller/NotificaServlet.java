package main.java.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.java.config.ValidazioneCampo;
import main.java.entity.Contatto;
import main.java.entity.Notifica;
import main.java.service.ContattoServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/notifica")
public class NotificaServlet extends HttpServlet {
    ContattoServiceImpl service = new ContattoServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String param = request.getParameter("idContatto");
        String regex =  request.getParameter("regex");
        String campo = request.getParameter("campo");

        try {
            if (param != null) {
                //               SELECT BY ID CONTATTO
                int id = Integer.parseInt(param);
                List<Notifica> notifiche = service.notificaByIdContatto(id);
                if (notifiche != null) {
                    String json = mapper.writeValueAsString(notifiche);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(json);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"errore\": \"Contatto non trovato\"}");
                }

            } else if (regex != null && campo != null) {
            //              SELECT BY CAMPO CON REGEX
                List<String> risultati = service.ControlloRegexNotifica(regex, campo);
                String json = mapper.writeValueAsString(risultati);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(json);

            } else {
                //                     SELECT *
                List<Notifica> notifiche = service.selectNotifica();
                String json = mapper.writeValueAsString(notifiche);
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
            Notifica notifica = mapper.readValue(request.getReader(), Notifica.class);

            boolean insert = service.insertNotifica(notifica);
            response.setContentType("application/json");
            if(insert){
                response.getWriter().write("{\"message\": \"Notifica inserita\"}" + notifica.toString());
            }
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
            Notifica notifica = service.notificaById(id);
            if(notifica != null) {
                boolean update = service.updateNotifica(id,param,valore,response);
                if(update) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"message\": \"Notifica aggiornata\"}" + notifica.toString());
                }else{
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Notifica non trovata\"}");
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
            Notifica notifica = service.notificaById(id);
            if (notifica != null) {
                boolean delete = service.deleteNotifica(id);
                if (delete) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("{\"messaggio\": \"Notifica eliminata\"}" + notifica.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"errore\": \"Notifica non trovata\"}");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }


}
