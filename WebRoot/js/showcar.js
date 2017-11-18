/**
 * 对购物车页面的一些操作
 */

var checkNum = 0;//记录当前被选中的商品的数量

var totalPrice;
//复选框选中或者取消的事件,用于更新购物车已选中商品的总价
function choose(target, bookId, version) {
	//获取当前总价钱
	var totalPrice1 = document.getElementById("totalPrice").innerHTML;
	//分割
	var totalPrices = totalPrice1.split("¥");
	totalPrice = Number(totalPrices[1]);
	
//	alert(totalPrice);
	//获取当前选择的那一行商品的总价
	var price = document.getElementById("price_" + bookId + "_" + version).innerHTML;
	//分割
	var prices = price.split("¥");
	price = Number(prices[1]);
	
	//判断当前复选框的选中状态
	if (target.checked == true) {
		//说明当前复选框被选中了
		//更新数据
		checkNum ++;
		totalPrice = totalPrice + price;
		
	} else if (target.checked == false) {
		//说明当前复选框被取消选中了
		//更新数据
		checkNum --;
		totalPrice = totalPrice - price;
	}
	
	document.getElementById("productNum").innerHTML = checkNum;
	if (checkNum == 0) {
		//如果未选中任何商品,0元
		document.getElementById("totalPrice").innerHTML = "¥0.00";
	} else {
		document.getElementById("totalPrice").innerHTML = "¥" + totalPrice;
	}
	
}

//更新图书的方法
function updateBook(bookId, type, version) {
//	alert("bookId : " + bookId);
	
	
	var bookNum = document.getElementById("book_" + bookId + "_" + version).value;
	var bookNum1;
	
	//获取当前书的单价
	var price1 = document.getElementById("single_price_" + bookId + "_" + version);
	var price = price1.innerHTML;
	//分割
	var prices = price.split("¥");
	price = Number(prices[1]);
	
	if (type == "des") {
		//说明是减少图书
		bookNum1 = Number(bookNum) - 1;
		totalPrice = totalPrice - price;
	} else if (type == "add") {
		//增加图书
		bookNum1 = Number(bookNum) + 1;
		totalPrice = totalPrice + price;
	}
	
	//判断当前有没有选中,如果选中了就更新数据
	var check = document.getElementById("check_" + bookId + "_" + version);
	if (check.checked) {
		//更新数据
		document.getElementById("totalPrice").innerHTML = "¥" + totalPrice;
//		alert("aaa");
	} else {
//		alert("bbb");
	}
//	if (document.getElementById("check_" + bookId).checked == true) {
//		//更新数据
//		document.getElementById("totalPrice").innerHTML = "¥" + totalPrice;
//		alert("aaa");
//	} else {
//		alert("ooo");
//	}
	
//	alert(bookNum1);
	var xmlHttp; 
    if (window.XMLHttpRequest) {
    	xmlHttp = new XMLHttpRequest();
    } else {
    	//兼容ie5,6
    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    //发送数据
    xmlHttp.open("get", "BookDeal?type=update&bookId=" + bookId + "&bookNum=" + bookNum1 + "&version=" + version, true);
	xmlHttp.send();
	
	//接收数据
	xmlHttp.onreadystatechange = function() {
		//当请求处理完成的时候,把数据显示出来
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
//			alert(book_num);
//			var book_price = document.getElementById("price_" + bookId).innerHTML;
//			alert(book_price);
			var msg = eval("(" + xmlHttp.responseText + ")");
			//把得到的单个订单项总价显示在页面上
			document.getElementById("price_" + bookId + "_" + version).innerHTML = "¥" + msg.totalPrice;
//			alert("msg.totalPrice : " + msg.totalPrice);
			document.getElementById("book_" + bookId + "_" + version).value = bookNum1;
//			alert("bookNum1 : " + bookNum1);
		}
	};
	
	if (bookNum1 == 0) {
		setTimeout('refresh()', 10);
	}
	
//	window.location.href = "/MyBookShop/ToShopCar";
	
//	setTimeout('refresh()', 2);
}

//刷新页面的方法
function refresh() {
	window.location.reload(true);
}


//删除图书的方法
function deleteBook(bookId, version) {
	
	var xmlHttp;
    if (window.XMLHttpRequest) {
    	xmlHttp = new XMLHttpRequest();
    } else {
    	//兼容ie5,6
    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    //发送数据
    xmlHttp.open("get", "BookDeal?type=delete&bookId=" + bookId + "&version=" + version, true);
	xmlHttp.send();
	
	setTimeout('refresh()', 2);
}

//购物车中为空的时候,点击图片进行购买,跳转到首页
function buy() {
	window.location.href = "/MyBookShop/ToIndex";
}

//对结算按钮的操作
function count() {
	//根据名字获取所有的checkbox
	var checkboxs = document.getElementsByName("checkbox");
	var bookIds = "";
	var temp = "";
	
	for (var i = 0; i < checkboxs.length; i ++) {
		if (checkboxs[i].checked == true) {
			temp = checkboxs[i].value;
			if (bookIds == "") {
				bookIds = temp;
			} else {
				bookIds = bookIds + "," + temp;
			}
		} 
	}
	
	//取出所有的bookId
	if (bookIds == "") {
		//说明当前没有选中任何的商品,不可以结算
		alert("没有选择图书!");
		return false;
	} else {
		//直接跳转到servlet去准备在结算页面的数据
		window.location.href = "/MyBookShop/ToConfirm?type=count&bookIds=" + bookIds;
	}
	
}

//对全选复选框的操作
function checkAll() {
	var check = document.getElementById("checkAll");
	//获取当前所有的复选框
	var checkboxs = document.getElementsByName("checkbox");
	
	if (check.checked) {
		//如果当前是全选
		//把所有复选框状态改成选中
		for (var i = 0; i < checkboxs.length; i ++) {
			checkboxs[i].checked = true;
		}
		
		//把价格改掉
		var spans = document.getElementsByName("single_price_span");
		var p = 0;
		for (var i = 0; i < spans.length; i ++) {
			console.log("进来了!");
			var a = spans[i].innerHTML.split("¥");
			var b = a[1] - 0 + 0;
			console.log(b);
			p = p + b;
		}
		
		document.getElementById("totalPrice").innerHTML = "¥" + p;
		document.getElementById("productNum").innerHTML = spans.length;
		checkNum = spans.length;
		totalPrice = p;
		
	} else {
		//把所有复选框状态改成未选中
		for (var i = 0; i < checkboxs.length; i ++) {
			checkboxs[i].checked = false;
		}
		
		//把价格置为0
		document.getElementById("totalPrice").innerHTML = "¥0.00";
		document.getElementById("productNum").innerHTML = 0;
		checkNum = 0;
		totalPrice = 0;
	}
}











