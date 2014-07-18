
jQuery( document ).ready(function( $ ) {
    
  function loadScript(){
        $.getScript("javascripts/webapp.js");
  }
  
  function submitForm(formId,elementId, callBack, errorCallback){    
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
                           callBack.apply();
                           $( elementId ).html( data );
                           //load script
                           loadScript();
                         },
                         error:function(error){
                             console.log(error);
                             errorCallback.call(this,error);
                         }
                     });
         });
       return false;
  }
  
  
  $("#addCustomer").bind("click", function(event) {
     //event.preventDefault();
     var url = "bank/createcustomer";
      $.get( url, function( data ) {
          $( "#bankAccountsBank" ).html( data );
           loadScript();
        });
    });
    
   $("#addUser").bind("click", function(event) {
     //event.preventDefault();
     var url = "bank/createuser";
      $.get( url, function( data ) {
          $( "#bankUsers" ).html( data );
           loadScript();
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
                      loadScript();
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
         loadScript();
        });
    });
    
     $("form#createUser" ).submit(function( event ) {
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
                      loadScript();
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
           loadScript();
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
 
//captacha
if($("#atmRealPerson").length){
    $("#atmRealPerson").realperson({length: 5, hashName: 'realPersonHash',});
   }
   
 if( $("#bankRealPerson").length){
     $("#bankRealPerson").realperson({length: 5, hashName: 'realPersonHash',});}

//popover
 $('.inline-deposit_button').click(function () {
        $(this).popover({
                html: true,
                trigger: 'manual',
                placement: 'bottom',
         }).popover('toggle');
         
         var _this = this;
         
         function callbackPopOver(){
               $(_this).popover('toggle');
               return ;
         }
         
         function errorCallback(error){
             console.log("error callback");
              console.log(error);
             $("#inline-form-submit-message").html(error.responseText);
         }
         $('.close-popover').off('click').on('click', function() {
             $(_this).popover('toggle');
             return false;
         });
         //
         submitForm(".deposit-inline-form","#bankAccountsBank",callbackPopOver,errorCallback);
        
           /*$('.submit-form-popover').off('click').on('click', function() {
             $(_this).popover('toggle');
             return false;
         });
         */
    });
});
