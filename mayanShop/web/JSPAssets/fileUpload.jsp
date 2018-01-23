<%-- 
    Document   : file upload
    Created on : 22-gen-2018, 22.14.51
    Author     : Michela e Marcello
--%>
<div class="fileUploader">
    <form method="post" action="../uploadFile" enctype="multipart/form-data">
        <label>Seleziona le immagini da caricare</label><br>
        <label>Puoi selezionare un numero qualsiasi di immagini.</label><br>
        <label>Dimensioni massime per immagine: 5MB</label><br>
        <label>Dimensioni massime caricamento: 20MB</label><br>
        <input type="file" name="file" id="fileChooser" accept="image/*" multiple><br>
        <input type="submit" value="Upload" />
    </form>
</div>