/**
 * 对图书详情页的js操作
 */

var version = 2;//表示当前用户选择的图书版本,2代表简装版,1代表精装版,3代表收藏版

//对添加购物车按钮的操作
function addShopCar(bookId) {
	//用户选择了多少数量的商品
	var bookNum = document.getElementById("bookNum").value;
	
	var xmlHttp;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		//兼容ie5,6
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	//请求数据,判断用户是否登录成功,跳转到相应的页面
//	alert(version);
	xmlHttp.open("get", "BookDeal?type=addBook&bookId=" + bookId + "&version=" + version + "&bookNum=" + bookNum , true);
	xmlHttp.send();
	
	//接收数据
	xmlHttp.onreadystatechange = function() {
		//当请求处理完成的时候,把数据显示出来
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var msg = eval("(" + xmlHttp.responseText + ")");
			if (msg.flag == true) {
				//用户登录了,添加购物车,跳转至购物车页面
				window.location.href = "/MyBookShop/ToShopCar";
			} else {
				//说明用户没有登录,直接跳转登陆界面
				window.location.href = "/MyBookShop/ToLogin";
			}
		}
	};
}

var click = "jzb";

//对用户点击不同版本做出不同响应
function choose(book_type, bookId) {
	click = book_type;
	var jzb = document.getElementById("jzb");
	var jin = document.getElementById("jin");
	var scb = document.getElementById("scb");
	var price = document.getElementById("price");
	
	if (book_type == "jzb") {
		jzb.style.background = "#C5131E";
		jin.style.background = "white";
		scb.style.background = "white";
		
		version = 2;//更改版本
		
		jzb.style.color = "white";
		jin.style.color = "black";
		scb.style.color = "black";
	} else if (book_type == "jin") {
		
		jzb.style.background = "white";
		jin.style.background = "#C5131E";
		scb.style.background = "white";
		
		version = 1;//更改版本
		
		jzb.style.color = "black";
		jin.style.color = "white";
		scb.style.color = "black";
	} else if (book_type == "scb") {
		jzb.style.background = "white";
		jin.style.background = "white";
		scb.style.background = "#C5131E";
		
		version = 3;//更改版本
		
		jzb.style.color = "black";
		jin.style.color = "black";
		scb.style.color = "white";
	} 
	
	var xmlHttp;
    if (window.XMLHttpRequest) {
    	xmlHttp = new XMLHttpRequest();
    } else {
    	//兼容ie5,6
    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    //请求数据,获取当前版本号的图书的价格
    xmlHttp.open("get", "BookDeal?type=getVersionPrice&version=" + version + "&bookId=" + bookId, true);
	xmlHttp.send();
    
	//接收数据
	xmlHttp.onreadystatechange = function() {
		//当请求处理完成的时候,把数据显示出来
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var msg = eval("(" + xmlHttp.responseText + ")");
			price.innerHTML = "￥" + msg.price;
		}
	};
	
}

//更新数量
function updateBook(type) {
	
	var bookNum = document.getElementById("bookNum").value;
	
	if (type == "add") {
		//如果是增加数量
		bookNum ++;
	} else if (type == "des") {
		//如果是减少数量
		if (bookNum <= 1) {
			bookNum = 1;
		} else {
			bookNum --;
		}
	}
	
	document.getElementById("bookNum").value = bookNum;
}

//对用户点击不同的选项卡做出不同的反应
function mouseOver(tab) {
	//获取所有选项卡
	var tabs = document.getElementsByName("tab_li");
	//获取所有选项卡对应的显示div
	var divs = document.getElementsByName("tab_div");
	
	//先把所有的选项卡样式清空,把所有的div显示为不可见
	for (var i = 0; i < tabs.length; i ++) {
		tabs[i].className = "";//styleclass为新的属性值  
		tabs[i].setAttribute("class","");  
		divs[i].style.display = "none";
	}
	
	//再把当前点击了的赋值样式和显示
	document.getElementById(tab).className = "current";
	document.getElementById(tab).setAttribute("class","current"); 
	document.getElementById(tab + "_div").style.display = "block";
	
}

//对用户鼠标移入的操作
function versionOver(book_type) {
	var jzb = document.getElementById("jzb");
	var jin = document.getElementById("jin");
	var scb = document.getElementById("scb");
	
	var curr = document.getElementById(click);
	
	if (book_type == "jzb") {
		jzb.style.background = "#C5131E";
		jin.style.background = "white";
		scb.style.background = "white";
		
		jzb.style.color = "white";
		jin.style.color = "black";
		scb.style.color = "black";
	} else if (book_type == "jin") {
		
		jzb.style.background = "white";
		jin.style.background = "#C5131E";
		scb.style.background = "white";
		
		jzb.style.color = "black";
		jin.style.color = "white";
		scb.style.color = "black";
	} else if (book_type == "scb") {
		jzb.style.background = "white";
		jin.style.background = "white";
		scb.style.background = "#C5131E";
		
		jzb.style.color = "black";
		jin.style.color = "black";
		scb.style.color = "white";
	} 
	
	curr.style.background = "#C5131E";
	curr.style.color = "white";
}


//对用户鼠标移出的操作
function versionOut(book_type) {
	var jzb = document.getElementById("jzb");
	var jin = document.getElementById("jin");
	var scb = document.getElementById("scb");
	
	var curr = document.getElementById(click);
	
	if (book_type == "jzb") {
		jzb.style.background = "white";
		
		
		jzb.style.color = "black";
		
	} else if (book_type == "jin") {
		jin.style.background = "white";
		
		jin.style.color = "black";
	} else if (book_type == "scb") {
		scb.style.background = "white";
		
		scb.style.color = "black";
	} 
	
	curr.style.background = "#C5131E";
	curr.style.color = "white";
}










