package servlet;

import bean.User;
import bean.negozioBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Funzione che modifica la quantità e il prezzo di un item per ogni negozio di
 * un utente
 *
 * @author Marcello
 */
@WebServlet(name = "modificaStock", urlPatterns = {"/modificaStock"})
public class modificaStock extends HttpServlet {

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
        //prendo l'utente dalla sessione
        User utente = new User(request.getSession());
        //prendo l'id dell'item da modificare
        int idItem = Integer.parseInt(request.getParameter("idItem"));
        //per ogni negozio che appartiene a quell'utente aggiorno gli stocks
        boolean flag = true;
        for (negozioBean negozio : dbLayer.negozioDAO.getNegoziByAdmin(utente)) {
            //mi prendo i parametri di quel negozio
            boolean popolato = (Integer.parseInt(
                    request.getParameter("idItem" + negozio.getIdNegozio()))) != -1;
            double prezzo = (Double.parseDouble(
                    request.getParameter("prezzo" + negozio.getIdNegozio())));
            int stock = (Integer.parseInt(
                    request.getParameter("stock" + negozio.getIdNegozio())));
            /*
            System.out.println("Negozio: "+negozio.getIdNegozio());
            System.out.println("Popolato: "+popolato);
            System.out.println("Prezzo: "+prezzo);
            System.out.println("Stock: "+stock);
             */
            flag = flag
                    && dbLayer.itemNegozioDAO.aggiornaStocks(
                    idItem, negozio.getIdNegozio(), popolato, prezzo, stock);
        }
        if(flag){
            //Se sono riuscito ad aggiornare gli stocks allora aggiorno il
            //prezzo minimo. Non mi interessa controllarlo per la gestione
            //degli errori
            dbLayer.itemDAO.fixPrezzoMinimo(idItem);
            //Ho finito la procedura, posso reindirizzare l'utente
            response.sendRedirect("./alert.jsp?mode=stock&id="+idItem);
        }else{
            //qualcosa è andato storto            
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
