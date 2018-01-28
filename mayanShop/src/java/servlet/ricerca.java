/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.itemBean;
import bean.negozioBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet che si occupa della ricerca di una lista di oggetti da mostrare come risultato della ricerca
 * @author Michela
 */
@WebServlet(name = "ricerca", urlPatterns = {"/ricerca"})
public class ricerca extends HttpServlet {

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
        String query = request.getParameter("item");
        String newS = aggiustaStringa(query);
        String select = request.getParameter("select");
        //otteniamo la sessione per poter impostare parametri di sessione
        HttpSession session = request.getSession();
        ArrayList<itemBean> lista = new ArrayList<>();
        
        if(select.equals("oggetti")){
            lista = dbLayer.itemDAO.getItemsRicerca(newS);
            String json = new Gson().toJson(lista);
            session.setAttribute("listaItems", json);
        } else if(select.equals("produttori")){
            lista = dbLayer.itemDAO.getItemsRicercaProduttori(newS);
            String json = new Gson().toJson(lista);
            session.setAttribute("listaItems", json);
        } else if(select.equals("zone")){
            log("sono nella ricerca");
            lista = dbLayer.itemDAO.getItemsRicercaZone(newS);
            for (Iterator<itemBean> iterator = lista.iterator(); iterator.hasNext();) {
                itemBean next = iterator.next();
                next.setRegioni(dbLayer.cittaDAO.getRegioniByItem(next.getIdItem()));
                next.setNegozi(dbLayer.negozioDAO.getNegoziByItemRicerca(next.getIdItem()));
            }
            String json = new Gson().toJson(lista);
            session.setAttribute("listaItems", json);
        } else if(select.equals("negozi")){
            ArrayList<negozioBean> listaN = dbLayer.negozioDAO.getNegoziRicerca(newS);
            String json = new Gson().toJson(listaN);
            session.setAttribute("listaItems", json);
        }
        
        // conversione della tipologia di ricerca in formato json
        String jsonSelect = new Gson().toJson(select);
        //aggiunta della tipologia di ricerca alla sessione
        session.setAttribute("selectRicerca", jsonSelect);

        response.sendRedirect("/mayanShop/visLista.jsp");
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
