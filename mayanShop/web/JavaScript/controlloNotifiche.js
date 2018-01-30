/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function (){                           
         
        console.log(lista);
        var s = "";
        var startLetto="";
        var stopLetto="";
    
        if ((!lista) || (lista.length <= 0)) {
            s = s + "<div>Non hai ancora ricevuto notifiche</div>";
        } else {   
           for (var i = 0; i < lista.length; i++) {
                
                startLetto = "<strong>";
                stopLetto = "</strong>";
                
                if((tipo != "amministratore" && lista[i].letto === "1") || (tipo == "amministratore" && lista[i].stato === "chiusa")){
                    startLetto = "<p>";
                    stopLetto = "</p>";
                }             
           
                s = s + "<div class='itemNotifica'>";
                    s = s + startLetto + lista[i].tipo + " - " + lista[i].nomeMittente;
                    s = s + "<em> " + lista[i].descrizione + "</em> " + stopLetto;
                    s = s + "<button onclick=\'callServlet("+lista[i].id_messaggio+", \"" +lista[i].tipo+"\");\'>Apri<//button>";
                s = s + "</div>";
                
            }
        }
        document.getElementById("tabNotifiche").innerHTML = s;
        
});

var callServlet = function(idMessaggio, tipo){
    document.location="/mayanShop/showMessage?idM="+idMessaggio+"&mType="+tipo;
}

var showMessage = function(idMessaggio){
            $.ajax({
                url:'./setLetto',
                data: {idM : idMessaggio},
                type:'POST',
                success:function(result){
                    console.log(result);
                    //cosa fare dopo la chiamata
                    //qui andrebbe il codice di output, ma il server fa solo la query settandolo 'letto'
                    //senza ritornare roba
                    //eventualmente inserire codice che "aggiorna" l'anteprima 
                    //(es. ricontrolla il set per generare il codice)
                }, 
                error: function(jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.status);
                    alert(textStatus);
                    alert(errorThrown);
                }       
            });
        }