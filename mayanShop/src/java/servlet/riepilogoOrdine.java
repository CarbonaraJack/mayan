/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.carrelloBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet che si occupa 
 * @author Michela
 */
@WebServlet(name = "riepilogoOrdine", urlPatterns = {"/riepilogoOrdine"})
public class riepilogoOrdine extends HttpServlet {

    /**
     * Processes requests for HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String carrello = (String) session.getAttribute("carrello");

        Gson gson = new Gson();
        TypeToken<ArrayList<carrelloBean>> listaCarrelloType = new TypeToken<ArrayList<carrelloBean>>() {};
        ArrayList<carrelloBean> listaCarrello = gson.fromJson(carrello, listaCarrelloType.getType());
        Iterator<carrelloBean> it = listaCarrello.iterator();
        
        while (it.hasNext()) {
            carrelloBean oggCorrente = it.next();
            String quantita = request.getParameter("quantita"+String.valueOf(oggCorrente.getIdItem())+String.valueOf(oggCorrente.getIdVenditore()));
            if(quantita!=null){
                oggCorrente.setQuantita(Integer.parseInt(quantita));
            }
        }
        String jsonList = new Gson().toJson(listaCarrello);
        session.setAttribute("carrello", jsonList);
        response.sendRedirect("/mayanShop/riepilogoOrdine.jsp");
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
        //processRequest(request, response);
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
