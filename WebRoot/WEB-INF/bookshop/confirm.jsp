<%@ page language="java" import="java.util.*, com.guaishushu.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>结算,确认订单页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/confirm.js"></script>
	<link rel="stylesheet" href="css/common.css"/>
	<link rel="stylesheet" href="css/icons.css" />
	<link rel="stylesheet" href="css/table.css" />
	<link rel="stylesheet" href="css/orderSure.css" />
	
  </head>
  
  <body>
  		<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="/MyBookShop/ToIndex?type=exit">安全退出</a>|</li>
                <li><a href="/MyBookShop/ToShowOrder?type=all">我的订单<span class="jt_down"></span></a>|</li>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="/MyBookShop/ToIndex"><img src="images/logo.png"  class="oneImg"></a>
    	

        <div class="h3_right">
        	<img src="images/play_04.png" alt="">
        </div>
       
    </div>
	<p class="orderButtom">填写并核对订单信息</p>
    <!--中间复杂部分-->
    <div class="content">
    	<div class="contentCenter">
    		<div class="centerTop" align="center">
    			<b style="font-size:20px;">收货人信息</b>
    			<p style="font-size:15px;">
					收件人姓名：<input type="text" id="name" onblur="checkName()"> 
					<div id="namediv" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="nameimg" src="css/easyUI/icons/no.png">
                    </div>
                    <h5 id="nameInfo"></h5>
					<br/>
					
					<p style="font-size:15px;">
					收件人地址：<input type="text" id="address" onblur="checkAddress()" class="">
					<div id="address_div" style="background: none; border-style: none;display: none">
                    	<img id="address_img" src="css/easyUI/icons/no.png">
                    </div>
                    <h5 id="addressInfo" style="margin-top: 8px;"></h5>
					<br/>
					
					<p style="font-size:15px;">
					收件人电话：<input type="text" id="tel" onblur="checkTel()" />
					<div id="tel_div" style="background: none; border-style: none;display: none">
                    	<img id="tel_img" src="css/easyUI/icons/no.png">
                    </div>
                    <h5 id="telInfo"></h5>
                    
    		</div>
    		<p class="singleP"><b>送货清单</b><span><a href="/MyBookShop/ToShopCar">返回修改购物车</a></span></p>
    		<div class="bigDiv">
    			<div class="twoDiv">
    				<b>商家：briup自营</b>
    				<%
    					//获取当前页面的图书id信息
    					String bookIds = (String) request.getAttribute("bookIds");
    					//获取订单项
    					Set<OrderLine> orderLines = (Set<OrderLine>) request.getAttribute("orderLines");
    					double totalPrice = 0.00;
    					
    					if (orderLines != null && orderLines.size() > 0) {
    						Iterator<OrderLine> it = orderLines.iterator();
    						while (it.hasNext()) {
    							OrderLine orderLine = it.next();
    							Product book = orderLine.getProduct();
    							CategoryDetail detail = book.getCate_detail();
    							Category category = detail.getCategory();
    							totalPrice += orderLine.getA_price() * orderLine.getAmount();
    							String versionMsg = "";//图书版本信息
    							switch (book.getVersion()) {
    							case 1:
    								versionMsg = "【精装版】";
    								break;
    							case 2:
    								versionMsg = "【简装版】";
    								break;
    							case 3:
    								versionMsg = "【收藏版】";
    								break;
    							}
    				%>
    				<ul class="oneUL">
    					<li >
    						<img src="<%=book.getFeature_images() %>" class="pic">
    						<p style="color: blue"><%=category.getName() %>&nbsp;&nbsp;<%=detail.getName() %>&nbsp;&nbsp;<%=book.getName() + versionMsg %>&nbsp;&nbsp;</p>
    						<p><font>¥<%=orderLine.getA_price()%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;×<%=orderLine.getAmount() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有货</p>
    						<p><img src="images/sureLogo_14.png" alt=""><span><%=book.getService_th() %></span></p>

    					</li>
    					<li><pre>【赠品】  高级定制干花书签  随机  ×1</pre></li>
					</ul>
    				<%
    						}
    					}
    				 %>
    			</div>
    		</div>
			
			<input type="hidden" id="totalPrice" value="<%=totalPrice + 6 %>"/>
			<input type="hidden" id="bookIds" value="<%=bookIds %>"/>
    		<div class="money">
    			<span><font><%=orderLines.size() %></font>件商品，总商品金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥<%=totalPrice %></span>
    			<span><img src="images/sureLogo_18.png" alt="">运费：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥6.00</span>
    			
    			<span>应付总额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥<%=totalPrice + 6 %></span>
    		</div>
    		<div class="submit">
    			<span>应付金额：<font>¥<%=totalPrice + 6 %></font><a href="javascript:void(0)" onclick="submit()"><img src="images/21_03.png" alt=""></span></a>
    		</div>
    	</div>

    </div>
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
