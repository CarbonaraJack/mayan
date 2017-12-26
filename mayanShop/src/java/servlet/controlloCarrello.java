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

        //String del = request.getParameter("del");
        
        ArrayList<carrelloBean> lista;
        
        /*if (del.equals("true")) {
            String idDel = request.getParameter("idDel");
            lista = delete(idDel);
        } else {
            String id = request.getParameter("item");
            String quant = request.getParameter("quant");
            lista = addCart(carrello,id,quant);
        }*/
        String id = request.getParameter("item");
            String quant = request.getParameter("quant");
            lista = addCart(carrello,id,quant);

        String jsonList = new Gson().toJson(lista);
        session.setAttribute("carrello", jsonList);
        
        RequestDispatcher rd = request.getRequestDispatcher("/carrello.jsp");
        rd.forward(request, response);
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

    private ArrayList<carrelloBean> addCart(String carrello, String id, String quantita) {
        Gson gson = new Gson();
        TypeToken<ArrayList<carrelloBean>> listaCarrelloType = new TypeToken<ArrayList<carrelloBean>>() {};
        ArrayList<carrelloBean> listaCarrello = gson.fromJson(carrello, listaCarrelloType.getType());
           
        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement("select * from Item where Item.id_item=" + id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                carrelloBean ogg = new carrelloBean();
                ogg.setIdItem(rs.getInt("id_item"));
                ogg.setNome(rs.getString("nome"));
                ogg.setProduttore(rs.getString("produttore"));
                ogg.setPrezzo(rs.getDouble("prezzo_minimo"));
                ogg.setQuantita(Integer.parseInt(quantita));
                
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
        }
        return listaCarrello;
    }
    
    private ArrayList<carrelloBean> delete(String id) {
        return null;
    }

}
