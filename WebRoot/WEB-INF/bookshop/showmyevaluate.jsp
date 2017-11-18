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
    
    <title>我的商品评价</title>
    
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
		span{
			font-size: 13px;
			color: #2C3B67;
		}
		a:link{
			color: blue;
			text-decoration:none;/*用于去除下划线*/
		}
		a:hover{
			color: red;
		}
		a:active{
			color:yellow ;
		}
		
	</style>
	
	<script type="text/javascript" src="js/showmyevaluate.js"></script>

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
    	<span style="color: #C5000B; font-size: 28px;">我的评价</span>
    	<c:if test="${param.type.equals('single') }">
    		<a href="javascript:void(0)" onclick="checkAll()"><span style="color: #50A74B; font-size: 14px;">查看全部评价</span></a>
    	</c:if>
  	</div>
  	

		<c:forEach items="${evaluates }" var="evaluate">
		<!-- 在page域中设置变量,以供java代码使用 -->
		<c:set var="version" value="${evaluate.product_version}" scope="page"></c:set>
		
			<%	
				int version = (int) pageContext.getAttribute("version");
				
				String book_version = "";
				
				switch(version) {
				case 1:
					book_version = "精装版";
					break;
				case 2:
					book_version = "简装版";
					break;
				case 3:
					book_version = "收藏版";
					break;
				}
			 %>
			
			<div style="margin-left: 160px; border:0.5px solid #F75521 ;margin-top: 30px; padding: 15px;">
				<div>
					<br>
					<div style="float: left;">
						<a href="javascript:void(0)" onclick="checkBook(${evaluate.product.id})"><img style="width: 80px; height: 100px" alt=""
							src="${evaluate.product.feature_images }"></a>
					</div>
					<div style="float: left; margin-left: 20px">
						<a href="javascript:void(0)" onclick="checkBook(${evaluate.product.id})"><span>${evaluate.product.name } 【<%=book_version %>】</span></a><br>
					 	<span>图书编号 : ${evaluate.product.ISBN }</span><br>
						<a href="javascript:void(0)" onclick="toBriup()"><span style="display: inline-block;">briup自营 </span></a> <br>
						<br>
						<span style="color: #C5000B">❤❤❤ 评分: ${evaluate.state } 分 ❤❤❤</span>
					</div>
				</div>
				<br>
				<div style="padding: 10px; border:1px solid #ccc ;width: 620px;height:50px; margin-left: 300px;" align="left">
					<p style="font-size: 14px;color:#000;">
						${evaluate.content }
					</p>
				</div>
				
				<div align="right">
				<br>
					<span>${evaluate.eva_date }</span><br><br>
					<a href="javascript:void(0)" onclick="deleteEvaluate(${evaluate.id})"><span style="color: red">删除评价</span></a>
				</div>
				
			</div>				
	
		</c:forEach>

		<br><br>
		
 <!--脚部-->
    <div class="footer3">
    	<div class="f3_top">
        	<div class="f3_center">
                <div class="ts1">品目繁多 愉悦购物</div>
                <div class="ts2">正品保障 天天低价</div>
                <div class="ts3">极速物流 特色定制</div>
                <div class="ts4">优质服务 以客为尊</div>
            </div>
        </div>
        <div class="f3_middle">
        	<ul class="f3_center">
            	<li class="f3_mi_li01">
                	<h1>购物指南</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>配送方式</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>支付方式</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>售后服务</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li01">
                	<h1>服务保障</h1>
                    <p>常见问题</p>
                    <p>找回密码</p>
                    <p>会员介绍</p>
                    <p>购物演示</p>
                </li>
                <li class="f3_mi_li06">
                	<h1>客服中心</h1>
                    <img src="images/qrcode_jprj.jpg" width="80px" height="80px">
                    <p>抢红包、疑问咨询、优惠活动</p>
                </li>
            </ul>
        </div>
         <div class="f3_bottom">
        	<p class="f3_links">
                <a href="#">关于我们</a>|
                <a href="#">联系我们</a>|
                <a href="#">友情链接</a>|
                <a href="#">供货商入驻</a> 
           	</p>
            <p>沪ICP备14033591号-8 杰普软件briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>
  </body>
</html>
