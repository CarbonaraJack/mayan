package servlet;

import bean.User;
import bean.itemBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che gestisce la stampa della lista di item da modificare nel lato
 * aziendale
 *
 * @author Marcello
 */
@WebServlet(name = "editItemList", urlPatterns = {"/editItemList"})
public class editItemList extends HttpServlet {

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
        HttpSession sessione = request.getSession();
        User utente = new User(sessione);
        sessione.setAttribute("modeEditList", "get");
        if (utente.getTipo().equals("venditore") || utente.getTipo().equals("amministratore")) {
            String pagina = request.getParameter("page");
            int page = 1;
            if (pagina != null) {
                //ho cambiato pagina
                page = Integer.parseInt(pagina);
            }
            sessione.setAttribute("paginaEditList", page);
            ArrayList<itemBean> oggetti
                    = dbLayer.itemDAO.getLightItemsOffset(page);
            //converto la lista di items in JSON
            String json = new Gson().toJson(oggetti);
            sessione.setAttribute("listaItemsEditList", json);
            //mi prendo il numero di items per gestire il numero di pagine
            int numeroItems = dbLayer.itemDAO.getNumItems();
            if (numeroItems == -1) {
                //errore in sql
                response.sendRedirect("./alert.jsp?mode=generic");
            } else {
                int numeroPagine = numeroItems / 15;
                if (numeroItems % 15 != 0) {
                    numeroPagine++;
                }
                sessione.setAttribute("numeroPagineEditList", numeroPagine);
                response.sendRedirect("./modificaItemLista.jsp");
            }
        } else {
            //l'utente non può visualizzare questa pagina
            response.sendRedirect("./alert.jsp?mode=restricted");
        }
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
        HttpSession sessione = request.getSession();
        User utente = new User(sessione);
        sessione.setAttribute("modeEditList", "post");
        if (utente.getTipo().equals("venditore") || utente.getTipo().equals("amministratore")) {
            String ricerca = request.getParameter("nomeItem");
            ArrayList<itemBean> oggetti
                    = dbLayer.itemDAO.getLightItemsRicerca(ricerca);
            //converto la lista di items in JSON
            String json = new Gson().toJson(oggetti);
            sessione.setAttribute("listaItemsEditList", json);
            sessione.setAttribute("ricercaEditList", ricerca);
            response.sendRedirect("./modificaItemLista.jsp");
        } else {
            //l'utente non può visualizzare questa pagina
            response.sendRedirect("./alert.jsp?mode=restricted");
        }
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
