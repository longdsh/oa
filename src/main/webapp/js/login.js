// user

var varconfirm_Boolean = false;



// password_confirm
$('.passwordsignup_confirm').blur(function(){
  if (($(".passwordsignup").val())==($(".passwordsignup_confirm").val())){
    varconfirm_Boolean = true;
  }else {
    varconfirm_Boolean = false;
  }
});




function confirm(){
   if(varconfirm_Boolean == true){
    return true;
  }else {
    alert("两次密码不同");
    return false;
  }
}
