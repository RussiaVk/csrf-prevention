
jQuery( document ).ready(function( $ ) {
  function submitForm(formId,elementId){    
         $(formId ).submit(function( event ) {
                event.preventDefault();
                var $form = $( this ),
                 url = $form.attr( "action" );
                 // Send the data using post
                 console.log($form);
                  $.ajax({
                         type: "POST",
                         url: url,
                         async: false,
                         data: $form.serialize(),
                         success: function (data) {
                           $( elementId ).html( data );
                         },
                         error:function(error){
                             console.log(error);
                         }
                     });
         });
  }
  
  
  $("#addCustomer").bind("click", function(event) {
     //event.preventDefault();
     var url = "bank/createcustomer";
      $.get( url, function( data ) {
          $( "#bankAccountsBank" ).html( data );
         // $.getScript("javascripts/webapp.js");
        });
    });
    
   $("#addUser").bind("click", function(event) {
     //event.preventDefault();
     var url = "bank/createuser";
      $.get( url, function( data ) {
          $( "#bankUsers" ).html( data );
         // $.getScript("javascripts/webapp.js");
        });
    });
    
  //post customer account
   $("form#createCustomer" ).submit(function( event ) {
           event.preventDefault();
           var $form = $( this ),
            url = $form.attr( "action" );
            // Send the data using post
            console.log($form);
             $.ajax({
                    type: "POST",
                    //the url where you want to sent the userName and password to
                    url: url,
                    async: false,
                    data: $form.serialize(),
                    success: function (data) {
                      $( "#bankAccountsBank" ).html( data );
                    },
                    error:function(error){
                     $( "#createCustomerErrorMsg" ).html( error );
                    }
                    
                });
    });
    
  //close customer form
   $("button#closeCustomerForm").bind("click", function(event) {
      event.preventDefault();
      var url = "bank?dest=account_list";
      $.get( url, function( data ) {
        $( "#bankAccountsBank" ).html( data );
          $.getScript("javascripts/webapp.js");
        });
    });
    
     $("form#createUser" ).submit(function( event ) {
      /*if ( $( "input:first" ).val() === "correct" ) {
        $( "span" ).text( "Validated..." ).show();
        return;
        }
        $( "span" ).text( "Not valid!" ).show().fadeOut( 1000 );
        */
           event.preventDefault();
           var $form = $( this ),
            url = $form.attr( "action" );
            // Send the data using post
            console.log($form);
             $.ajax({
                    type: "POST",
                    //the url where you want to sent the userName and password to
                    url: url,
                    async: false,
                    data: $form.serialize(),
                    success: function (data) {
                      $( "#bankUsers" ).html( data );
                    },
                    error:function(error){
                        console.log(error);
                    }
                    
                });
    });
    
    
     $("button#closeUserForm").bind("click", function(event) {
      //event.preventDefault();
        var url = "bank?dest=user_list";
        $.get( url, function( data ) {
           $( "#bankUsers" ).html( data );
            $.getScript("javascripts/webapp.js");
        });
    });
   /**
    * ATM button actions
    * */
    $(".atm-action").click(function(event) {
         event.preventDefault();
         var url =  $(this).attr('href');
         //$.load()
         //$.get( url, function( data ) {
            $( "#atm-main-container" ).load(url);
          //});
          return false; 
    });
    
    
    $(".atm-amount").click(submit_function);

    function submit_function(event){
            
        event.preventDefault();
        var $form = $( "#Withdraw_confirm" ),
        url = $form.attr( "action" );

        // Send the data using post
        console.log($form);
        
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            data: $form.serialize(),
            success: function (data) {
                $( "#atm-main-container" ).html( data );              
            },
            error:function(error){
                console.log(error);
            }
        });    
    }
    
    $(".atm-withdraw").click(submit_withdraw);

    function submit_withdraw(event){
            
        event.preventDefault();
        var $form = $( "#Withdraw" ),
        url = $form.attr( "action" );
        // Send the data using post
        console.log($form);
        
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            data: $form.serialize(),
            success: function (data) {
                $( "#atm-main-container" ).html( data );              
            },
            error:function(error){
                console.log(error);
            }
        });    
    }
 
    /*
    $(".logout").click(function(event) {
         event.preventDefault();
         var url =  $(this).attr('href');
         //$.load()
         //$.get( url, function( data ) {
           // $( "#atm-main-container" ).load(url);
          //});
          $.post(url,function(data,status){
              //alert("Data: " + data + "\nStatus: " + status);
            });
            
          return false; 
    });
    */
   
   
//captacha
if($("#atmRealPerson").length){
    $("#atmRealPerson").realperson({length: 5, hashName: 'realPersonHash',});
   }
   
 if( $("#bankRealPerson").length){
     $("#bankRealPerson").realperson({length: 5, hashName: 'realPersonHash',});}

});
