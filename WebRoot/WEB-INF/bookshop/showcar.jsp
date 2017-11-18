<%@ page language="java" import="java.util.*, com.guaishushu.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="js/showcar.js"></script>

	<link rel="stylesheet" href="css/fullCar.css" />
	<link rel="stylesheet" href="css/common.css" />
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/icons.css" />
	<link rel="stylesheet" href="css/table.css" />
	
	<style type="text/css">
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
    		<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="/MyBookShop/ToIndex?type=exit">安全退出</a>|</li>
                <li><a href="#">我的订单<span class="jt_down"></span></a>|</li>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="/MyBookShop/ToIndex"><img src="images/logo.png"  class="oneImg"></a>
    	

        <div class="h3_right">
        	<img src="images/play_03.png" alt="">
        </div>
       
    </div>
<!--中间部分div-->
<%
	//查看当前用户购物车中是否有图书
	boolean flag = false;
	ShopCar car = null;
	if (session.getAttribute("car") != null) {
		car = (ShopCar) session.getAttribute("car");
		if (car.getMap() != null && car.getMap().size() > 0) {
	flag = true;
		}
	}
%>
 
 <% 
 	if (flag) {
 %> 
 	<div class="empty">
	<div class="peisong"><pre>全部商品<%=car.getMap().size() %></pre></div>
	<div class="mainCenter">
		<div class="allCheck">
			<input type="checkbox" id="checkAll" onchange="checkAll()"><p>全选</p>
			<p style="margin-left: 130px">商品</p>
			<p style="margin-left: 140px">单价(元)</p>
			<p style="margin-left: 140px">数量</p>
			<p style="margin-left: 160px">小计(元)</p>
			<p style="margin-left: 150px">操作</p>
		</div>
		<div class="mainPro">
			<%
				Iterator<OrderLine> lines = car.getOrderlines();
				while (lines.hasNext()) {
					OrderLine line = lines.next();
					Product book = line.getProduct();
					String versionMsg = "";
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
				<div class="bb" style="text-align: center;">
					<input type="checkbox"  onchange="choose(this, <%=book.getId() %>, <%=book.getVersion() %>)" id="check_<%=book.getId() %>_<%=book.getVersion() %>"  name="checkbox" value="<%=book.getId() %>=<%=book.getVersion() %>" >
					<img style="width: 50px; height: 70px; margin-left: 40px" src="<%=book.getFeature_images() %>" >
				 	<span style="margin-top: 50px; margin-left: 30px">
                   		<%=book.getName() + versionMsg %>  
                    	<br>
                	</span>
                	<div style="margin-left: 160px;" class="mm" >
                		<span id="single_price_<%=book.getId() %>_<%=book.getVersion() %>">¥<%=line.getA_price() %></span>
                	</div>
                		
                	<span style="height: 30px; margin: 40px;">
                		<span style="height: 30px; margin: 0px;">
                			<div style="width: 20px; height: 20px; background-color: #cccccc">
                				<a style="font-size: 15px" href="javascript:void(0)" onclick="updateBook(<%=book.getId() %>, 'des', <%=book.getVersion() %>)">-</a>
                			</div>
                		</span>
                		<input readonly="readonly" style="width: 35px; margin: auto; text-align: center;" id="book_<%=book.getId() %>_<%=book.getVersion() %>" value="<%=line.getAmount() %>" type="text">
                		<span style="height: 30px; margin: 0px;">
                			<div style="width: 20px; height: 20px; background-color: #cccccc">
                				<a style="font-size: 15px" href="javascript:void(0)" onclick="updateBook(<%=book.getId() %>, 'add', <%=book.getVersion() %>)">+</a>
                			</div>
                		</span>
                	</span>
                	
                	<div class="ri" style="margin: auto;">
                		<span id="price_<%=book.getId() %>_<%=book.getVersion() %>" style="font-size: 12px; color: red; margin-left: 40px; margin-top: 38px;" name="single_price_span">¥<%=line.getA_price() * line.getAmount() %></span>
                	</div>
                	
                	<%-- <div id="price_<%=book.getId() %>" class="ri">¥<%=book.getPrice() %></div> --%>
               		<div class="righ">
               		 	<div class="rig"><a href="javascript:void(0)" onclick="deleteBook(<%=book.getId() %>, <%=book.getVersion() %>)">删除</a></div>
               		</div>
				</div>
			<%
				}
			 %>
			
		</div>
		<div class="allButtom" style="text-align: center;">
			<a href="javascript:void(0)" onclick="count()"><p style="width: 80px; height: 60px; font-size: 16px;" class="caozuo">去结算</p></a>
			<span>已选择<font id="productNum">0</font>件商品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总价(不含运费)：<font id="totalPrice">¥0.00</font></span>
		</div>
	</div>
</div>
 <%
 	} else {
 %> 
 	<!--中间部分-->
	<div class="empty" align="center">
	<a href="javascript:void(0)" onclick="buy()"><img src="images/empty_03.png" class="car"></a>
	</div>
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
  </body>
</html>
