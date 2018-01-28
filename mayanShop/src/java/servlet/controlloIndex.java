/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.itemBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet che si occupa della ricerca degli oggetti da visualizzare nell'index
 * @author Michela
 */
@WebServlet(name = "controlloIndex", urlPatterns = {"/index"})
public class controlloIndex extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code> method.
     * quando viene chiamata la pagina di index con metodo get, vengono cercati i 10 item più visti e i 10 item più acquistati
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<itemBean> listaVis = dbLayer.itemDAO.getItemsIndex("tot_visualizzazioni", "10");
        ArrayList<itemBean> listaAcq = dbLayer.itemDAO.getItemsIndex("tot_acquistato", "10");
        
        // conversione della lista in formato json
        String jsonVis = new Gson().toJson(listaVis);
        String jsonAcq = new Gson().toJson(listaAcq);

        //aggiunta della lista alla sessione
        HttpSession session = request.getSession();
        session.setAttribute("listaVis", jsonVis);
        session.setAttribute("listaAcq", jsonAcq);

        // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
        response.sendRedirect("/mayanShop/index.jsp");
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

}
