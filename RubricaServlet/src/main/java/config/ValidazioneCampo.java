package main.java.config;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ValidazioneCampo {

    public static boolean validaCampo(String valore, String regex, String nomeCampo, HttpServletResponse response) throws IOException {
        List<String> campi = Arrays.asList("nome", "cognome", "indirizzo", "telefono", "email", "dataNascita");
        if(!campi.contains(nomeCampo)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"errore\": \"Campo '" + nomeCampo + "' non previsto per la validazione\"}");
            return false;
        }else{
            if (valore == null || !Pattern.matches(regex, valore)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"errore\": \"Il valore " + valore + " non valido\"}");
                return false;
            }
            return true;
        }

    }


}


