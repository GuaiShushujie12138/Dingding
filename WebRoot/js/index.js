/**
 * 
 */

//鼠标移入
function over(div) {
	//获取所有div
	var divs = document.getElementsByName("show_search");
	
	for (var i = 0; i < divs.length; i ++) {
		divs[i].className = "";
	}
	
	div.className = "show_search_div";
}

//鼠标移出
function out(div) {
	div.className = "";
}

//点击选中的条目
function chooseA(div) {
	document.getElementById("search_input").value = div.innerHTML;
}

//点击搜索按钮
function search() {
	//获取文本框中的内容
	var text = document.getElementById("search_input").value;
	var book1 = document.getElementById("show_search_1").innerHTML;
	var book2 = document.getElementById("show_search_2").innerHTML;
	var book3 = document.getElementById("show_search_3").innerHTML;
	
//	alert("书名 : " + text);
	
	if (text == null || text == "") {
		alert("请输入要搜索的书名");
	} else {
		if (text != book1 && text != book2 && text != book3) {
			alert("抱歉,搜索的书名不存在");
		} else {
			window.location.href = "/MyBookShop/SearchDeal?type=jump&bookname=" + text;
		}
	}
}

//搜索框keyup事件
function searchBook() {
	//获取文本框中的内容
	var text = document.getElementById("search_input").value.trim();
//	alert("书名 : " + text);
	
	//通过ajax异步获取数据
	var ajax = new XMLHttpRequest();
	
	ajax.open("get", "/MyBookShop/SearchDeal?type=ajax&str=" + text, true);
	ajax.send();
	
	ajax.onreadystatechange = function() {
		
		if (ajax.readyState == 4 && ajax.status == 200) {
			var booknames = ajax.responseText;
//			alert(booknames);
			if (booknames != null && booknames != "" && booknames != "undefined") {
				var books = booknames.split(",");
				if (books != null && books.length > 0) {
					for (var i = 1; i <= books.length; i ++) {
						document.getElementById("show_search_" + i).innerHTML = books[i - 1];
					}
				}
			}
		}
	};
}

//鼠标聚焦事件
function show() {
	document.getElementById("serarch_div").style.display = "block";
}

//鼠标失焦事件
function hide() {
	setTimeout("hidden()", 500);
}

//鼠标移出事件
function leave() {
	setTimeout("hidden()", 500);	
}

function hidden() {
//	alert("进来了");
	document.getElementById("serarch_div").style.display = "none";
}

var index = 0;
//轮播
window.onload = function() {
	setInterval("turn()", 1500);
};

//换
function turn() {
	index ++;
	switch (index) {
	case 1:
		document.getElementById("turn_1").style.display = "none";
		document.getElementById("turn_2").style.display = "block";
		document.getElementById("turn_3").style.display = "none";
		break;
	case 2:
		document.getElementById("turn_1").style.display = "none";
		document.getElementById("turn_2").style.display = "none";
		document.getElementById("turn_3").style.display = "block";
		break;
	case 3:
		document.getElementById("turn_1").style.display = "block";
		document.getElementById("turn_2").style.display = "none";
		document.getElementById("turn_3").style.display = "none";
		index = 0;
		break;
	}
}







