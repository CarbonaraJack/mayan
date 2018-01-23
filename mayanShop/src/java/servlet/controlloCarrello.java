/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.ConnectionProvider;
import bean.carrelloBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michela
 */
@WebServlet(name = "controlloCarrello", urlPatterns = {"/controlloCarrello"})
public class controlloCarrello extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlloCarrello</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlloCarrello at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        HttpSession session = request.getSession();
        String carrello = (String) session.getAttribute("carrello");

        String del = request.getParameter("del");
        
        ArrayList<carrelloBean> lista;
        
        if (del.equals("true")) {
            String idDel = request.getParameter("idDel");
            String idNeg = request.getParameter("idNeg");
            lista = delete(idDel, idNeg, carrello);
        } else {
            String id = request.getParameter("item");
            String idNegozio = request.getParameter("negozio");
            String quant = request.getParameter("quant");
            lista = addCart(carrello,id,idNegozio,quant);
        }
        /*String id = request.getParameter("item");
            String quant = request.getParameter("quant");
            lista = addCart(carrello,id,quant);*/

        String jsonList = new Gson().toJson(lista);
        session.setAttribute("carrello", jsonList);
        
        //RequestDispatcher rd = request.getRequestDispatcher("/carrello.jsp");
        //rd.forward(request, response);
        response.sendRedirect("/mayanShop/carrello.jsp");
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

    private ArrayList<carrelloBean> addCart(String carrello, String id, String idNegozio, String quantita) {
        Gson gson = new Gson();
        TypeToken<ArrayList<carrelloBean>> listaCarrelloType = new TypeToken<ArrayList<carrelloBean>>() {};
        ArrayList<carrelloBean> listaCarrello = gson.fromJson(carrello, listaCarrelloType.getType());
           
        try {
            Connection con = ConnectionProvider.getCon();
            String query = "select Item.id_item, Item.nome as nomeItem, produttore, Negozio.nome as nomeNegozio, Link_Negozio_Item.prezzo, Link_Negozio_Item.num_stock, Foto.link_foto, Negozio.id_negozio";
            query = query + " from Item, Link_Negozio_Item, Negozio, Foto ";
            query = query + "where Item.id_item=Link_Negozio_Item.id_item and Negozio.id_negozio=Link_Negozio_Item.id_negozio and Item.thumbnail=Foto.id_foto and Link_Negozio_Item.id_negozio=" + idNegozio + " and Item.id_item="+id+";";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                carrelloBean ogg = new carrelloBean();
                ogg.setIdItem(rs.getInt("id_item"));
                ogg.setNome(rs.getString("nomeItem"));
                ogg.setProduttore(rs.getString("produttore"));
                ogg.setVenditore(rs.getString("nomeNegozio"));
                ogg.setPrezzo(rs.getDouble("prezzo"));
                ogg.setImmagine(rs.getString("link_foto"));
                ogg.setQuantita(Integer.parseInt(quantita));
                ogg.setIdVenditore(rs.getInt("id_negozio"));
                
                // se ci sono già oggetti presenti nel carrello, aggiungo l'oggetto desiderato alla lista degli oggetti già presenti
                if (carrello != null) {
                    listaCarrello.add(ogg);
                } else {
                    listaCarrello = new ArrayList<>();
                    listaCarrello.add(0, ogg);
                    //listaCarrello.add(ogg);
                }
            }
        } catch (Exception e) {
            log("Errore nella ricerca dell'oggetto da aggiungere al carrello");
        }
        return listaCarrello;
    }
    
    private ArrayList<carrelloBean> delete(String idItem, String idNeg, String carrello) {
        Gson gson = new Gson();
        TypeToken<ArrayList<carrelloBean>> listaCarrelloType = new TypeToken<ArrayList<carrelloBean>>() {};
        ArrayList<carrelloBean> listaCarrello = gson.fromJson(carrello, listaCarrelloType.getType());
        
        Iterator<carrelloBean> it = listaCarrello.iterator();
        //int index = 0;
        
        while (it.hasNext()) {
            carrelloBean oggCorrente = it.next();
            //se l'oggetto corrente è l'oggetto cercato, viene rimosso dalla lista
            if ((oggCorrente.getIdItem() == Integer.valueOf(idItem)) && (oggCorrente.getIdVenditore() == Integer.valueOf(idNeg))) {
                //rimuove l'oggetto dalla lista
                it.remove();
            }
        }
        
        return listaCarrello;
    }

}
