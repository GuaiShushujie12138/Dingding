

/**
 * 对booklist页面中添加购物车的一些响应
 */

function addShopCar(bookId) {
//	alert(bookId);
	//判断用户是否已经登录,如果没有登录就跳到登陆界面,如果登录了就提示用户添加购物车成功了
	 var xmlHttp;
	    if (window.XMLHttpRequest) {
	    	xmlHttp = new XMLHttpRequest();
	    } else {
	    	//兼容ie5,6
	    	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    
	    //请求数据,判断用户是否登录
	    xmlHttp.open("get", "UserCheck?type=isLogin&bookId=" + bookId, true);
		xmlHttp.send();
	    
		//接收数据
		xmlHttp.onreadystatechange = function() {
			//当请求处理完成的时候,把数据显示出来
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var msg = eval("(" + xmlHttp.responseText + ")");
				if (msg.flag == true) {
					//说明用户登录了
					alert("添加购物车成功!");
				} else {
					window.location.href = "/MyBookShop/ToLogin";
				}
			}
		};
}

//对点击图书价格区间的操作
function clickRank(rank_id) {
	changeRank(rank_id);
	localStorage.setItem("rankMsg", rank_id);
	localStorage.removeItem("pageNow");
	//跳转
	ajax();
}

//对价格区间的样式改变
function changeRank(rank_id) {
	//获取所有的图书价格的a标签
	var ranks = document.getElementsByName("a_rank");
	var rank = document.getElementById("rank_" + rank_id);
	
	for (var i = 0; i < ranks.length; i ++) {
			ranks[i].style.background = "#ffffff";
			ranks[i].style.color = "#666666";
	}
	//再把当前点击了的a标签改变样式
	rank.style.background = "#e4393c";
	rank.style.color = "#ffffff";
	
	//显示上面的li
	document.getElementById("rank_li").style.display = "block";
	document.getElementById("span_rank").innerHTML = rank.innerHTML;
}

//对点击出版社的操作
function clickPublish(publish_id) {
	changePublish(publish_id);
	
	localStorage.setItem("publishMsg", publish_id);
	localStorage.removeItem("pageNow");
	//跳转
	ajax();
}

function changePublish(publish_id) {
	//获取所有的出版社的a标签
	var publishs = document.getElementsByName("a_publish");
	var publish = document.getElementById("publish_" + publish_id);
	
	for (var i = 0; i < publishs.length; i ++) {
			publishs[i].style.background = "#ffffff";
			publishs[i].style.color = "#666666";
	}
	
	//再把当前点击了的a标签改变样式
	publish.style.background = "#e4393c";
	publish.style.color = "#ffffff";
	
	//显示上面的li
	document.getElementById("publish_li").style.display = "block";
	document.getElementById("span_publish").innerHTML = publish.innerHTML;
}

//对图书价格li的删除
function cancelRank() {
	document.getElementById("rank_li").style.display = "none";
	
	var ranks = document.getElementsByName("a_rank");
	for (var i = 0; i < ranks.length; i ++) {
		ranks[i].style.background = "#ffffff";
		ranks[i].style.color = "#666666";
	}
	
	localStorage.removeItem("pageNow");
	localStorage.setItem("rankMsg", "");
	//跳转
	ajax();
}

//对出版社li的删除
function cancelPublish() {
	document.getElementById("publish_li").style.display = "none";
	
	var publishs = document.getElementsByName("a_publish");
	for (var i = 0; i < publishs.length; i ++) {
//		alert(i);
		publishs[i].style.background = "#ffffff";
		publishs[i].style.color = "#666666";
	}
	
	localStorage.removeItem("pageNow");
	localStorage.setItem("publishMsg", "");
	//跳转
	ajax();
}

//对排序点击的操作
function clickSort(sortId) {
	localStorage.removeItem("pageNow");
	//改变排序按钮的样式
	changeSort(sortId);
	
	localStorage.setItem("sortMsg", sortId);
	
	//跳转
	ajax();
}

//对排序按钮样式改变的方法
function changeSort(sortId) {
	//a标签
	var sorts = document.getElementsByName("sort");
	//包裹a标签的li标签
	var sorts_li = document.getElementsByName("sort_li");
	var sort = document.getElementById(sortId);
	var sort_li = document.getElementById(sortId + "_li");
	

	for (var i = 0; i < sorts.length; i ++) {
		sorts_li[i].style.background = "#ffffff";
		sorts[i].style.color = "#666666";
	}
	
	sort_li.style.background = "#e4393c";
	sort.style.color = "#ffffff";
}

//跳转页面,重新加载booklist的方法
function jump() {
	//分类类型
	var type = document.getElementById("type").value;
	var category_id = document.getElementById("category_id").value;
	var detail_id = document.getElementById("detail_id").value;

//	alert("跳转前sortMsg: " + localStorage.getItem("sortMsg"));
//	alert("跳转前rankMsg: " + localStorage.getItem("rankMsg"));
//	alert("跳转前publishMsg: " + localStorage.getItem("publishMsg"));
	window.location.href = "/MyBookShop/ToBookList?type=" + type + "&sort=" + localStorage.getItem("sortMsg") + "&category_id=" + category_id + "&detail_id=" + detail_id + "&rank=" + localStorage.getItem("rankMsg") + "&publish=" + localStorage.getItem("publishMsg") + "&pageNow=" + localStorage.getItem("pageNow");
}

//ajax异步交互
function ajax() {
	var xmlHttp;//创建对象
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		// 为了兼容ie5,ie6
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	//发送数据
	//准备请求
	//分类类型
	var type = document.getElementById("type").value;
	var category_id = document.getElementById("category_id").value;
	var detail_id = document.getElementById("detail_id").value;
	xmlHttp.open("get", "BookListDeal?type=" + type + "&sort=" + localStorage.getItem("sortMsg") + "&category_id=" + category_id + "&detail_id=" + detail_id + "&rank=" + localStorage.getItem("rankMsg") + "&publish=" + localStorage.getItem("publishMsg") + "&pageNow=" + localStorage.getItem("pageNow"), true);
	xmlHttp.send();

	//接收数据
	//把servlet中传递过来的json数据封装成对象
	xmlHttp.onreadystatechange = function() {
		var msg = xmlHttp.responseText;
		//当请求处理完成的时候,把数据显示出来
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			document.getElementById("list_bottom_div").innerHTML = msg;
		}
	};
}


//跳转到第几页
function jumpPage(page) {
	localStorage.setItem("pageNow", page);
	
	ajax();
	changeLi(page);
}

//用输入框跳转
function jumpPage2() {
	//获取输入框中的跳转页面数
	var jump_page = document.getElementById("input_page").value;
//	alert("jump_page : " + jump_page);
	
	//总页数
	var pageCount = document.getElementById("pageCount").value;
//	alert("pageCount : " + pageCount);
	
	if (jump_page > 0 &&  jump_page <= pageCount) {
		localStorage.setItem("pageNow", jump_page);
		jump();
		return true;
	} else {
		alert("输入的跳转页数不合法!");
		return false;
	}
	
	
}

//对分页鼠标移入的操作
function mouseOver(now) {
	var li = document.getElementById("li_sort_" + now);
	
	li.style.background = "#FA5000";
	li.style.color = "white";
}

//对分页鼠标移出的操作
function mouseOut(now) {
	var li = document.getElementById("li_sort_" + now);
	var liNow = document.getElementById("pageNow").value;

	//判断当前鼠标移出的不是当前页,如果是当前页就不改变样式
	if (now != liNow) {
		li.style.background = "#ffffff";
		li.style.color = "#666666";
	}
	
}


//改变li标签样式
function changeLi(li_id) {
	//获取所有li标签
	var lis = document.getElementsByName("li_sort");
	var li = document.getElementById("li_sort_" + li_id);
	
	for (var i = 0; i < lis.length; i ++) {
		//先把所有li标签背景和颜色清零
		lis[0].style.background = "#ffffff";
		lis[0].style.color = "#666666";
	}
	
	//再把当前点击了的li标签样式改变
	li.style.background = "#FA5000";
	li.style.color = "white";
}

//一加载网页就调用的方法
window.onload = function () {
	var b1 = false, b2 = false, b3 = false;
//	alert("进来了!");
	var sort = document.getElementById("sortName").value;
//	alert("sort" + sort);
	if (sort == "null" || sort == "" || sort == null) {
		//为空就不调用方法
		b1 = true;
	} else {
		//调用改变样式的方法
		//changeSort(sort);
	}
	
	var publish = document.getElementById("publishName").value;
//	alert("publish" + publish);
	if (publish == "null" || publish == "" || publish == null) {
		//为空就不调用方法
		b2 = true;
	} else {
		//调用改变样式的方法
		//changePublish(publish);
	}
	
	var rank = document.getElementById("rankName").value;
//	alert("rank" + rank);
	if (rank == "null" || rank == "" || rank == null) {
		//为空就不调用方法
		b3 = true;
	} else {
		//调用改变样式的方法
		//changeRank(rank);
	}
	
	//改变当前页li标签的样式
	var li = document.getElementById("pageNow").value;
	changeLi(li);
	
	localStorage.removeItem("pageNow");
	
	if (b1 && b2 && b3) {
		localStorage.clear();
	}
};



