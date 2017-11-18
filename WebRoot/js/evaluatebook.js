/**
 * 评价商品的js
 */

//提交评论
function submitEvaluate() {
	//取出所有的textarea
	var areas = document.getElementsByTagName("textarea");
	var flag = true;//记录能否提交
	var flag2 = true;//记录评分
	
	//遍历,如果有为空的评价就不让提交
	for (var i = 0; i < areas.length; i ++) {
		if (areas[i].value == null || areas[i].value == "") {
			flag = false;
			break;
		}
	}
	
	//获取评分
	var ratys = document.getElementsByName("raty_score");
	for (var i = 0; i < ratys.length; i ++) {
//		alert(" value :" + ratys[i].value);
		if (ratys[i].value == null || ratys[i].value == "") {
			flag2 = false;
			break;
		}
	}
	
	if (!flag) {
		alert("请完成评价!");
		return false;
	} else if (!flag2) {
		alert("请完成评分!");
		return false;
	} else {
		//说明可以提交评论
		return true;
	}
	
}

//取消评价
function cancelEvaluate() {
	window.location.href = "/MyBookShop/ToShowOrder?type=all";
}


//鼠标移入提交评论
function over() {
	document.getElementById("submit").style.background = "#E60012";
}

//鼠标移出提交评论的事件
function out() {
	document.getElementById("submit").style.background = "#CF0052";
}

//鼠标移入取消
function over1() {
	document.getElementById("cancel").style.background = "#E60012";
}

//鼠标移出取消的事件
function out1() {
	document.getElementById("cancel").style.background = "#CF0052";
}