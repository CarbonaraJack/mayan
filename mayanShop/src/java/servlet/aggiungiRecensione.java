package servlet;

import bean.User;
import bean.recensioneBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlet.decodeURI.decodeURIComponent;

/**
 * Servlet che gestisce l'inserimento di nuove recensioni
 *
 * @author Marcello
 */
@WebServlet(name = "aggiungiRecensione", urlPatterns = {"/aggiungiRecensione"})
public class aggiungiRecensione extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("idForm"));
        String mode = request.getParameter("modeForm");
        String testo = decodeURIComponent(request.getParameter("recensione"));
        double rating = Double.parseDouble(request.getParameter("rating"));
        recensioneBean recensione = new recensioneBean(testo, rating, utente.getIdUser());
        if(dbLayer.recensioneDAO.proceduraInserimento(recensione, mode, id)){
            //inserimento eseguito con successo
                response.sendRedirect("./alert.jsp?mode=recensione");
        }else{
            //c'è stato un problema
                response.sendRedirect("./alert.jsp?mode=generic");
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
