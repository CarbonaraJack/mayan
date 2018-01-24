/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    if(carrelloCont != null){
        //$("#barraCarrello").append(" (" + carrelloCont + ")");
        document.getElementById("barraCarrello").innerHTML = "Carrello (" + carrelloCont + ")";
    } else {
        document.getElementById("barraCarrello").innerHTML = "Carrello (0)";
    }
});