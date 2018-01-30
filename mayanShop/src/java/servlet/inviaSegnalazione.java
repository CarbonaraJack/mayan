package servlet;

import bean.userBean;
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
 * Servlet che gestisce l'invio delle segnalazioni ad un acquisto da parte degli 
 * utenti.
 * Dopo l'esecuzione della query, l'utente viene reindirizzato ad una jsp per la gestione
 * degli alert
 * 
 * 
 * @author Thomas
 */
@WebServlet(name = "inviaSegnalazione", urlPatterns = {"/inviaSegnalazione"})
public class inviaSegnalazione extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

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
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Gestisce l'invio della segnalazione
     * 
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        userBean utente = new userBean(session);
        String text = decodeURIComponent(request.getParameter("testo"));
        String idNegozio = (String) request.getParameter("idNegozio");
        String idVenditore = dbLayer.messaggioDAO.getVenditore(idNegozio);
        int userId = utente.getIdUser();
        int idVen = Integer.parseInt(idVenditore);
        int idT = Integer.parseInt(request.getParameter("idTransazione"));

        //Eseguo l'inserimento della segnalazione
        boolean isDone = dbLayer.messaggioDAO.insertSegnalazione(text, idVen, userId, idT);

        PrintWriter out = response.getWriter();

        if (isDone) {
            //segnalazione eseguita con successo
            response.sendRedirect("./alert.jsp?mode=segnalazione");

        } else {
            //qualcosa è andato storto
            response.sendRedirect("./alert.jsp?mode=generic");
        }
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
