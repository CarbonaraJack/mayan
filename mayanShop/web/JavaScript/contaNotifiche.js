/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    if (id != null) {
                
        setInterval(countNotifications, 5000);

        function countNotifications(){
            $.ajax({
                url:'countNotification',
                method:'POST',
                data:{id:id},
                success:function(result){
                    $('#count').html("" + result + " Notifiche");
                }            
            });
        }
    }
});