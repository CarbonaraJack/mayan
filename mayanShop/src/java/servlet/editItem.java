package servlet;

import bean.fotoBean;
import bean.userBean;
import bean.itemBean;
import bean.itemNegozioBean;
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
 *
 * @author jack
 */
@WebServlet(name = "editItem", urlPatterns = {"/editItem"})
public class editItem extends HttpServlet {

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
        //prendo tutti i parametri passati dal jsp
        userBean utente = new userBean(sessione);
        String mode = request.getParameter("mode");
        int idItem = -1;
        String idItemString = request.getParameter("item");
        if (idItemString != null) {
            //se l'oggetto è nullo non posso fare il parse,
            //quindi lascio l'id a -1
            idItem = Integer.parseInt(idItemString);
        }
        //scremo gl utenti normali
        if (utente.getTipo().equals("venditore") || utente.getTipo().equals("amministratore")) {
            //interrogo il database per i dati necessari
            ArrayList<String> categorie = dbLayer.itemDAO.getCategorie();
            ArrayList<String> produttori = dbLayer.itemDAO.getProduttori();
            itemBean oggetto = null;
            ArrayList<fotoBean> listaFoto = null;
            if (idItemString != null) {
                //se devo creare un nuovo oggetto non devo cercare le sue info
                //o le sue foto
                oggetto = dbLayer.itemDAO.getItem(idItem);
                listaFoto = dbLayer.fotoDAO.getFotoItem(idItem);
            }
            ArrayList<itemNegozioBean> listaNegozi
                    = dbLayer.itemNegozioDAO.getNegoziStocks(utente, oggetto);
            //Compatto i parametri in un solo oggetto
            ArrayList<Object> parametri = new ArrayList<>();
            parametri.add(categorie);
            parametri.add(produttori);
            parametri.add(oggetto);
            parametri.add(listaNegozi);
            parametri.add(listaFoto);
            //converto i dati in json
            String json = new Gson().toJson(parametri);
            //inserisco i dati nella sessione
            sessione.setAttribute("par_EditItem", json);
            sessione.setAttribute("mode_EditItem", mode);
            //procedo al jsp
            response.sendRedirect("./modificaItem.jsp");
        } else {
            //l'utente non può visualizzare questa pagina
            response.sendRedirect("./alert.jsp?mode=restricted");
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
