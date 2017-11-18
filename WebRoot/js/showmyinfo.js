/**
 * 对更新用户信息的校验
 */
var b1 = true;
var b2 = true;
var b3 = true;
var b4 = true;
var b5 = true;

function checkPasswd(){
    password=document.getElementById("passwd").value.trim();
    //var password=$("#password").value;
    //$("#passwordInfo").innerHTML="";
    //密码长度在8个字符到16个字符，由字母、数字和".""-""_""@""#""$"组成
    //var passwordRegex=/^[0-9A-Za-z.\-\_\@\#\$]{8,16}$/;
    //密码长度在8个字符到16个字符，由字母、数字和"_"组成
    var passwordRegex=/^[0-9A-Za-z_]\w{7,15}$/;
    if(!passwordRegex.test(password)){
    	document.getElementById("passwddiv").style.display="block";
        document.getElementById("passwdInfo").innerHTML="密码长度必须在8个字符到16个字符之间";
        b1 = false;
    }else{
    	document.getElementById("passwddiv").style.display="none";
    	document.getElementById("passwdInfo").innerHTML="";
        b1 = true;
    }
}

//验证邮编
function checkZip(){
    var zip=document.getElementById("zip").value.trim();
    /*六位的邮编,只能包含数字*/
    var zipRegex=/^[1-9][0-9]{5}$/;
    
    if(!zipRegex.test(zip)){
    	document.getElementById("zip_div").style.display="block";
        document.getElementById("zipInfo").innerHTML="邮编格式不正确";
        b2 = false;
    }else if(zipRegex.test(zip)) {
    	document.getElementById("zip_div").style.display="none";
    	document.getElementById("zipInfo").innerHTML="";
        b2 = true;
    }
}

//验证地址
function checkAddress(){
    var address=document.getElementById("address").value.trim();
    
    if(address == null || "" == address){
    	document.getElementById("address_div").style.display="block";
        document.getElementById("addressInfo").innerHTML="地址不能为空!";
        b3 = false;
    }else {
    	document.getElementById("address_div").style.display="none";
    	document.getElementById("addressInfo").innerHTML="";
        b3 = true;
    }
}

//验证电话
function checkTel(){
    var address=document.getElementById("telephone").value.trim();
    var telRegex= /^(13[0-9]{9})|(15[89][0-9]{8})|(18[67][0-9]{8})|(17[6][0-9]{8})$/
    
    if(!telRegex.test(address)){
    	document.getElementById("telephone_div").style.display="block";
        document.getElementById("telephoneInfo").innerHTML="手机号码格式错误!";
        b4 = false;
    }else if (telRegex.test(address)) {
    	document.getElementById("telephone_div").style.display="none";
    	document.getElementById("telephoneInfo").innerHTML="";
        b4 = true;
    }
}

//验证邮箱
function checkEmail(){
    var email = document.getElementById("email").value.trim();
    var emailRegex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
    
    if(!emailRegex.test(email)){
    	document.getElementById("email_div").style.display="block";
        document.getElementById("emailInfo").innerHTML="邮箱格式不正确!";
        b5 = false;
    }else if (emailRegex.test(email)) {
    	document.getElementById("email_div").style.display="none";
    	document.getElementById("emailInfo").innerHTML="";
        b5 = true;
    }
}
//验证用户的点击修改按钮
function checkModify() {
	var modify = document.getElementById("modify");

	if (b2 && b3 && b4 && b5) {
		// 可以点击	
		document.getElementById("modify").disabled = true;
		alert("修改成功!");
		return true;
	} else {
		document.getElementById("modify").disabled = false;
		alert("格式错误!");
		return false;
	}
}


//鼠标移入
function over() {
	document.getElementById("modify").style.background = "red";
}

//鼠标移出
function out() {
	document.getElementById("modify").style.background = "#F75D08";
}







