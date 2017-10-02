//$(function() {
	// user

	var varconfirm_Boolean = false;
	
	//验证学号长度
	$(".idsignup").change(function(){
    	//alert($(this).val().length)
    	if($(this).val().length<=9){
    		alert("学号长度需大于等于10")
    	}
    })

	// password_confirm
	$("#passwordsignup_confirm").change(function(){
	  if (($("#passwordsignup").val())==($("#passwordsignup_confirm").val())){
	    varconfirm_Boolean = true;
	  }else {
	  	alert("密码不同");
	    varconfirm_Boolean = false;
	  }
	});


	function confirm(){
	   if(varconfirm_Boolean == true){
	    return true;
	  }else {
	    alert("两次输入密码不同");
	    return false;
	  }
	}

//});
