/**
 * 对confirm.jsp页面的操作
 */
var b1, b2, b3;

//提交订单的响应
function submit() {
	//获取总价
	var totalPrice = document.getElementById("totalPrice").value;
	//获取要结算的图书的id
	var bookIds = document.getElementById("bookIds").value;
	
	//获取收件人的信息,名字,地址,电话
	var name = document.getElementById("name").value;
	var address = document.getElementById("address").value;
	var tel = document.getElementById("tel").value;
	
	//判断用户有没有完成收货信息的填写
	if (b1 && b2 && b3) {
		//跳转到选择付款方式的页面
		window.location.href = "/MyBookShop/ToConfirm?type=submit&totalPrice=" + totalPrice + "&bookIds=" + bookIds + "&name=" + name + "&address=" + address + "&tel=" + tel;
	} else {
		alert("请完整的填写收货人信息");
	}
}

//验证用户名
function checkName(){
    var name=document.getElementById("name").value.trim();
    var nameRegex=/^[^@#]{3,16}$/;
    if(!nameRegex.test(name)){
    	document.getElementById("namediv").style.display="block";
        document.getElementById("nameInfo").innerHTML="用户名为3~16个字符，且不能包含”@”和”#”字符";
        b1 = false;
    }else{
    	document.getElementById("namediv").style.display="none";
    	document.getElementById("nameInfo").innerHTML="";
        b1 = true;
    }
}

//验证地址
function checkAddress(){
    var address=document.getElementById("address").value.trim();
    
    if(address == null || "" == address){
    	document.getElementById("address_div").style.display="block";
        document.getElementById("addressInfo").innerHTML="地址不能为空!";
        b2 = false;
    }else {
    	document.getElementById("address_div").style.display="none";
    	document.getElementById("addressInfo").innerHTML="";
        b2 = true;
    }
}

//验证电话
function checkTel(){
    var address=document.getElementById("tel").value.trim();
    var telRegex= /^(13[0-9]{9})|(15[89][0-9]{8})|(18[67][0-9]{8})|(17[6][0-9]{8})$/
    
    if(!telRegex.test(address)){
    	document.getElementById("tel_div").style.display="block";
        document.getElementById("telInfo").innerHTML="手机号码格式错误!";
        b3 = false;
    }else if (telRegex.test(address)) {
    	document.getElementById("tel_div").style.display="none";
    	document.getElementById("telInfo").innerHTML="";
        b3 = true;
    }
}
	

