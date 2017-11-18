<%@ page language="java" import="java.util.*, com.guaishushu.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单页面</title>
    
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
	
	<script type="text/javascript" src="js/showorder.js"></script>
	
	<style type="text/css">
		span{
			display: inline-block;
			font-size: 14px;
			color: black;
		}
		td{
			font-size: 12px;
			color: black;
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

  </head>
  
  <body>
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
    	<span style="color: #C5000B; font-size: 35px;">我的订单</span>
    	<%
    		String type = (String) request.getAttribute("type");
    		if ("single".equals(type)) {
    	%>
    		<a href="/MyBookShop/ToShowOrder?type=all" style="color: blue; font-size: 14px">查看全部订单</a>
    	<%		
    		}
    	 %>
    </div>

    </div>
    		<%
    			Set<Order> orders = (Set<Order>) request.getAttribute("orders");
    			Iterator<Order> it = orders.iterator();
    			while (it.hasNext()) {
    				Order order = it.next();
    				Set<OrderLine> orderLines = order.getOrderlines();
    				//System.out.println("orderLines size : " + orderLines.size());
    				/*超链接文字,如果未付款就显示去付款,如果付款完成就显示去评价,
    				如果是评价完成就显示查看评价,对应三个onclick方法*/
    				String a = "";
    				String onclick = "";
    				//订单状态
    				String status = "";
    				status = order.getStatus();
    				if ("1".equals(status)) {
    					status = "待付款";
    					a = "去付款";
    					onclick = "pay(" + order.getId() + ", " + order.getSum_price() + ")";
    				} else if ("2".equals(status)) {
    					status = "买家已付款<br>付款方式 : " + order.getPayway().getPay_style();
    					a = "去评价";
    					onclick = "book_evaluate(" + order.getId() + "," + 8 + ")";
    				} else if ("3".equals(status)) {
    					status = "评价完成";
    					a = "查看评价";
    					onclick = "checkEvaluate(" + order.getId() +")";
    				}
    		%>
    		
    	<div style="border: 2px; border-color: #EAF8FF; ">
    		<div style="background-color: #EAF8FF; font-size: 28px;">
    			&nbsp &nbsp &nbsp <span><%=order.getStart_date() %></span>
    			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <span>订单号 : <%=order.getId() %></span>
    			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <span><img style="width: 20px; height: 20px" src="images/xiaomifeng.png"></span>
    			<span>briup自营</span>
    			&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <span><img style="width: 60px; height: 25px" src="images/lianxiwo.png"></span>
    		</div>
    		<table border="0.5px" bordercolor="#DAF3FF" cellspacing="0.5px" align="center"s>
    			
    		<%
    			//判断当前是不是第一个订单项
    			boolean flag = true;
    			
    			Iterator<OrderLine> it2 = orderLines.iterator();
    			while (it2.hasNext()) {
    			OrderLine orderLine = it2.next();
    			Product book = orderLine.getProduct();
    			CategoryDetail detail = book.getCate_detail();
    			Category category = detail.getCategory();
    			//图书版本
    			int version = orderLine.getProduct_version();
    			String versionMsg = "";
    			switch (version) {
    			case 1:
    				versionMsg = "精装版";
    				break;
    			case 2:
    				versionMsg = "简装版";
    				break;
    			case 3:
    				versionMsg = "收藏版";
    				break;
    			}
    			
    		%>
    		
    			<tr>
    				<td width="180px"><a href="javascript:void(0)" onclick="clickBook(<%=book.getId() %>)"><img style="width: 80px; height: 120px; margin: 35px" src="<%=book.getFeature_images() %>"></a></td>
    				<td style="padding: 10px" width="300px" style="text-align: left;">图书- <%=category.getName() %>- <%=detail.getName() %>- <a href="javascript:void(0)" onclick="clickBook(<%=book.getId() %>)">《<%=book.getName() %>》 <%=book.getISBN() %></a>  briup自营  [交易快照]<br>
    					<span style="color: #666666; font-size: 12px">版本 : 【<%=versionMsg %>】</span><br><br><br>
    						<img style="float: left; width: 20px; height: 20px" src="images/icons/bgPic_11.png">
    						<img style="float: left; width: 20px; height: 20px" src="images/icons/bgPic_13.png">
    						<img style="float: left; width: 20px; height: 20px" src="images/icons/bgPic_18.png">
    						<img style="float: left; width: 20px; height: 20px" src="images/icons/bgPic_21.png">
    				</td>
    				<td width="80px">¥<%=orderLine.getA_price() %></td>
    				<td width="80px"><%=orderLine.getAmount() %></td>
    		<%
    			if (flag) {
    		%>
					<td rowspan="<%=orderLines.size() %>" width="100px">订单商品总数 <br><%=order.getNum() %></td>
    				<td rowspan="<%=orderLines.size() %>" width="120px">退款/退货<br>投诉商家</td>
					<td rowspan="<%=orderLines.size() %>" width="120px" ><span style="color: #C5000B">¥<%=order.getSum_price() %></span><br><span style="color: #666666; font-size: 12px;">(含运费 : ¥6.0)</span></span></td>
    				<td rowspan="<%=orderLines.size() %>" width="120px" >订单状态<br><br>(<%=status %>)<br><a href="javascript: void(0)" onclick="<%=onclick %>"><%=a %></a></td>
    				<td rowspan="<%=orderLines.size() %>" width="120px"><a href="javascript: void(0)"  onclick="deleteOrder(<%=order.getId() %>)">删除订单</a></td> 
				    		
    		<%		 
    				//下一次就不进来了
    				flag = false;
    			}
    		 %>
    			
    			</tr>
    		
    		<%	
    			}
    		%>
    		
    		</table>
    		<hr style="color: #EAF8FF"/>
    		</div>
    		<br><br><br>
    		<%	
    			}
    		 %>
    		
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
