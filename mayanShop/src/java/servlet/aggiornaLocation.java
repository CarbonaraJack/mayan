package servlet;

import bean.User;
import bean.cittaBean;
import bean.locationBean;
import bean.negozioBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che permette di aggiornare/inserire la location di un negozio
 *
 * @author Marcello
 */
@WebServlet(name = "aggiornaLocation", urlPatterns = {"/aggiornaLocation"})
public class aggiornaLocation extends HttpServlet {

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
        //mi sistemo tutti i parametri
        User utente = new User(request.getSession());
        int idNegozio = Integer.parseInt(request.getParameter("idNegozio"));
        String locationJson = decodeURI.decodeURIComponent(
                request.getParameter("locationJson")
        );
        String cittaJson = decodeURI.decodeURIComponent(
                request.getParameter("cittaJson")
        );
        locationBean location = new locationBean(locationJson);
        negozioBean negozio = dbLayer.negozioDAO.getNegozio(idNegozio);
        cittaBean citta = new cittaBean(cittaJson);

        //controllo se l'utente ha il permesso di modificare questo negozio
        if (dbLayer.negozioDAO.isMyShop(utente, negozio)) {
            //procedo
            //estrapolo l'id della città. Se non esiste viene inserita
            //automaticamente
            int idCitta = dbLayer.cittaDAO.findIdCitta(citta);
            if (idCitta != -1) {
                //ho trovato la città
                location.setIdCitta(idCitta);
                //controllo se il negozio ha già una location
                if (negozio.getIdLocation() == -1) {
                    //Il negozio non ha ancora la location, quindi la inserisco
                    int idLocation = dbLayer.locationDAO.findIdLocation(location);
                    if (idLocation != -1) {
                        //città inserita con successo
                        negozio.setIdLocation(idLocation);
                        if(dbLayer.negozioDAO.updateLocation(negozio)){
                            //Inserimento eseguito con successo
                            response.sendRedirect("./alert.jsp?mode=aggiornaLoc");
                        }else {
                        //c'è stato un problema in sql nell'aggiornamento della loc
                        response.sendRedirect("./alert.jsp?mode=generic");
                            
                        }

                    } else {
                        //c'è stato un problema in sql nell'inserimento loc
                        response.sendRedirect("./alert.jsp?mode=generic");
                    }

                } else {
                    //Il negozio ha già la location, quindi la aggiorno
                    location.setIdLocation(negozio.getIdLocation());
                    if (dbLayer.locationDAO.updateLocation(location)) {
                        //Aggiornamento eseguito con successo
                        response.sendRedirect("./alert.jsp?mode=aggiornaLoc");
                    } else {
                        //c'è stato un problema con sql nell'aggiornamento della loc
                        response.sendRedirect("./alert.jsp?mode=generic");
                    }
                }
            } else {
                //c'è stato un errore con sql nella ricerca della città
                response.sendRedirect("./alert.jsp?mode=generic");
            }
        } else {
            //non ho i permessi per modificare la location
            response.sendRedirect("./alert.jsp?mode=editforbidden");
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
