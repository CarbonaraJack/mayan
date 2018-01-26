/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    if (id != -1) {
        
        countNotifications();
        setInterval(countNotifications, 1*60*1000);

        function countNotifications(){
            $.ajax({
                url:"countNotification",
                type:"POST",
                data:{id:id},
                success:function(result){
                    $("#count").text("" + result + " Notifiche");
                }            
            });
        }
    }
});