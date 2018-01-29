/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function (){                           
         
        var s = "";
        var startLetto="";
        var stopLetto="";
    
        if ((!lista) || (lista.length <= 0)) {
            s = s + "<div>Non hai ancora ricevuto notifiche</div>";
        } else {   
           for (var i = 0; i < lista.length; i++) {
               if(lista[i].letto =="0"){
                    startLetto = "<strong>";
                    stopLetto = "</strong>";
                } else {
                    startLetto = "<p>";
                    stopLetto = "</p>";
                }
           
                s = s + "<div class='itemNotifica'>";
                    s = s + "<div class='itemInfo'>" + startLetto + lista[i].tipo + " - " + lista[i].nomeMittente + stopLetto + "</div>";
                    s = s + "<div class='itemText'>" + startLetto +"<em>" + lista[i].descrizione + "</em>" + stopLetto;
                    s = s + "<div class='itemAzioni'><button onclick=\"callServlet("+lista[i].idMessaggio+", " +lista[i].tipo+")\">Apri<//button></div>";
                s = s + "</div>";
                
            }
        }
        document.getElementById("tabNotifiche").innerHTML = s;
        
        var callServlet = function(idMessaggio, tipo){
            document.location.href="/mayanShp/MyServlet?nav='"+append_input+"'";
        }
});

