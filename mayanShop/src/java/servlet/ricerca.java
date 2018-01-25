/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.itemBean;
import com.google.gson.Gson;
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
 *
 * @author Michela
 */
@WebServlet(name = "ricerca", urlPatterns = {"/ricerca"})
public class ricerca extends HttpServlet {

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
            out.println("<title>Servlet ricerca</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ricerca at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        String query = request.getParameter("item");
        String newS = aggiustaStringa(query);
        String select = request.getParameter("select");
        ArrayList<itemBean> lista = new ArrayList<>();
        
        if(select.equals("oggetti")){
            lista = dbLayer.itemDAO.getItemsRicerca(newS);
        } else if(select.equals("produttori")){
            lista = dbLayer.itemDAO.getItemsRicercaProduttori(newS);
        } else if(select.equals("negozi")){
            
        }
         
        
        // conversione della lista in formato json
        String json = new Gson().toJson(lista);
        String jsonSelect = new Gson().toJson(select);

        //aggiunta della lista alla sessione
        HttpSession session = request.getSession();
        session.setAttribute("listaItems", json);
        session.setAttribute("selectRicerca", jsonSelect);

        response.sendRedirect("/mayanShop/visLista.jsp");
    }
    
    private String aggiustaStringa(String query){
        String newString = "";
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if(c=='\''){
                newString = newString + "\\'";
            } else if (c=='"'){
                newString = newString + "\"";
            } else {
                newString = newString + c;
            }
        }
        return newString;
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
