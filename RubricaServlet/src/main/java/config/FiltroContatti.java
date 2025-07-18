package main.java.config;


import main.java.dao.DaoContatto;
import main.java.entity.Contatto;
import org.w3c.dom.css.Counter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@WebFilter("/*")
public class FiltroContatti implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        boolean contattoEsiste = false;
        String metodo = httpRequest.getMethod();

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");

        if(nome != null && cognome != null){
            String query = "SELECT * FROM contatto WHERE nome = ? AND cognome = ?";

            try (Connection conn = ConnessioneDB.getConnection();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, nome);
                ps.setString(2, cognome);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    contattoEsiste = true;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else{
            httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            httpResponse.getWriter().write("Parametri passati non validi");
        }

        if (contattoEsiste || metodo.equalsIgnoreCase("POST")) {
            filterChain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("\nContatto non trovato nel db.\n");
        }
    }

    @Override
    public void destroy() {
    }
}
