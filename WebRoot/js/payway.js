/**
 * 对选择支付页面的操作
 */

//页面一加载就默认选中一个单选按钮
window.onload = function() {
	//获取页面上的所有单选按钮
	var radios = document.getElementsByName("radio");
	
	radios[0].checked = true;
};

//支付
function pay() {
	//判断用户选择的是哪个支付方式
	//获取页面上的所有单选按钮
	var radios = document.getElementsByName("radio");
	var payway;
	var order_id = document.getElementById("order_id").value;
	
	for (var i = 0; i < radios.length; i ++) {
		if (radios[i].checked) {
			payway = radios[i].value;
			break;
		}
	}
	
	//跳转到订单完成界面
	window.location.href = "/MyBookShop/ToConfirm?type=pay&payway=" + payway + "&order_id=" + order_id;
	
}

//对取消的按钮操作
function cancel() {
	var order_id = document.getElementById("order_id").value;
	//直接跳转到查看订单页面
	window.location.href = "/MyBookShop/ToShowOrder?type=single&order_id=" + order_id;
}
