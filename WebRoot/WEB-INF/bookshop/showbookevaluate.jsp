<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书评价</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" href="css/common.css"/>
	<link rel="stylesheet" href="css/icons.css" />
	<link rel="stylesheet" href="css/table.css" />
	<link rel="stylesheet" href="css/orderSure.css" />
	
	<style type="text/css">
		a:link{
			text-decoration:none;/*用于去除下划线*/
		}
		a:hover{
			color: red;
		}
		a:active{
			color:yellow ;
		}
	</style>
	
	<script type="text/javascript" src="js/showbookevaluate.js"></script>

  </head>
  
  <body>
  		 <!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="/MyBookShop/ToIndex?type=exit">安全退出</a>|</li>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="/MyBookShop/ToIndex"><img src="images/logo.png"  class="oneImg"></a>
  	
  	<div align="center">
    	<br><br><br>
    	<span style="color: #C5000B; font-size: 24px;">全部评价(${size } 条)</span>
    </div>
    
    <div align="left">
    	<a href="javascript:void(0)" onclick="checkBook(${book.id})"><img alt="" style="width: 160px; height: 190px;" src="${book.feature_images }"></a>
    	<a href="javascript:void(0)" onclick="checkBook(${book.id})"><span style="margin-left: 210px; color: #00744A; font-size: 14px;">${book.name }</span></a><br>
    	<br>
    	<span style="margin-left: 210px; color: #00744A; font-size: 14px;">图书编号 : ${book.ISBN }</span>
    </div>
    
    <c:forEach items="${evaluates }" var="evaluate">
    
    	<c:if test="${evaluate.product_version == 2 }">
    		<c:set scope="page" var="book_version" value="简装版"></c:set>
    	</c:if>
    	
    	<c:if test="${evaluate.product_version == 1 }">
    		<c:set scope="page" var="book_version" value="精装版"></c:set>
    	</c:if>
    	
    	<c:if test="${evaluate.product_version == 3 }">
    		<c:set scope="page" var="book_version" value="收藏版"></c:set>
    	</c:if>
    
    <div style="margin-left: 200px;padding: 20px;;border: 0.5px solid #7EB4EA; margin-top: 20px; color: blue;">
    	<div>
    		<span style="color: blue">${evaluate.user.name }</span><br><br>
    	</div>
    	<div style="border: 0.5px solid #eee; padding: 10px;"align="center">
    		<span style="color: black;">${evaluate.content }</span>
    		<br>
    	</div>
    	<br><br>
    	<span>版本 : 【${book_version }】</span>
    	<div align="center">
    		<span style="color: C5131E"> ❤ 满意度评分   ${evaluate.state }  ❤ </span>
    	</div>
    	<div align="right">
    		<span>${evaluate.eva_date }</span>
    	</div>
    </div>
    
    </c:forEach>
    
  </body>
</html>
