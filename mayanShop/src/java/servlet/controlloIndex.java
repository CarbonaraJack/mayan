/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.ConnectionProvider;
import bean.itemBean;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
@WebServlet(name = "controlloIndex", urlPatterns = {"/index"})
public class controlloIndex extends HttpServlet {

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
            out.println("<title>Servlet controlloIndex</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlloIndex at " + request.getContextPath() + "</h1>");
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
        ArrayList<itemBean> listaVis = ricercaListaOggettiVisualizzati();
        ArrayList<itemBean> listaAcq = ricercaListaOggettiAcquistati();
            
        // conversione della lista in formato json
        String jsonVis = new Gson().toJson(listaVis);
        String jsonAcq = new Gson().toJson(listaAcq);

        //aggiunta della lista alla sessione
        HttpSession session = request.getSession();
        session.setAttribute("listaVis", jsonVis);
        session.setAttribute("listaAcq", jsonAcq);

        // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
        //RequestDispatcher rd = request.getRequestDispatcher("/visLista.jsp");
        //rd.forward(request, response);
            
        response.sendRedirect("/mayanShop/index.jsp");
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
    
    private ArrayList<itemBean> ricercaListaOggettiVisualizzati() {
        // array contenente tutti gli elementi cercati
        ArrayList<itemBean> lista = new ArrayList<itemBean>();
        try {
            Connection con = ConnectionProvider.getCon();

            PreparedStatement ps = con.prepareStatement("select * from Item, Foto where Item.thumbnail=Foto.id_foto order by tot_visualizzazioni desc limit 10;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                itemBean newItem = new itemBean();
                newItem.setNome(rs.getString("nome"));
                newItem.setProduttore(rs.getString("produttore"));
                newItem.setCategoria(rs.getString("categoria"));
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
    
    private ArrayList<itemBean> ricercaListaOggettiAcquistati() {
        // array contenente tutti gli elementi cercati
        ArrayList<itemBean> lista = new ArrayList<itemBean>();
        try {
            Connection con = ConnectionProvider.getCon();

            PreparedStatement ps = con.prepareStatement("select * from Item, Foto where Item.thumbnail=Foto.id_foto order by tot_acquistato desc limit 10;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                itemBean newItem = new itemBean();
                newItem.setNome(rs.getString("nome"));
                newItem.setProduttore(rs.getString("produttore"));
                newItem.setCategoria(rs.getString("categoria"));
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
}
