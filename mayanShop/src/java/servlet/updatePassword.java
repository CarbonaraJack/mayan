/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce il reset password
 *
 * @author Marcello
 */
@WebServlet(name = "updatePassword", urlPatterns = {"/updatePassword"})
public class updatePassword extends HttpServlet {

    /**
     * Gestore dell porta /updatePassword
     *
     * @param request l'username e la nuova password
     * @param response l'ok dopo che la password è stata resettata
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String place = request.getParameter("place");
        boolean success = false;
        //controllo se l'utente esiste
        if (place.equals("forgot")) {
            String email = request.getParameter("email");
            if (dbLayer.userDAO.isAvailable(email)) {
                //se l'utente non esiste torno indietro e avviso
                response.sendRedirect("./login.jsp?mode=forgot&err=f1");
            } else {
                //l'utente esiste, aggiorno la password
                User utente = dbLayer.userDAO.getUser(email);
                success = dbLayer.userDAO.updatePassword(utente, password);
                if (success) {
                    response.sendRedirect("./alert.jsp?mode=reset");
                } else {
                    response.sendRedirect("./alert.jsp?mode=reset&err=r1");
                }
            }
        }else if(place.equals("reset")){
            User utente= new User(request.getSession());
            success = dbLayer.userDAO.updatePassword(utente, password);
            if (success){
                response.sendRedirect("./alert.jsp?mode=updpwd");
            }else{
                response.sendRedirect("./profilo.jsp?err=p1");
            }
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
