/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.ConnectionProvider;
import bean.negozioBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michela
 */
@WebServlet(name = "controlloNegozi", urlPatterns = {"/controlloNegozi"})
public class controlloNegozi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlloNegozi</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlloNegozi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idNegozio = (String) request.getParameter("idNegozio");
        
        //negozioBean negozio = ricercaNegozio(idNegozio);
        negozioBean negozio = dbLayer.negozioDAO.getNegozio(Integer.parseInt(idNegozio));
        negozio.setLocation(dbLayer.locationDAO.getLocation(negozio.getIdLocation()));
        negozio.setCitta(dbLayer.cittaDAO.getCitta(negozio.getIdCitta()));
        negozio.setFoto(dbLayer.fotoDAO.getFotoNegozio(Integer.parseInt(idNegozio)));
            
        // conversione della lista in formato json
        String json = new Gson().toJson(negozio);

        //aggiunta dell'oggetto alla sessione
        HttpSession session = request.getSession();
        session.setAttribute("negozio", json);

        // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
        response.sendRedirect("/mayanShop/visNegozio.jsp?idNegozio=" + idNegozio);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /*private negozioBean ricercaNegozio(String idNegozio) {
        negozioBean negozio = new negozioBean();
        
        try {
            Connection con = ConnectionProvider.getCon();
            //String query = "select * from Negozio, Location, Citta where Negozio.id_location=Location.id_location and Location.id_citta=Citta.id_citta and id_negozio=" + idNegozio;
            String query = "select * from Negozio where id_negozio=" + idNegozio;
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                negozio.setIdNegozio(rs.getInt("id_negozio"));
                negozio.setNome(rs.getString("nome"));
                negozio.setDescrizione(rs.getString("descrizione"));
                negozio.setTipo(rs.getString("tipo"));
                negozio.setOrari(rs.getString("orari"));
                negozio.setWebLink(rs.getString("web_link"));
                negozio.setValutazioneMedia(rs.getInt("valutazione_media"));
                negozio.setNumWarning(rs.getInt("num_warning"));
                
                if (negozio.getTipo().equals("fisico")) {
                    String idLocation = rs.getString("id_location");
                    
                    String queryLoc = "select * from Location, Citta where Location.id_citta=Citta.id_citta and Location.id_location=" + idLocation;
                    PreparedStatement psLoc = con.prepareStatement(queryLoc);
                    ResultSet rsLoc = psLoc.executeQuery();
                    
                    while (rsLoc.next()) {
                        negozio.setLatitudine(rs.getString("latitudine"));
                        negozio.setLongitudine(rs.getString("longitudine"));
                        negozio.setVia(rs.getString("via"));
                        negozio.setCitta(rs.getString("citta"));
                        negozio.setRegione(rs.getString("regione"));
                        negozio.setStato(rs.getString("stato"));
                    }
                }
                
                try {
                    String queryF = "select id_negozio, Foto.id_foto, link_foto from Link_Negozio_Foto, Foto where Link_Negozio_Foto.id_foto=Foto.id_foto and Link_Negozio_Foto.id_negozio=" + idNegozio;
                    PreparedStatement psFoto = con.prepareStatement(queryF);
                    ResultSet rsFoto = psFoto.executeQuery();
                    while(rsFoto.next()) {
                        //foto.add(rs2.getString("link_foto"));
                        negozio.setFoto(rsFoto.getString("link_foto"));
                    }
                    //negozio.setFoto(foto);
                } catch (Exception e) {
                    log("Errore nella ricerca delle foto del negozio " + idNegozio);
                }
                
            }
        } catch (Exception e) {  
            log("Errore nella ricerca del negozio " + idNegozio);
        }
        return negozio;
    }*/

}
