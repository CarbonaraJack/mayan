package servlet;

import bean.Foto;
import bean.User;
import bean.negozioBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet che gestisce il caricamento di files
 *
 * @author Marcello
 */
@WebServlet(name = "uploadFile", urlPatterns = {"/uploadFile"})
@MultipartConfig
public class uploadFile extends HttpServlet {

    private final static Logger LOGGER
            = Logger.getLogger(uploadFile.class.getCanonicalName());

    /**
     * Metodo che gestisce il caricamento dei files
     *
     * @param request i files
     * @param response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Gestisco il path dove salvare il file
        String basePath = getServletContext().getRealPath("") + "img";
        //tolgo build per caricare il file nei file di progetto
        final String path = basePath.replaceAll(File.separator + "build", "");

        User utente = new User(request.getSession());
        String source = request.getParameter("source");
        if (source.equals("negozio")) {
            System.out.println("Negozio");
            //ho caricato un file dall'editor negozio
            String idNegozio = request.getParameter("idNegozio");
            negozioBean negozio = dbLayer.negozioDAO.getNegozio(
                    Integer.parseInt(idNegozio));
            //controllo di avere i permessi di editare la foto di questo negozio
            if (dbLayer.negozioDAO.isMyShop(utente, negozio)) {
                System.out.println("è gestito da me");
                //il negozio è mio e posso caricare l'immagine.
                boolean res = true;

                //mi tiro giù i files
                final Collection<Part> files = request.getParts();
                //loop per ogni file
                for (Part file : files) {
                    System.out.println("file");
                    //mi prendo il filename
                    String fileName = file.getSubmittedFileName();
                    //se non ha filename il part è un altro parametro
                    if (fileName != null) {
                        System.out.println(file.getSubmittedFileName());
                        String estensione = fileName.substring(fileName.lastIndexOf("."));
                        //System.out.println("estensione: " + estensione);
                        String nomeFoto = dbLayer.fotoDAO.insertFoto(estensione);
                        Foto foto = dbLayer.fotoDAO.getFoto(nomeFoto);
                        OutputStream out = null;
                        InputStream filecontent = null;
                        //inizializzo gli streams
                        try {
                            out = new FileOutputStream(new File(path + File.separator
                                    + nomeFoto));
                            //apro lo stream per scrivere il file
                            filecontent = file.getInputStream();
                            //apro lo stream per leggere il file
                            int read = 0;
                            final byte[] bytes = new byte[1024];
                            //ciclo il file in lettura e scivo sul disco
                            while ((read = filecontent.read(bytes)) != -1) {
                                out.write(bytes, 0, read);
                            }
                            System.out.println("creato file " + nomeFoto + " in " + path);
                            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                                    new Object[]{nomeFoto, path});
                        } catch (FileNotFoundException fne) {
                            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                                    new Object[]{fne.getMessage()});
                        } finally {
                            //chiudo gli streams
                            if (out != null) {
                                out.close();
                            }
                            if (filecontent != null) {
                                filecontent.close();
                            }
                        }

                        //Ora che ho caricato la foto la collego al negozio
                        res = res && dbLayer.fotoNegozioDAO.linkFotoNegozio(foto, negozio);

                    }
                }
                if (res) {
                    //TUTTO OK
                    response.sendRedirect("./alert.jsp?mode=insertFoto");
                } else {
                    //qualcosa non è andato
                    response.sendRedirect("./alert.jsp?mode=insertFoto&err=f1");
                }

            } else {
                //ho provato a modificare l'immagine di un negozio ma non sono
                //il proprietario
                response.sendRedirect("./alert.jsp?mode=insertnegozio&err=i3");
            }
        } else if (source.equals("item") || source.equals("user")) {
            //sto caricando una foto per un oggetto
            int idItem = Integer.parseInt(request.getParameter("idItem"));
            boolean res = true;

            //mi tiro giù i files
            final Collection<Part> files = request.getParts();
            //loop per ogni file
            for (Part file : files) {
                System.out.println("file");
                //mi prendo il filename
                String fileName = file.getSubmittedFileName();
                //se non ha filename il part è un altro parametro
                if (fileName != null) {
                    System.out.println(file.getSubmittedFileName());
                    String estensione = fileName.substring(fileName.lastIndexOf("."));
                    //System.out.println("estensione: " + estensione);
                    String nomeFoto = dbLayer.fotoDAO.insertFoto(estensione);
                    Foto foto = dbLayer.fotoDAO.getFoto(nomeFoto);
                    OutputStream out = null;
                    InputStream filecontent = null;
                    //inizializzo gli streams
                    try {
                        out = new FileOutputStream(new File(path + File.separator
                                + nomeFoto));
                        //apro lo stream per scrivere il file
                        filecontent = file.getInputStream();
                        //apro lo stream per leggere il file
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        //ciclo il file in lettura e scivo sul disco
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        System.out.println("creato file " + nomeFoto + " in " + path);
                        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                                new Object[]{nomeFoto, path});
                    } catch (FileNotFoundException fne) {
                        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                                new Object[]{fne.getMessage()});
                    } finally {
                        //chiudo gli streams
                        if (out != null) {
                            out.close();
                        }
                        if (filecontent != null) {
                            filecontent.close();
                        }
                    }
                    //Ora che ho caricato la foto la collego all'item
                    res = res && dbLayer.fotoItemDAO.linkFotoItemCompleto(foto, idItem);

                }
            }
            if (res) {
                //TUTTO OK
                String edited = "";
                if (source.equals("item")) {
                    edited = "&id=" + idItem;
                }
                response.sendRedirect("./alert.jsp?mode=insertFoto" + edited);
            } else {
                //qualcosa non è andato
                response.sendRedirect("./alert.jsp?mode=insertFoto&err=f1");
            }

        } else {
            //ho provato a caricare qualcosa da qualche parte che non va bene
            response.sendRedirect("./alert.jsp?mode=generic");
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
