package servlet;

import bean.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che si occupa dei login
 *
 * @author Marcello
 */
public class login extends HttpServlet {

    /**
     * Request processor per le richieste di login sulla porta /checkLogin
     *
     * @param request in email ho la mail, in password ho la password
     * @param response torno al login se qualcosa non va, altrimenti loggo e
     * vado alla home
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (dbLayer.userDAO.isAvailable(email)) {
            //l'utente non esiste nel database
            response.sendRedirect("./login.jsp?mode=login&err=l1");
        } else {
            User utente = dbLayer.userDAO.getUser(email);
            //System.out.println("id: "+utente.getIdUser()+" name: " 
            //        +utente.getNome()+" cognome: "+utente.getCognome()+" email: "+utente.getEmail());
            if (dbLayer.userDAO.isPasswordCorrect(utente, password)) {
                //esegui il login
                //aggiungo le informazioni utente alla sessione
                HttpSession sessione = request.getSession();
                utente.setSession(sessione);
                response.sendRedirect("./alert.jsp?mode=login");
            } else {
                response.sendRedirect("./login.jsp?mode=login&err=l1");
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
