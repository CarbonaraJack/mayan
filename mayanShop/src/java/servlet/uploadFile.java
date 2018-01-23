package servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
        response.setContentType("text/html;charset=UTF-8");

        // Gestisco il path dove salvare il file
        final String path = getServletContext().getRealPath("") + "img";
        //mi tiro giù i files
        final Collection<Part> files = request.getParts();
        //loop per ogni file
        for (Part file : files) {
            //mi prendo il filename
            String fileName = file.getSubmittedFileName();
            OutputStream out = null;
            InputStream filecontent = null;
            //inizializzo gli streams
            try {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                //apro lo stream per scrivere il file
                filecontent = file.getInputStream();
                //apro lo stream per leggere il file
                int read = 0;
                final byte[] bytes = new byte[1024];
                //ciclo il file in lettura e scivo sul disco
                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                System.out.println("creato file "+fileName+" in "+path);
                LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
                        new Object[]{fileName, path});
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
