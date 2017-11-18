/**
 * 
 */

/*删除订单*/
function deleteOrder(order_id) {
	window.location.href = "/MyBookShop/OrderDeal?type=delete&order_id=" + order_id;
}

/*点击图书图片,跳转到图书详情页*/
function clickBook(bookId) {
	window.location.href = "/MyBookShop/ToBookView?bookId=" + bookId;
}

/*支付*/
function pay(order_id, totalPrice) {
//	alert("aaaa");
	window.location.href = "/MyBookShop/ToPayWay?order_id=" + order_id + "&totalPrice=" + totalPrice;
}

/*评价*/
function book_evaluate(order_id) {
//	alert("进来了!");
	window.location.href = "/MyBookShop/ToEvaluateBook?order_id=" + order_id;
}

/*查看评价*/
function checkEvaluate(order_id) {
	window.location.href = "/MyBookShop/ToShowMyEvaluate?type=single&order_id=" + order_id;
}

//测试
function test(onclick) {
	alert(onclick);
}



