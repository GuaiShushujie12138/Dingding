/**
 * 
 */
//查看所有评价
function checkAll() {
	window.location.href = "/MyBookShop/ToShowMyEvaluate?type=all";
}

//查看图书
function checkBook(bookId) {
	window.location.href = "/MyBookShop/ToBookView?bookId=" + bookId;
}

//删除评价
function deleteEvaluate(evaluate_id) {
	window.location.href = "/MyBookShop/EvaluateDeal?type=deleteEvaluate&evaluate_id=" + evaluate_id;
}