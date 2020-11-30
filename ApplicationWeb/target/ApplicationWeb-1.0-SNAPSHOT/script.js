/**
Fonction appelée depuis le bouton de formulaire :
-> 1. définir la propriété "action" depuis le modèle "action_template" pour poster le paramètre en tant que query
-> 2. valider / poster
**/
function sendMe(){
    var f = document.getElementById("myform");
    var typed = document.getElementById("typedName");
    console.log("adding new Name " + typed.value );
    f.setAttribute("action",
        f.getAttribute("action_template").replace('%n%',typed.value));
    $("#myform").submit();
}

$(document).ready(
  function (){
      /* Surcharger le comportement de la méthode SUBMIT */
      $("#myform").submit(function(event) {
          console.log ("Internal Handler Jquery");
          /* stop form from submitting normally */
          event.preventDefault();

          /* get the action attribute from the <form action=""> element */
          var $form = $(this),
              url = $form.attr('action');

          /* Send the data en type = PUT */
          var posting = $.ajax(url, {
              url : url,
              type: 'PUT',
               /* callback de la Request 202 */
              success : function (resul){
                  console.info(resul);
                  $('#resultServer').html(resul);
              }
          });

          /* callback done : indiquer Succes dans #resultHttp */
          posting.done(function(data) {
              $('#resultHttp').text('success');
          });
           /* callback done : indiquer Failed dans #resultHttp */
          posting.fail(function() {
              $('#resultHttp').text('failed');
          });
      });

      console.log ("init Ok");
  }
);