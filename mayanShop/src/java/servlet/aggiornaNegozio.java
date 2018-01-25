package servlet;

import bean.User;
import bean.negozioBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static servlet.decodeURI.decodeURIComponent;

/**
 * Servlet che permette di aggionare le infomrazioni di un negozio
 * @author Marcello
 */
@WebServlet(name = "aggiornaNegozio", urlPatterns = {"/aggiornaNegozio"})
public class aggiornaNegozio extends HttpServlet {

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
        User utente = new User(sessione);
        String idNegozio = request.getParameter("idNegozio");
        if (idNegozio.equals("nuovo")) {
            //devo aggiungere il negozio
            String nome = decodeURIComponent(request.getParameter("nome"));
            String descrizione = decodeURIComponent(request.getParameter("descrizione"));
            String url = decodeURIComponent(request.getParameter("url"));
            String orario = decodeURIComponent(request.getParameter("orario"));
            negozioBean negozio = new negozioBean(
                    nome,
                    descrizione,
                    url,
                    orario,
                    request.getParameter("tipo"));
            //controllo che l'utente sia un negoziante o amministratore
            if (utente.getTipo().equals("venditore") || utente.getTipo().equals("amministratore")) {
                //eseguo l'inserimento
                if (dbLayer.negozioDAO.insertNegozio(utente, negozio)) {
                    //ho inserito il negozio, aggiorno la lista nella sessione
                    ArrayList<negozioBean> listaNegozi = dbLayer.negozioDAO.getNegoziByAdmin(utente);
                    String json = new Gson().toJson(listaNegozi);
                    sessione.setAttribute("listaNegozi", json);
                    response.sendRedirect("./alert.jsp?mode=insertnegozio");
                } else {
                    //qualcosa è andato storto
                    response.sendRedirect("./alert.jsp?mode=insertnegozio&err=i1");
                }
            } else {
                //l'utente non ha i permessi per inserire il negozio
                response.sendRedirect("./alert.jsp?mode=insertnegozio&err=i2");
            }
        } else {
            int id = Integer.parseInt(idNegozio);
            String nome = decodeURIComponent(request.getParameter("nome"));
            String descrizione = decodeURIComponent(request.getParameter("descrizione"));
            String url = decodeURIComponent(request.getParameter("url"));
            String orario = decodeURIComponent(request.getParameter("orario"));
            negozioBean negozio = new negozioBean(
                    id,
                    nome,
                    descrizione,
                    url,
                    orario,
                    request.getParameter("tipo"));
            //controllo che abbia i permessi per modificare il negozio
            if (dbLayer.negozioDAO.isMyShop(utente, negozio)) {
                //posso modificare il negozio
                if (dbLayer.negozioDAO.updateInfo(negozio)) {
                    //ho modificato il negozio, aggiorno la lista nella sessione
                    ArrayList<negozioBean> listaNegozi = dbLayer.negozioDAO.getNegoziByAdmin(utente);
                    String json = new Gson().toJson(listaNegozi);
                    sessione.setAttribute("listaNegozi", json);
                    response.sendRedirect("./alert.jsp?mode=editnegozio");
                } else {
                    response.sendRedirect("./alert.jsp?mode=editnegozio&err=n1");
                }
            } else {
                response.sendRedirect("./alert.jsp?mode=editforbidden");
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
