/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import bean.itemBean;
import bean.itemNegozioBean;
import com.google.gson.Gson;
import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michela
 */
@WebServlet(name = "controlloItems", urlPatterns = {"/controlloItems"})
public class controlloItems extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String ricerca)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlloItems</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlloItems at " + ricerca + "</h1>");
            out.println("</body>");
            out.println("</html>");

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

        String ricerca = (String) request.getParameter("ric");
        String oggettoSingolo = (String) request.getParameter("objS");
        String idOggetto = (String) request.getParameter("idOgg");
        
        if (ricerca.equals("true")) {
            ArrayList<itemBean> lista = ricercaListaOggetti();
            
            // conversione della lista in formato json
            String json = new Gson().toJson(lista);

            //request.setAttribute("listaItems", json);
            //aggiunta della lista alla sessione
            HttpSession session = request.getSession();
            session.setAttribute("listaItems", json);

            // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
            //RequestDispatcher rd = request.getRequestDispatcher("/visLista.jsp");
            //rd.forward(request, response);
            
            response.sendRedirect("/mayanShop/visLista.jsp");
        } 
        else if (oggettoSingolo.equals("true")) {
            itemBean oggetto = ricercaOggettoSingolo(idOggetto);
            
            // conversione della lista in formato json
            String json = new Gson().toJson(oggetto);

            //request.setAttribute("listaItems", json);
            //aggiunta dell'oggetto alla sessione
            HttpSession session = request.getSession();
            session.setAttribute("item", json);

            // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
            //RequestDispatcher rd = request.getRequestDispatcher("/DisplayObject.jsp");
            //rd.forward(request, response);
            response.sendRedirect("/mayanShop/visOggetto.jsp?item=" + idOggetto);
        }
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

    private ArrayList<itemBean> ricercaListaOggetti() {
        // array contenente tutti gli elementi cercati
        ArrayList<itemBean> lista = new ArrayList<itemBean>();
        try {
            Connection con = ConnectionProvider.getCon();

            //String query = (String) request.getAttribute("query");
            //PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps = con.prepareStatement("select * from Item");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                itemBean newItem = new itemBean();
                newItem.setNome(rs.getString("nome"));
                newItem.setProduttore(rs.getString("produttore"));
                newItem.setCategoria(rs.getString("categoria"));
                newItem.setDescrizione(rs.getString("descr_item"));
                newItem.setIdItem(rs.getInt("id_item"));
                newItem.setPrezzoMinimo(rs.getInt("prezzo_minimo"));
                newItem.setVoto(rs.getDouble("voto_medio"));

                lista.add(newItem);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    // funzione che cerca un singolo oggetto 
    private itemBean ricercaOggettoSingolo(String id) {
        itemBean oggetto = new itemBean();
        itemNegozioBean itemNegozio = new itemNegozioBean();

        try {
            Connection con = ConnectionProvider.getCon();

            //String query = (String) request.getAttribute("query");
            //PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps = con.prepareStatement("select * from Item where id_item=" + id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                oggetto.setNome(rs.getString("nome"));
                oggetto.setProduttore(rs.getString("produttore"));
                oggetto.setCategoria(rs.getString("categoria"));
                oggetto.setDescrizione(rs.getString("descr_item"));
                oggetto.setIdItem(rs.getInt("id_item"));
                oggetto.setPrezzoMinimo(rs.getInt("prezzo_minimo"));
                oggetto.setVoto(rs.getDouble("voto_medio"));
                
                PreparedStatement ps2 = con.prepareStatement("select id_item, Negozio.id_negozio, num_stock, prezzo, nome, tipo from Link_Negozio_Item, Negozio where Negozio.id_negozio=Link_Negozio_Item.id_negozio and id_item=" + id);
                ResultSet rs2 = ps2.executeQuery();
                // lista per contenere i negozi in cui è disponibile l'oggetto
                ArrayList<itemNegozioBean> listaItemNegozio = new ArrayList<itemNegozioBean>();
                
                while (rs2.next()) {
                    itemNegozio.setIdNegozio(rs2.getInt("id_negozio"));
                    itemNegozio.setNomeNegozio(rs2.getString("nome"));
                    itemNegozio.setNumStock(rs2.getInt("num_stock"));
                    itemNegozio.setPrezzo(rs2.getDouble("prezzo"));
                    itemNegozio.setTipoNegozio(rs2.getString("tipo"));
                    //aggiungo il negozio in cui è disponibile l'oggetto alla lista dei negozi
                    listaItemNegozio.add(itemNegozio);
                }
                // aggiungo all'oggetto la lista dei negozi in cui è disponibile l'oggetto ricercato 
                oggetto.setNegozi(listaItemNegozio);
            }
        } catch (Exception e) {
        }
        //ritorno l'oggetto cercato
        return oggetto;
    }
}
