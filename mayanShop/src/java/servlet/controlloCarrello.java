/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.carrelloBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet che si occupa dell'aggiunta e della rimozione degli oggetti dal carrello e dell'aggiunta dei nuovi acquisti
 * @author Michela
 */
@WebServlet(name = "controlloCarrello", urlPatterns = {"/controlloCarrello"})
public class controlloCarrello extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequestGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String carrello = (String) session.getAttribute("carrello");
        // variabile che specifica se è necessario rimuovere un elemento oppure tutti gli elementi presenti nel carrello
        String del = request.getParameter("del");
        //String delAll = request.getParameter("delAll");        

        Gson gson = new Gson();
        TypeToken<ArrayList<carrelloBean>> listaCarrelloType = new TypeToken<ArrayList<carrelloBean>>() {};
        ArrayList<carrelloBean> listaCarrello = gson.fromJson(carrello, listaCarrelloType.getType());
        log("del "+del);
        if (del.equals("all")){
            log("here");
            session.removeAttribute("carrello");
            session.removeAttribute("contCarrello");
        } else if (del.equals("true")) {
            String idDel = request.getParameter("idDel");
            String idNeg = request.getParameter("idNeg");
            delete(idDel, idNeg, listaCarrello);
            String jsonCont = new Gson().toJson(listaCarrello.size());
            String jsonList = new Gson().toJson(listaCarrello);
            session.setAttribute("carrello", jsonList);
            session.setAttribute("contCarrello", jsonCont);
        } else {
            log("qui");
            String id = request.getParameter("item");
            String idNegozio = request.getParameter("negozio");
            log("id "+id);
            log("idN "+idNegozio);
            //String quant = request.getParameter("quant");
            String quant = request.getParameter("quantita" + id + idNegozio);
            log("Q: "+quant);
            log("size "+listaCarrello.size());
            if(Integer.parseInt(quant) > 0){
                carrelloBean ogg = dbLayer.carrelloDAO.getItemCarrello(Integer.parseInt(id), Integer.parseInt(idNegozio));
                ogg.setQuantita(Integer.parseInt(quant));

                // se ci sono già oggetti presenti nel carrello, aggiungo l'oggetto desiderato alla lista degli oggetti già presenti
                if (carrello != null) {
                    log("dentro");
                    if(ricercaQuantita(Integer.parseInt(id),Integer.parseInt(idNegozio),listaCarrello,Integer.parseInt(quant)) == -1){
                        listaCarrello.add(ogg);
                    }
                } else {
                    listaCarrello = new ArrayList<>();
                    listaCarrello.add(0, ogg);
                }
            }
            String jsonCont = new Gson().toJson(listaCarrello.size());
            String jsonList = new Gson().toJson(listaCarrello);
            session.setAttribute("carrello", jsonList);
            session.setAttribute("contCarrello", jsonCont);
        }

        response.sendRedirect("/mayanShop/carrello.jsp");
    }
    
    /**
     * Processes requests for HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequestPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeCognome = (String)request.getParameter("nomeCognome");
        String indirizzo = (String)request.getParameter("indirizzo");
        String citta = (String)request.getParameter("citta");
        String provincia = (String)request.getParameter("provincia");
        String cap = (String)request.getParameter("cap");
        String paese = (String)request.getParameter("paese");
        String numTel = (String)request.getParameter("numTel");
        String numCarta = (String)request.getParameter("numCarta");
        String intestatario = (String)request.getParameter("intestatario");
        String scadenza = (String)request.getParameter("scadenza");

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String carrello = (String) session.getAttribute("carrello");

        Gson gson = new Gson();
        TypeToken<ArrayList<carrelloBean>> listaCarrelloType = new TypeToken<ArrayList<carrelloBean>>() {};
        ArrayList<carrelloBean> listaCarrello = gson.fromJson(carrello, listaCarrelloType.getType());
        Iterator<carrelloBean> it = listaCarrello.iterator();

        while (it.hasNext()) {
            carrelloBean oggCorrente = it.next();
            int newStock = dbLayer.negozioDAO.getNumStock(oggCorrente.getIdItem(), oggCorrente.getIdVenditore()) - oggCorrente.getQuantita();
            int newTotAcquistato = dbLayer.itemDAO.getTotAcquistato(oggCorrente.getIdItem()) + oggCorrente.getQuantita();
            if(newStock < 0){
                log("Errore, non sono disponibili gli item richiesti");
            } else {
                boolean ris = dbLayer.acquistoDAO.insertAcquisto(oggCorrente.getQuantita(), oggCorrente.getQuantita()*oggCorrente.getPrezzo(), new Date(), oggCorrente.getIdItem(), userId, oggCorrente.getIdVenditore());
                if(ris){
                    log("ce l'ho fatta");
                    dbLayer.negozioDAO.updateNumStock(oggCorrente.getIdItem(), oggCorrente.getIdVenditore(), newStock);
                    dbLayer.itemDAO.updateAcquistati(oggCorrente.getIdItem(), newTotAcquistato);
                    session.removeAttribute("carrello");
                    session.removeAttribute("contCarrello");
                } else{
                    log("non ce l'ho fatta");
                }
            }
        }
        response.sendRedirect("/mayanShop/index.jsp");
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
        processRequestGet(request, response);
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
        processRequestPost(request, response);
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

    /**
     * funzione che elimina l'item specificato dal carrello
     * @param idItem id dell'oggetto da eliminare
     * @param idNeg id del negozio da cui si sta acquistando l'item, necessario per individuare l'item corretto da eliminare
     * @param listaCarrello lista di item (tipo carrelloBean) presenti nel carrello
     * @return 
     */
    private int delete(String idItem, String idNeg, ArrayList<carrelloBean> listaCarrello) {
        Iterator<carrelloBean> it = listaCarrello.iterator();
        //int index = 0;

        while (it.hasNext()) {
            carrelloBean oggCorrente = it.next();
            //se l'oggetto corrente è l'oggetto cercato, viene rimosso dalla lista
            if ((oggCorrente.getIdItem() == Integer.valueOf(idItem)) && (oggCorrente.getIdVenditore() == Integer.valueOf(idNeg))) {
                //rimuove l'oggetto dalla lista
                it.remove();
                return 1;
            }
        }
        return -1;
    }

    /**
     * funzione che aggiorna la quantità specifica dell'item voluto se è presente nella lista, altrimenti ritorna che l'item non è stato trovato
     * @param idItem id dell'item da cercare
     * @param idNegozio id del negozio in cui l'item è presente
     * @param listaCarrello lista degli item presenti nel carrello
     * @param quantita quantità dell'item da aggiornare
     * @return 
     */
    private int ricercaQuantita(int idItem, int idNegozio, ArrayList<carrelloBean> listaCarrello, int quantita){
        Iterator<carrelloBean> it = listaCarrello.iterator();
        int index = 0;

        while (it.hasNext()) {
            carrelloBean oggCorrente = it.next();
            if ((oggCorrente.getIdItem() == idItem) && (oggCorrente.getIdVenditore() == idNegozio)) {
                quantita = quantita + oggCorrente.getQuantita();
                log(String.valueOf(quantita));
                oggCorrente.setQuantita(quantita);
                log(String.valueOf(oggCorrente.getQuantita()));
                return index;
            }
            index++;
        }
        return -1;
    }

}
