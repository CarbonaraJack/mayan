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
import com.google.gson.Gson;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
            out.println("<h1>Servlet controlloItems at " + request.getContextPath() + "</h1>");
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

        try {
            Connection con = ConnectionProvider.getCon();
            
            //String query = (String) request.getAttribute("query");

            //PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps = con.prepareStatement("select * from Item");
            ResultSet rs = ps.executeQuery();

            // array contenente tutti gli elementi cercati
            ArrayList<itemBean> lista = new ArrayList<itemBean>();
            while (rs.next()) {
                itemBean newItem = new itemBean();
                newItem.setNome(rs.getString("nome"));
                newItem.setProduttore(rs.getString("produttore"));
                newItem.setCategoria(rs.getString("categoria"));
                newItem.setIdItem(rs.getInt("id_item"));
                newItem.setPrezzo(rs.getInt("prezzo_minimo"));
                newItem.setPrezzo(rs.getDouble("voto_medio"));

                lista.add(newItem);
            }
            // conversione della lista in formato json
            String json = new Gson().toJson(lista);

            //request.setAttribute("listaItems", json);

            //aggiunta della lista alla sessione
            HttpSession session = request.getSession();
            session.setAttribute("listaItems", json);

            // reindirizza su un'altra pagina in cui vengono visualizzati i risultati
            RequestDispatcher rd = request.getRequestDispatcher("/visLista.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
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

}
