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
     * Handles the HTTP <code>GET</code> method. ritorna le informazioni del
     * negozio da visualizzare e reindirizza alla pagina visNegozio.jsp
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
           
        negozioBean negozio = dbLayer.negozioDAO.getNegozio(Integer.parseInt(idNegozio));
        negozio.setItems(dbLayer.itemDAO.getItemsForNegozi(negozio.getIdNegozio())); 

        if (negozio.getIdLocation() != -1) {
            //UN NEGOZIO ONLINE PUO' NON AVERE UNA LOCATION
            negozio.setLocation(dbLayer.locationDAO.getLocation(negozio.getIdLocation()));
            negozio.setCitta(dbLayer.cittaDAO.getCitta(negozio.getIdCitta()));
        }
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
}
