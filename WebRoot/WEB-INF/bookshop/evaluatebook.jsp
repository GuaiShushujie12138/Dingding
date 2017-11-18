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
    
    <title>评价</title>
    
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
	<link rel="stylesheet" href="css/amstars.css"/>
	
	<style type="text/css">
		span{
			font-size: 14px;
			color: #2C3B67;
		}
	</style>
	
	<script type="text/javascript" src="js/amstars.js"></script>
	<script type="text/javascript" src="js/evaluatebook.js"></script>

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
    	<span style="color: #C5000B; font-size: 30px;">商品评价</span>
  	</div>
  	
  	<form action="/MyBookShop/EvaluateDeal" method="post">
  	<br><br>

		<%
			int index = 0;
		 %>

		<c:forEach items="${orderLines }" var="line">
		<!-- 在page域中设置变量,以供java代码使用 -->
		<c:set var="version" value="${line.product_version}" scope="page"></c:set>
		
			<%	
				//Integer.parseInt(${line.product_version}.toString());
				int version = (int) pageContext.getAttribute("version");
				
				String book_version = "";
				index ++;
				pageContext.setAttribute("index", index);
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
			
			<div style="margin-left: 160px;">
				<div>
					<div style="float: left;">
						<img style="width: 200px; height: 240px" alt=""
							src="${line.product.feature_images }">
					</div>
					<div style="float: left; margin-left: 20px">
						<span>${line.product.name } 【<%=book_version %>】</span><br>
						<br> <span>图书编号 : ${line.product.ISBN }</span><br>
						<br> <span style="display: inline-block;">briup自营 </span> <br>
						<br>
					</div>
				</div>

				<br>
				<br>
				<br>
				<br>
				
				<input type="hidden" value="${line.product.id }" name="book_id"/>
				<input type="hidden" value="${line.product_version }" name="book_version"/>
				<input type="hidden" name="raty_score" id="raty_${index}"/>
				
				<div align="center" style="margin-right: 240px;">
					<span style="color: #CF0052">填写评价</span>
				</div>

				<div style="margin-left: 60px;">
					<textarea name="mytextarea" style="resize: none;" rows="5" cols="120"
						maxlength="300"></textarea>
				</div>
				
				<div class="amstars_show"> 满意度评分： <a class="amstars" href="javascript:void(0)"> <font class="amstars_val" name="92.11" style="width: 92.11px;"> </font> <span></span> </a> </div>
				
				<br>
				<br>
				<br>
				<br>
				<br>
			</div>
	
		</c:forEach>



		<br><br>
		
	<!-- 添加评价 -->
	<input type="hidden" name="type" value="addEvaluate"/>
	<input type="hidden" name="order_id" value="${param.order_id }"/>
	
  	<div align="right">
  		<!-- <ul style="display: inline-block;" class="pro_select_vals">
  			<a href="javascript:void(0)" onclick="submitEvaluate()" onmouseover="over()" onmouseout="out()"><li id="submit" style="background: #CF0052; color: white; font-size: 16px; padding: 13px;">提交评论<li></a>
  		</ul> -->
  		
  		<div align="right" id="submit" style="padding: 12px; cursor:pointer ; width:66px;  background: #CF0052;border:1px solid #cb2a2d;
			margin:24px 0;">
			<input type="submit" onclick="return submitEvaluate()" onmouseover="over()" onmouseout="out()" value="提交评价" style="color: white; border:none;  background: none; font-size: 16px; cursor:pointer ;"/>
		</div>
  		
  		<ul style="display: inline-block;" class="pro_select_vals">
  			<a href="javascript:void(0)" onclick="cancelEvaluate()" onmouseover="over1()" onmouseout="out1()"><li id="cancel" style="background: #CF0052; color: white; font-size: 16px; padding: 13px;">取消评价<li></a>
  		</ul>
  	</div>
  	
  	</form>
  	
  
  
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
