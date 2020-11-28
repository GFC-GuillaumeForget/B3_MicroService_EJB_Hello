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
      /* attach a submit handler to the form */
      $("#myform").submit(function(event) {
          console.log ("Internal Handler Jquery");
          /* stop form from submitting normally */
          event.preventDefault();

          /* get the action attribute from the <form action=""> element */
          var $form = $(this),
              url = $form.attr('action');

          /* Send the data */
          var posting = $.ajax(url, {
              url : url,
              type: 'PUT',

              success : function (resul){
                  console.info(resul);
                  $('#resultServer').html(resul);
              }
          });

          /* Alerts the results */
          posting.done(function(data) {
              $('#resultHttp').text('success');
          });
          posting.fail(function() {
              $('#resultHttp').text('failed');
          });
      });

      console.log ("init Ok");
  }
);