/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dbLayer.NotificationChecker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "showMessage", urlPatterns = {"/showMessage"})
public class showMessage extends HttpServlet {
    


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
        String id = (String) request.getParameter("id");
        ResultSet rs;
        String s="";

        NotificationChecker db = new NotificationChecker(id);
        rs = db.getMessage();
        ArrayList<String> mittente = new ArrayList();
        ArrayList<String> destinatario = new ArrayList();
        
        try {
            
            mittente = db.findUserInf(rs.getString("id_mittente"));
            destinatario = db.findUserInf(rs.getString("id_destinatario"));
            
            try (PrintWriter out = response.getWriter()) {
            
                if(rs.next()){ 
                    s += "<!DOCTYPE html>\n";
                    s += "<html>\n";
                    s += "<head>\n";
                    s += "<title>MayanShop</title>\n";
                    s += "</head>\n";
                    s += "<body>\n";
                    s += "<p>Tipo di messaggio: " + rs.getString("tipo") + "</p>\n";
                    s += "<br>\n";
                    s += "<p>Mittente: " + mittente.get(0) + " (" + mittente.get(1) + ")</p>\n";
                    s += "<br>\n";
                    s += "<p>Destinatario: " + destinatario.get(0) + " (" + destinatario.get(1) + ")</p>\n";
                    s += "<br>\n";
                    s += "<p>Transizione: " + rs.getString("id_transizione") + "</p>\n";
                    s += "<br>\n";
                    s += "<p>Testo: " + rs.getString("descrizione") + "</p>\n";
                    s += "<br>\n";
                    s += "<form action=\"/uploadMessage\" method=\"POST\">\n";
                    s += "Risposta: <input type=\"text\" name=\"risposta\" id=\"risposta\"><br>\n";
                    s += "<input type=\"submit\" value=\"Rispondi\">\n";
                    s += "</form>\n";
                    s += "</body>\n";
                    s += "</html>\n";
                    s += "\n";      
            
                }else{
                    s += "<!DOCTYPE html>\n";
                    s += "<html>\n";
                    s += "<head>\n";
                    s += "<title>MayanShop</title>\n";
                    s += "</head>\n";
                    s += "<body>\n";
                    s += "<h1>Errore visualizzazione messaggio</h1>\n";
                    s += "</body>\n";
                    s += "</html>\n";
                }
                
                out.println(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(showMessage.class.getName()).log(Level.SEVERE, null, ex);
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
