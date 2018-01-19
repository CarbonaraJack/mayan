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
import bean.recensioneBean;
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
            PreparedStatement ps = con.prepareStatement("select * from Item, Foto where Item.thumbnail=Foto.id_foto;");
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
                newItem.setImmagine(rs.getString("link_foto"));

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
                
                try {
                    String queryFoto = "select link_foto from Foto, Link_Item_Foto where Foto.id_foto=Link_Item_Foto.id_foto and Link_Item_Foto.id_item="+id+";";
                    PreparedStatement psFoto = con.prepareStatement(queryFoto);
                    ResultSet rsFoto = psFoto.executeQuery();
                    
                    while(rsFoto.next()) {
                        oggetto.setFoto(rsFoto.getString("link_foto"));
                    }
                } catch (Exception e) {
                    log("Errore ricerca immagini oggetto");
                }
                
                try {
                    PreparedStatement psNeg = con.prepareStatement("select id_item, Negozio.id_negozio, num_stock, prezzo, nome, tipo from Link_Negozio_Item, Negozio where Negozio.id_negozio=Link_Negozio_Item.id_negozio and id_item=" + id);
                    ResultSet rsNeg = psNeg.executeQuery();
                    // lista per contenere i negozi in cui è disponibile l'oggetto
                    ArrayList<itemNegozioBean> listaItemNegozio = new ArrayList<itemNegozioBean>();

                    while (rsNeg.next()) {
                        itemNegozio.setIdNegozio(rsNeg.getInt("id_negozio"));
                        itemNegozio.setNomeNegozio(rsNeg.getString("nome"));
                        itemNegozio.setNumStock(rsNeg.getInt("num_stock"));
                        itemNegozio.setPrezzo(rsNeg.getDouble("prezzo"));
                        itemNegozio.setTipoNegozio(rsNeg.getString("tipo"));
                        //aggiungo il negozio in cui è disponibile l'oggetto alla lista dei negozi
                        listaItemNegozio.add(itemNegozio);
                    }
                    // aggiungo all'oggetto la lista dei negozi in cui è disponibile l'oggetto ricercato 
                    oggetto.setNegozi(listaItemNegozio);
                } catch (Exception e) {
                    log("Problemi aggiunta dei negozi in cui è presente l'item");
                }
                
                try {
                    String queryRec = "select Recensione.*, User.nome, User.cognome from Recensione, Link_Rec_Item, User where Recensione.id_recensione=Link_Rec_Item.id_recensione and User.id_user=Recensione.id_user and id_item="+id+";";
                    PreparedStatement psRec = con.prepareStatement(queryRec);
                    ResultSet rsRec = psRec.executeQuery();
                    // lista per contenere le recensioni dell'oggetto cercato
                    ArrayList<recensioneBean> listaRecensioni = new ArrayList<recensioneBean>();
                    
                    while(rsRec.next()) {
                        recensioneBean newRec = new recensioneBean();
                        newRec.setIdRecensione(rsRec.getInt("id_recensione"));
                        newRec.setStelline(rsRec.getDouble("stelline"));
                        newRec.setTesto(rsRec.getString("testo"));
                        newRec.setTipo(rsRec.getString("tipo"));
                        newRec.setIdAutore(rsRec.getInt("id_user"));
                        newRec.setNomeAutore(rsRec.getString("nome"));
                        newRec.setCognomeAutore(rsRec.getString("cognome"));
                        // aggiungo la recensione alla lista delle recensioni
                        listaRecensioni.add(newRec);
                    }
                    //aggiungo le recensioni all'oggetto cercato
                    oggetto.setRecensioni(listaRecensioni);
                } catch (Exception e) {
                    log("Errore ricerca delle recensioni");
                }
            }
        } catch (Exception e) {
            log("Errore ricerca dell'oggetto desiderato");
        }
        //ritorno l'oggetto cercato
        return oggetto;
    }
}
