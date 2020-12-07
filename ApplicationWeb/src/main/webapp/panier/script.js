 var choixArticle = {
    data : [],
    selected : null,
    panierResultLength: 0,

    loadArticles : function(callback){
        //old : [{index : 0, nom:"cd",prix:10 }, {index : 1, nom:"livre",prix:20 },{index : 2, nom:"A4Paper",prix:19 }]
            var url = "../rest/panier/getArticles";
            var posting = $.ajax(url, {
                url : url,
                type: 'GET',
                /* callback de la Request 202 */
                success : function (resul){
                  choixArticle.data = resul;
                  callback(); //poursuite appel fonction callback fournie
                }
            });
    },

    buildTable : function(){
        $.each(choixArticle.data,function(index, value){
            var actionHtml =  '<button class="text-left" value="ADD" onclick="choixArticle.add('+ index + ' ); return false;">ajouter...</button></div>';
            var tBody = "<tr><td>%i</td><td>%n</td><td>%p</td><td>%a</td></tr>"
                .replace("%i",value.id)
                .replace("%n",value.nom)
                .replace("%p",value.prix)
                .replace("%a",actionHtml)
            $('#table_body').append(tBody);
         })
    },
    add : function(index){
        choixArticle.selected = choixArticle.data[index];
         $("#myform").submit();
    }
};

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
              type: 'POST',
              data : {
                nom: choixArticle.selected.nom,
                prix : choixArticle.selected.prix
              },
               /* callback de la Request 202 */
              success : function (resul){
                  console.info(resul);
                 //with old method (JSON items) we have to convert
                 // >  var json = JSON.parse (resul);
                 //with best method (model article items) jquery automatically converts
                 var json = resul;

                  choixArticle.panierResultLength = json.length;
                  $('#resultServer').html(JSON.stringify(resul));
              }
          });

          /* callback done : indiquer Succes dans #resultHttp */
          posting.done(function(data) {
              $('#resultHttp').text('articles ajoutés au panier : ' + choixArticle.panierResultLength  );
          });
           /* callback done : indiquer Failed dans #resultHttp */
          posting.fail(function() {
              $('#resultHttp').text('failed');
          });
      });

      /* Load data THEN Build Table */
      choixArticle.loadArticles(choixArticle.buildTable);


      console.log ("init Ok");
  }
);

