/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.messaggioBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet per la gestione della lista di notifiche: la servlet aggiunge alla 
 * sessione la lista delle notifiche in json, che vengono gestite dalla jsp 
 * 
 * @author Thomas
 */
@WebServlet(name = "showNotifiche", urlPatterns = {"/showNotifiche"})
public class showNotifiche extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Il metodo aggiunge la lista alla sessione in json
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        int id = (Integer) session.getAttribute("userId");
        
        boolean isAdmin = false;
        if(session.getAttribute("userType").equals("amministratore")){
            isAdmin = true;
        }
        
        ArrayList<messaggioBean> messaggi = dbLayer.messaggioDAO.getMessaggeList(isAdmin, id);
        
        //converto la lista in formato json
        String json = new Gson().toJson(messaggi);
        
        //aggiungo il json alla sessione
        session.setAttribute("listaMessaggi", json);
        
        //reindirizzo su una pagina in cui vengono visualizzati i risultati
        response.sendRedirect("/mayanShop/notifiche.jsp");
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
