$(document).ready(function (){    
    if (id !== -1) {
        countNotifications();
        setInterval(countNotifications, 1*60*1000); //1min
        // AJAX che conta le notifiche e le imposta nell'header
        function countNotifications(){
            $.ajax({
                url:'./countNotification',
                type:'POST',
                success:function(result){
                    console.log(result);
                    $('#count').text("Notifiche ("+result.toString().trim()+")");
                }, 
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR.status);
                    console.log(textStatus);
                    console.log(errorThrown);
                }       
            });
        }
    }
});