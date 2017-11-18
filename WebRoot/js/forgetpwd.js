/**
 * 对找回密码一系列操作的验证
 */
var b1 = false, b2 = false;//在第一个忘记密码页面的判断标识

//验证用户名
var name;
function checkUserName(){
    name=document.getElementById("J_euserName").value.trim();
    var nameRegex=/^[^@#]{3,16}$/;
    if(!nameRegex.test(name)){
    	document.getElementById("namediv").style.display="block";
        document.getElementById("nameInfo").innerHTML="用户名为3~16个字符，且不能包含”@”和”#”字符";
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
		//当请求处理完成的时候,把数据显示出来
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200 && b1) {
			var msg = eval("(" + xmlHttp.responseText + ")");
			if (msg.flag == true) {
				//说明该用户名不存在,不能够修改密码
				document.getElementById("namediv").style.display="block";
		        document.getElementById("nameInfo").innerHTML="用户名不存在!";
		        b1 = false;
			} else {
				document.getElementById("nameInfo").innerHTML="";
		        document.getElementById("namediv").style.display="none";
		        b1 = true;
			}
		}
	};
}

//更换验证码图片
function changeCheckCode() {
	window.location.href = "/MyBookShop/ToForgetPwd?username=" + name;
}

//检查用户的验证码是否填写正确
function checkCode() {
	var checkcode = document.getElementById("J_eUserNameCode").value.trim();
	//判断验证码是否正确
	 var xmlHttp;
	    if (window.XMLHttpRequest) {
	    	xmlHttp = new XMLHttpRequest();
	    } else {
	    	//兼容ie5,6
	    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    
	    //请求数据,判断验证码是否填写正确
	    xmlHttp.open("get", "UserCheck?type=checkcode&checkcode=" + checkcode, true);
		xmlHttp.send();
	    
		//接收数据
		xmlHttp.onreadystatechange = function() {
			//当请求处理完成的时候,把数据显示出来
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var msg = eval("(" + xmlHttp.responseText + ")");
				if (msg.flag == true) {
					//说明验证码正确
			        b2 = true;
				} else {
					document.getElementById("checkcodeInfo").innerHTML="验证码填写错误!";
			        document.getElementById("checkcode_div").style.display="block";
			        b2 = false;
				}
			}
		};
}


//判断用户当前是否可以进行第一个下一步
function checkStep1() {
	if (b1 && b2) {
		return true;
	} else {
		alert("请正确完成上述步骤!");
		return false;
	}
}



