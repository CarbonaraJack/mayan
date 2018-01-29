package servlet;

import bean.Foto;
import bean.User;
import bean.negozioBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che cancella le foto dal database
 *
 * @author Marcello
 */
@WebServlet(name = "cancellaFoto", urlPatterns = {"/cancellaFoto"})
public class cancellaFoto extends HttpServlet {

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
        User utente = new User(request.getSession());
        if (request.getParameter("mode").equals("negozio")) {
            //ho chiamato il servlet dalla pagina di modifica negozi
            Foto foto = dbLayer.fotoDAO.getFoto(
                    request.getParameter("idFoto"));
            //Controllo che l'utente abbia il permesso di cancellare la foto
            if (dbLayer.fotoNegozioDAO.isOwnerFoto(utente, foto)) {
                //posso cancellare la foto
                //mi prendo la lista di negozi da unlinkare
                ArrayList<negozioBean> listaNegozi = new ArrayList();
                listaNegozi = dbLayer.fotoNegozioDAO.getNegozi(foto);
                //inizializzo un boolean a true, se rimane true è andato tutto a
                //buon fine
                boolean res = true;
                for (negozioBean negozio : listaNegozi) {
                    res = res
                            & dbLayer.fotoNegozioDAO.unlinkFotoNegozio(foto, negozio);
                }
                res = res
                        & dbLayer.fotoDAO.deleteFoto(foto);
                if (res) {
                    //tutto è andato a buon fine
                    response.sendRedirect("./alert.jsp?mode=deleteFoto");
                } else {
                    //c'è stato qualche problema
                    response.sendRedirect("./alert.jsp?mode=deleteFoto&err=f2");
                }
            } else {
                //l'utente non è il proprietario, non faccio nulla
                response.sendRedirect("./alert.jsp?mode=deleteFoto&err=f1");
            }
        } else if (request.getParameter("mode").equals("item")) {
            //Ho chiamato il servlet dalla pagina di modifica item
            int idItem = Integer.parseInt(request.getParameter("idItem"));
            Foto foto = dbLayer.fotoDAO.getFoto(request.getParameter("idFoto"));
            if (dbLayer.fotoItemDAO.deleteFotoItem(foto, idItem)) {
                //foto cancellata con successo
                response.sendRedirect("./alert.jsp?mode=deleteFoto&id=" + idItem);
            } else {
                //qualcosa è andato storto
                response.sendRedirect("./alert.jsp?mode=generic");
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
