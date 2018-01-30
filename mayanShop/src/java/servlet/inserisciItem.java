package servlet;

import bean.userBean;
import bean.itemBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlet.decodeURI.decodeURIComponent;

/**
 * Servlet che gestisce l'inserimento e la modifica di un item
 *
 * @author Marcello
 */
@WebServlet(name = "inserisciItem", urlPatterns = {"/inserisciItem"})
public class inserisciItem extends HttpServlet {

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
        userBean utente = new userBean(sessione);
        if (utente.getTipo().equals("venditore") || utente.getTipo().equals("amministratore")) {
            String mode = request.getParameter("mode");
            System.out.println("mode: " + mode);
            String nome = decodeURIComponent(request.getParameter("nome"));
            String descrizione = decodeURIComponent(request.getParameter("descrizione"));
            String categoria = request.getParameter("categoria");
            String produttore = request.getParameter("produttore");
            if (categoria.equals("nuova")) {
                categoria = decodeURIComponent(request.getParameter("nuovaCategoria"));
            }
            if (produttore.equals("nuovo")) {
                produttore = decodeURIComponent(request.getParameter("nuovoProduttore"));
            }
            itemBean oggetto = new itemBean(nome, produttore, descrizione, categoria);
            /*
            System.out.println("nome: "+nome);
            System.out.println("categoria: "+categoria);
            System.out.println("produttore: "+produttore);
            System.out.println("descrizone: "+descrizione);
             */
            //ho tutti i parametri, procedo all'inserimento/update
            if (mode.equals("new")) {
                //inserisco il nuovo item
                if (dbLayer.itemDAO.insertItem(oggetto)) {
                    //l'oggetto è stato inserito con successo
                    int nuovoId = dbLayer.itemDAO.getIdItem(oggetto);
                    if (nuovoId != -1) {
                        //ho inserito l'oggetto correttamente. redirigo alla
                        //servlet per rimodificarlo ulteriormente
                        response.sendRedirect("./alert.jsp?mode=newitem&id=" + nuovoId);
                    } else {
                        //non ho trovato il nuovo oggetto. Forse qualcuno l'ha
                        //modificato
                        response.sendRedirect("./alert.jsp?mode=generic");
                    }
                } else {
                    //qualcosa è andato storto
                    response.sendRedirect("./alert.jsp?mode=generic");
                }
            } else {
                //modifico l'item
                int idItem = Integer.parseInt(request.getParameter("idItem"));
                oggetto.setIdItem(idItem);
                //ora che ho l'oggetto completo lo aggiorno
                if (dbLayer.itemDAO.updateItem(oggetto)) {
                    //ho aggiornato l'oggetto correttamente. redirigo alla
                    //servlet per rimodificarlo ulteriormente
                    response.sendRedirect("./alert.jsp?mode=upditem&id=" + oggetto.getIdItem());

                } else {
                    //qualcosa è andato storto
                    response.sendRedirect("./alert.jsp?mode=generic");

                }
            }
        } else {
            //l'utente non possiede i permessi necessari per modificare un item
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
