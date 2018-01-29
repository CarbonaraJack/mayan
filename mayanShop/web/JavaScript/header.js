$(document).ready(function () {
    if (carrelloCont != null) {
        //$("#barraCarrello").append(" (" + carrelloCont + ")");
        document.getElementById("barraCarrello").innerHTML = "Carrello (" + carrelloCont + ")";
    } else {
        document.getElementById("barraCarrello").innerHTML = "Carrello (0)";
    }
});