package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bean.messaggioBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlet.decodeURI.decodeURIComponent;

/**
 * Servlet per la gestione delle risposte alle segnalazioni
 * 
 * @author Thomas
 */
@WebServlet(urlPatterns = {"/risSegnalazione"})
public class risSegnalazione extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Metodo che prende i parametri dalla jsp ed esegue le query di conseguenza
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");
        
        String risposta = decodeURIComponent(request.getParameter("testo"));
        int idMessaggio = Integer.parseInt(request.getParameter("idMessaggio"));
        int idMittente = Integer.parseInt(request.getParameter("idMittente"));
        int idDestinatario = Integer.parseInt(request.getParameter("idDestinatario"));
        int idTransazione = Integer.parseInt(request.getParameter("idTransazione"));
        
        //Controllo che la checkbox per la chiusura del messaggio sia selezionata
        boolean close = false;
        if(request.getParameter("close") != null){
            close = true;
        }
        
        boolean isDone = false;
        if (!close){ //Se l'utente ha deciso la chiusura
            if(userType.equals("amministratore")){                
                isDone = dbLayer.messaggioDAO.risSegnalazione(risposta,(Integer) session.getAttribute("userId"), idMittente, idTransazione, idMessaggio);
                isDone = dbLayer.messaggioDAO.risSegnalazione(risposta,(Integer) session.getAttribute("userId"), idDestinatario, idTransazione, idMessaggio);
            } else { //
                isDone = dbLayer.messaggioDAO.risSegnalazione(risposta,(Integer) session.getAttribute("userId"), idMittente, idTransazione, idMessaggio);
            }
        } else {  //se non ha deciso di chiudere          
            isDone = dbLayer.messaggioDAO.rifSegnalazione(risposta,(Integer) session.getAttribute("userId"), idMittente, idTransazione, idMessaggio);
            isDone = dbLayer.messaggioDAO.rifSegnalazione(risposta,(Integer) session.getAttribute("userId"), idDestinatario, idTransazione, idMessaggio);
        }
        
        //reindirizzo agli alert corretti
        if(isDone==true){
                response.sendRedirect("/mayanShop/alert.jsp?mode=risSegn");        
            } else {
                response.sendRedirect("/mayanShop/alert.jsp?mode=generic");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
