package servlet;

import bean.Acquisto;
import com.google.gson.Gson;
import dbLayer.DBAcquisti;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "controlloAcquisti", urlPatterns = {"/controlloAcquisti"})
public class controlloAcquisti extends HttpServlet {

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

        HttpSession session = request.getSession();
        String userId = "" + session.getAttribute("userId");
        ArrayList<Acquisto> acquisti = new ArrayList<>();
        //String oggettoSingolo = (String) request.getParameter("objS");
        //String idOggetto = (String) request.getParameter("idOgg");

        DBAcquisti db = new DBAcquisti();
        acquisti = db.getListaAcquisti(userId); //ottengo una lista con gli id delle transazioni relativo all'utente

        // conversione della lista in formato json
        String json = new Gson().toJson(acquisti);

        //request.setAttribute("listaItems", json);
        //aggiunta della lista alla sessione
        session.setAttribute("listaStorico", json);

        // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
        //RequestDispatcher rd = request.getRequestDispatcher("/visLista.jsp");
        //rd.forward(request, response);
        response.sendRedirect("/mayanShop/storicoAcquisti.jsp");

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
