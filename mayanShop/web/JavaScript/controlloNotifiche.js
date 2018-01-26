/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function (){                           
           
        checkForNotifications();
        
        function checkForNotifications(){
            var id = session.getAttribute("userId");
            $.ajax({
                url:"showNotification",
                type:"POST",
                data:{id:id},
                success:function(result){
                    $("#result").html(result);
                }            
            });
        }
        
             
        
    
});

