package servlet;

import bean.acquistoBean;
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
 * Servlet per l'aggiunta della lista di messaggi e una lista di interi per la gestione
 * dell'invio delle segnalazioni
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

    /**
     * Gestisce il metodo <code>GET</code> della servlet.
     * Aggiunge la lista degli acquisti ed un array per il controllo dll'invio
     * delle segnalazioni
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
        ArrayList<acquistoBean> acquisti = new ArrayList<>();
        ArrayList<Integer> checkSegnalazioniList= new ArrayList<>();
        //String oggettoSingolo = (String) request.getParameter("objS");
        //String idOggetto = (String) request.getParameter("idOgg");

        acquisti = dbLayer.acquistoDAO.getListaAcquisti(userId); //ottengo una lista con gli id delle transazioni relativo all'utente
        
        //Creo la lista contenente i valori per gestire l'invio della segnalazione
        for(acquistoBean i : acquisti){
            System.out.println("Id Transazione: "+i.getIdTransazione());
            System.out.println("User Id: "+(Integer) session.getAttribute("userId"));
            checkSegnalazioniList.add(dbLayer.messaggioDAO.checkSegnalazione(i.getIdTransazione(),(Integer) session.getAttribute("userId")));
        }
        
        // conversione della lista in formato json
        String json = new Gson().toJson(acquisti);
        String jsoncheck = new Gson().toJson(checkSegnalazioniList);

        //request.setAttribute("listaItems", json);
        //aggiunta della lista alla sessione
        session.setAttribute("listaStorico", json);
        session.setAttribute("listaCheckSegnalazione", jsoncheck);

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
