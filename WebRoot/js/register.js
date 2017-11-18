/**
 * 对注册界面的一些验证
 */
var b1, b2, b3, b4, b5, b6, b7;
//验证用户名
function checkUserName(){
    var name=document.getElementById("username").value.trim();
    var nameRegex=/^[^@#]{3,16}$/;
    if(!nameRegex.test(name)){
    	document.getElementById("namediv").style.display="block";
        document.getElementById("nameInfo").innerHTML="用户名为3~16个字符，且不能包含”@”和”#”字符";
        document.getElementById("nameimg").src="css/easyUI/icons/no.png";
        b1 = false;
    }else{
        b1 = true;
    }
    
    //判断用户名是否存在
    var xmlHttp;
    if (window.XMLHttpRequest) {
    	xmlHttp = new XMLHttpRequest();
    } else {
    	//兼容ie5,6
    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    //请求数据,判断用户名是否存在
    xmlHttp.open("get", "UserCheck?type=usernameCheck&username=" + name, true);
	xmlHttp.send();
    
	//接收数据
	xmlHttp.onreadystatechange = function() {
//		alter("xmlHttp.readyState : " + xmlHttp.readyState + "xmlHttp.status : " + xmlHttp.status);
		//当请求处理完成的时候,把数据显示出来
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200 && b1) {
			var msg = eval("(" + xmlHttp.responseText + ")");
//			alert(msg);
			if (msg.flag == true) {
//				alert(true);
				//说明该用户名可以注册
				document.getElementById("nameInfo").innerHTML="";
		        document.getElementById("namediv").style.display="block";
		        document.getElementById("nameimg").src="css/easyUI/icons/ok.png";
		        b1 = true;
			} else {
//				alert(false);
				document.getElementById("namediv").style.display="block";
		        document.getElementById("nameInfo").innerHTML="该用户名已被注册";
		        document.getElementById("nameimg").src="css/easyUI/icons/no.png";
		        b1 = false;
			}
		}
	};
}

var password;
//验证密码（长度在8个字符到16个字符）
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
        document.getElementById("passwdimg").src="css/easyUI/icons/no.png";
        document.getElementById("passwdInfo").innerHTML="密码长度必须在8个字符到16个字符之间";
        b2 = false;
    }else{
    	document.getElementById("passwddiv").style.display="block";
        document.getElementById("passwdimg").src="css/easyUI/icons/ok.png";
        document.getElementById("passwdInfo").innerHTML="";
        b2 = true;
    }
}

//验证校验密码（和上面密码必须一致）
function checkSurePasswd() {
    var repassword=document.getElementById("sure_passwd").value.trim();
    //校验密码和上面密码必须一致
    if(repassword!==password){
    	document.getElementById("sure_passwd_div").style.display="block";
    	document.getElementById("sure_passwd_img").src="css/easyUI/icons/no.png";
        document.getElementById("sure_passwdInfo").innerHTML="两次输入的密码不一致";
        b3 = false;
    }else if(repassword==password){
    	document.getElementById("sure_passwd_div").style.display="block";
    	document.getElementById("sure_passwd_img").src="css/easyUI/icons/ok.png";
        document.getElementById("sure_passwdInfo").innerHTML="";
        b3 = true;
    }
}

//验证邮编
function checkZip(){
    var zip=document.getElementById("zip").value.trim();
    /*六位的邮编,只能包含数字*/
    var zipRegex=/^[1-9][0-9]{5}$/;
    
    if(!zipRegex.test(zip)){
    	document.getElementById("zip_div").style.display="block";
    	document.getElementById("zip_img").src="css/easyUI/icons/no.png";
        document.getElementById("zipInfo").innerHTML="邮编格式不正确";
        b4 = false;
    }else if(zipRegex.test(zip)) {
    	document.getElementById("zip_div").style.display="block";
    	document.getElementById("zip_img").src="css/easyUI/icons/ok.png";
        document.getElementById("zipInfo").innerHTML="";
        b4 = true;
    }
}

//验证地址
function checkAddress(){
    var address=document.getElementById("address").value.trim();
    
    if(address == null || "" == address){
    	document.getElementById("address_div").style.display="block";
    	document.getElementById("address_img").src="css/easyUI/icons/no.png";
        document.getElementById("addressInfo").innerHTML="地址不能为空!";
        b5 = false;
    }else {
    	document.getElementById("address_div").style.display="block";
    	document.getElementById("address_img").src="css/easyUI/icons/ok.png";
        document.getElementById("addressInfo").innerHTML="";
        b5 = true;
    }
}

//验证电话
function checkTel(){
    var address=document.getElementById("telephone").value.trim();
    var telRegex= /^(13[0-9]{9})|(15[89][0-9]{8})|(18[67][0-9]{8})|(17[6][0-9]{8})$/
    
    if(!telRegex.test(address)){
    	document.getElementById("telephone_div").style.display="block";
    	document.getElementById("telephone_img").src="css/easyUI/icons/no.png";
        document.getElementById("telephoneInfo").innerHTML="手机号码格式错误!";
        b6 = false;
    }else if (telRegex.test(address)) {
    	document.getElementById("telephone_div").style.display="block";
    	document.getElementById("telephone_img").src="css/easyUI/icons/ok.png";
        document.getElementById("telephoneInfo").innerHTML="";
        b6 = true;
    }
}

//验证邮箱
function checkEmail(){
    var email = document.getElementById("email").value.trim();
    var emailRegex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
    
    if(!emailRegex.test(email)){
    	document.getElementById("email_div").style.display="block";
    	document.getElementById("email_img").src="css/easyUI/icons/no.png";
        document.getElementById("emailInfo").innerHTML="邮箱格式不正确!";
        b7 = false;
    }else if (emailRegex.test(email)) {
    	document.getElementById("email_div").style.display="block";
    	document.getElementById("email_img").src="css/easyUI/icons/ok.png";
        document.getElementById("emailInfo").innerHTML="";
        b7 = true;
    }
}
//验证用户的点击注册按钮
function checkSubmit() {
	// 判断当前用户有没有同意用户注册协议
	var isAgree = document.getElementById("agree");

	if (isAgree.checked == true && b1 && b2 && b3 && b4 && b5 && b6 && b7) {
		// 可以点击
		document.getElementById("submit").disabled = true;
		//alert("可以注册!");
//		window.location.href="/MyBookShop/UserCheck";
		alert("注册成功!");
		return true;
	} else {
		document.getElementById("submit").disabled = false;
		alert("请完善注册信息!");
		return false;
	}
}




