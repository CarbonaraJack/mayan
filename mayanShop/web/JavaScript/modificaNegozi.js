$(document).ready(function () {
    if (userId === 0) {
        console.log(userId);
        window.location = './alert.jsp?mode=notlog';
    } else {
        if (userType !== "venditore") {
            window.location = './alert.jsp?mode=restricted';
        }
    }
    /**
     //prendo il parametro err dal get
     var err = $(document).getUrlParam("err");
     var updateMessage = document.getElementById("updateMessage");
     var passwordMessage = document.getElementById("passwordMessage");
     if(err==="u1"){
     updateMessage.style.display="inline";
     updateMessage.innerHTML="Nessuna informazione da aggiornare.";
     }
     */
});