<%-- 
    Document   : file upload
    Created on : 22-gen-2018, 22.14.51
    Author     : Michela e Marcello
--%>
<div class="fileUploader">
    <form method="post" action="UploadServlet" enctype="multipart/form-data">
        <label>Seleziona le immagini da caricare</label><br>
        <input type="file" name="dataFile" id="fileChooser" accept="image/*" multiple><br>
        <input type="submit" value="Upload" />
    </form>
</div>