/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dbLayer.DBSegnalazione;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Thomas
 */
@WebServlet(name = "inviaSegnalazione", urlPatterns = {"/inviaSegnalazione"})
public class inviaSegnalazione extends HttpServlet {

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
            out.println("<title>Servlet inviaSegnalazione</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet inviaSegnalazione at " + request.getContextPath() + "</h1>");
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
        
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");
        String idNegozio = (String) request.getParameter("idNeg");
        String idTransazione = (String) request.getParameter("idT");
        
        DBSegnalazione db = new DBSegnalazione();
        String idVenditore = db.getVenditore(idNegozio);
        
        session.setAttribute("userId", userId);
        session.setAttribute("idVen", idVenditore);
        session.setAttribute("idT", idTransazione);
        
        response.sendRedirect("/mayanShop/segnala.jsp");
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
        
        HttpSession session = request.getSession();
        
        String text = request.getParameter("testo");
        
        int userId = (Integer) session.getAttribute("userId");
        String idVenditore = (String) session.getAttribute("idVen");
        String idTransazione = (String) session.getAttribute("idT");
       
        int idVen = Integer.parseInt(idVenditore);
        int idT = Integer.parseInt(idTransazione);
        
        DBSegnalazione db = new DBSegnalazione();
        
        boolean isDone = db.insertSegnalazione(text, idVen, userId, idT);   
         
        PrintWriter out = response.getWriter();
        
        if (isDone){
            //insert riuscito           
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Messaggio inviato correttamente');");
            out.println("location='index.jsp';");
            out.println("</script>");
            
        }else{
            //insert non riuscito
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Qualcosa è andato storto! Riprova più tardi');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
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
