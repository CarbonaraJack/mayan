/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
    if (id != null) {
                
        function checkForNotifications(){
            $.ajax({
                url:'showNotification',
                method:'POST',
                data:{id:id},
                success:function(result){
                    $('#result').html(result);
                }            
            });
        }
    }
});

