package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.itemBean;
import com.google.gson.Gson;
import javax.servlet.http.HttpSession;

/**
 * servlet che si occupa della visualizzazione di un oggetto singolo
 *
 * @author Michela
 */
@WebServlet(name = "controlloItems", urlPatterns = {"/controlloItems"})
public class controlloItems extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idOggetto = (String) request.getParameter("idOgg");

        itemBean oggetto = dbLayer.itemDAO.getItem(Integer.parseInt(idOggetto));
        oggetto.setFoto(dbLayer.fotoDAO.getFotoItem(Integer.parseInt(idOggetto)));
        oggetto.setNegozi(dbLayer.itemNegozioDAO.getNegoziByItem(Integer.parseInt(idOggetto)));
        oggetto.setRecensioni(dbLayer.recensioneDAO.getRecenzioneByItem(Integer.parseInt(idOggetto)));
        dbLayer.itemDAO.updateVisualizzazioni(Integer.parseInt(idOggetto), oggetto.getNumVisualizzazioni() + 1);

        // conversione della lista in formato json
        String json = new Gson().toJson(oggetto);

        //aggiunta dell'oggetto alla sessione
        HttpSession session = request.getSession();
        session.setAttribute("item", json);

        // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
        response.sendRedirect("/mayanShop/visOggetto.jsp?item=" + idOggetto);
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
