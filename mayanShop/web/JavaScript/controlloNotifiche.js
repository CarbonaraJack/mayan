/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    if (id != null) {
                
        setInterval(checkForNotifications, 5000);

        function checkForNotifications(){
            $.ajax({
                url:'ShowNotification',
                method:'POST',
                data:{id:id},
                success:function(result){
                    $('#result').html(result);
                }            
            });
        }
    }
});

