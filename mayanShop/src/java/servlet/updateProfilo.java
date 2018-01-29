package servlet;

import bean.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che gestisce l'aggiornamento delle informazioni del profilo
 *
 * @author Marcello
 */
@WebServlet(name = "updateProfilo", urlPatterns = {"/updateProfilo"})
public class updateProfilo extends HttpServlet {

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
        HttpSession sessione = request.getSession();
        User utente = new User(sessione);
        String nuovoNome = request.getParameter("nome");
        String nuovoCognome = request.getParameter("cognome");
        String nuovaEmail = request.getParameter("email");
        //se i parametri sono uguali
        if ((utente.getNome().equals(nuovoNome))
                && (utente.getCognome().equals(nuovoCognome))
                && (utente.getEmail().equals(nuovaEmail))) {
            //non ho modificato dati nel form
            response.sendRedirect("./profilo.jsp?err=u1");
        } else {
            //eseguo l'update
            utente.setNome(nuovoNome);
            utente.setCognome(nuovoCognome);
            utente.setEmail(nuovaEmail);
            boolean result = dbLayer.userDAO.updateInfo(utente);
            if (result) {
                //aggiorno la sessione e do conferma di avvenuto update
                utente.setSession(sessione);
                response.sendRedirect("./alert.jsp?mode=updinf");
            } else {
                //qualcosa non è andato
                response.sendRedirect("./profilo.jsp?err=u2");
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
