<%@page import="com.guaishushu.bean.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>booklist</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/common.css"/>
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/icons.css" />
	<link rel="stylesheet" href="css/table.css" />
	
	<style type="text/css">
		.li_sort{
			float: left;
			font-size: 16px;
			color: #666666;
			margin: 8px;
			font-family: 黑体;
		}
		.a_sort{
			background-color: red;
		}
		
		#current_page{
			color : white;
			background-color: #FA5000;
		}
		.span_font{
			font-size: 12px;
			color: black;;
		}
	</style>
	
	<style type="text/css">
		
		.show_search_div{
			background: #C5000C;
			color: white;
		}
	</style>
	
	<script type="text/javascript" src="js/index.js"></script>
	
	<script type="text/javascript" src="js/booklist.js"></script>	
	
  </head>
  
  <body>
	<!--顶部-->
  		<%
  			boolean flag = false;//标志用户是否登录
  			User user = (User) session.getAttribute("login_user");
  			
  			//获取传递过来的图书分类数据
  			TreeSet<Category> set = (TreeSet<Category>) application.getAttribute("all_books");
  			Iterator<Category> it_cate = set.iterator();
  		
  			if (user != null) {
  				//说明用户登录了
  				flag = true;
  			}
  	 %>
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<% if (flag) {
            		%> 
            			<li><a style="color : green; size: 4px"><%=user.getName() %>, 欢迎!</a></li>
            			<li><a href="/MyBookShop/ToIndex?type=exit">安全退出</a>|</li>
                		<li><a href="/MyBookShop/ToShowOrder?type=all">我的订单<span class="jt_down"></span></a>|</li>
            		<%
            	} else {
            		%> 
            			<li><a href="/MyBookShop/ToLogin" style="color: #ff0000">请登录</a></li>
            		<%
            	} %>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
      <div class="header3">
    	<a href="/MyBookShop/ToIndex"><img src="images/logo.png"></a>
        	<div class="h3_center" style="position:relative;">
        	<div class="search_box">
            	<input style="font-size: 16px;color: #C5000C;margin-left: 15px;" id="search_input" onkeyup="searchBook()" onblur="hide()" onfocus="show()" type="text"/>
                <span style="cursor: pointer;" onclick="search()">搜索</span>
            </div>
            <div id="serarch_div" style="z-index:9999; display: none; width: 421px; background:#fff; height: 800px;border: 0.5px solid #ccc; position: absolute;">
            	<div id="show_search_1" name="show_search" style="padding-left:13px;height: 15px; width: 408px;cursor: pointer;border-bottom: 0.01px solid gray;" onmouseover="over(this)" onmouseout="out(this)" onclick="chooseA(this)">Java编程思想</div>
            	<div id="show_search_2" name="show_search" style="padding-left:13px;height: 15px; width: 408px;cursor: pointer;border-bottom: 0.01px solid gray;" onmouseover="over(this)" onmouseout="out(this)" onclick="chooseA(this)">丝绸之路</div>
            	<div id="show_search_3" name="show_search" style="padding-left:13px;height: 15px; width: 408px;cursor: pointer;border-bottom: 0.01px solid gray;" onmouseover="over(this)" onmouseout="out(this)" onclick="chooseA(this)">好心眼儿巨人</div>
            </div>
            <br>
            <p>
            	<%
            		while (it_cate.hasNext()) {
            			Category category = it_cate.next();
            	%>
            		<a href="/MyBookShop/ToBookList?type=category&category_id=<%=category.getId() %>"><%=category.getName() %></a>|
            	<%	
            		}
            	 %>
            </p>
        </div>
        <%  
        if (flag) {
        %>
        	<div class="h3_right">
        	<div class="myyy">
            	<a href="/MyBookShop/ToMyInfo">个人信息</a>
                <span class="sj_down"></span>
             </div>
            <div class="tsc">
            	<a href="/MyBookShop/ToShopCar">去购物车结算</a>
                <span class="sj_right"></span>
            </div>
        </div>
        <%
        }
        %>
    </div>
    <!--头部导航-->
    <div class="nav_top">
    	<div class="nav_top_center">
            <div>
                全部图书分类
            </div>
            <ul>
            <%
            	Iterator<Category> it_cate2 = set.iterator();
            	while (it_cate2.hasNext()) {
            		Category category = it_cate2.next();
            %>
            	<li><a href="/MyBookShop/ToBookList?type=category&category_id=<%=category.getId() %>"><%=category.getName() %></a></li>
            <%	
            	}
            %>
            </ul>
        </div>
    </div>
    
    <!--内容-->
    <div class="container4">
    	<!--热卖-->
    	<div class="c4_b1">
        	<ul class="c4_b1_boxes">
            	<%	
            				//所有出版社
            				Set<Publish> publishs = (Set<Publish>) request.getAttribute("publishs");
            				
            				//所有图书价格
            				Set<PriceRank> ranks = (Set<PriceRank>) request.getAttribute("ranks");
       						
       						//排序方式
       						String sortMsg = request.getParameter("sort");
       						
       						//筛选方式
       						String publishMsg = request.getParameter("publish");
       						String rankMsg = request.getParameter("rank");
       						
       						//大分类还是小分类
       						String type = request.getParameter("type");
       						//System.out.println("type : " + type);
       						
       							//大分类,获取大分类id
       							String categoryMsg = request.getParameter("category_id");
       							String detailMsg = request.getParameter("detail_id");
       							long category_id = 0;
       							if (categoryMsg != null) {
       								category_id = Long.parseLong(categoryMsg);
       							}
       							
       							//小分类,获取小分类id
       							long detail_id = 0;
       							if (detailMsg != null) {
       								detail_id = Long.parseLong(detailMsg);
       							}
       							
                    		//拿出热卖图书
                    		Set<Product> hot_books = (Set<Product>) request.getAttribute("hot_books");
							Iterator<Product> it_book = hot_books.iterator();
							
							while (it_book.hasNext()) {
								Product book = it_book.next();
						%>
							<li>
                           		<a href="/MyBookShop/ToBookView?bookId=<%=book.getId() %>"><img style="width: 110px; height: 125px" src="<%=book.getFeature_images() %>"/></a>
                           		  
                       			<div class="c4_b1_box_txt">
                    				<a style="color: blue;" href="/MyBookShop/ToBookView?bookId=<%=book.getId() %>"><%=book.getName() %></a>
                        			<h1>活动价：<b>￥<%=book.getPrice() %></b></h1>
                        			<a href="javascript:void(0);" onclick="addShopCar(<%=book.getId() %>)"><h2>立即抢购</h2></a>
                   				 </div>
                       		</li>
						<%
							}                    		
                    	 %>
            </ul>
        </div>
        
        
        
        <!-- 当前是大分类还是小分类 -->
        <input type="hidden" value="<%=type %>" id="type"/>
        <input type="hidden" value="<%=category_id %>" id="category_id"/>
        <input type="hidden" value="<%=detail_id %>" id="detail_id"/>
        <input type="hidden" value="<%=sortMsg %>" id="sortName"/>
        <input type="hidden" value="<%=publishMsg %>" id="publishName"/>
        <input type="hidden" value="<%=rankMsg %>" id="rankName"/>
        
        <div class="c4_b2">
            <h1 class="c4_b2_x">
            	<a href="javascript:void(0)">${categoryName }&nbsp;&nbsp;></a>
                <span><a href="javascript:void(0)">${detailName }</a></span>
            </h1> 
                
            <ul class="c4_b2_y">
            	<li id="rank_li" style="display: none">
                	<span class="search_key">价格：</span>
                    <span id="span_rank" class="search_val">0-99</span>
                    <a href="javascript:void(0)" onclick="cancelRank()"><span class="search_del"></span></a>
                </li>
                <li id="publish_li" style="display: none">
                	<span class="search_key">出版社：</span>
                    <span id="span_publish" class="search_val">清华出版社</span>
                    <a href="javascript:void(0)" onclick="cancelPublish()"><span class="search_del"></span></a>
                </li>
            </ul>
        </div>
        <div class="c4_b3">
        	<h1></h1>
        	<div>
            	<ul class="c4_b3_search">
                	<li class="c4_b3_name">价格：</li>
                    <li class="c4_b3_vals">
                        <% 
                        Iterator<PriceRank> it_rank = ranks.iterator();
          				while (it_rank.hasNext()) {
          					PriceRank rank = it_rank.next();
          				%>
          				
          					<a name="a_rank" value="<%=rank.getId() %>" id="rank_<%=rank.getId() %>" href="javascript:void(0)" onclick="clickRank(<%=rank.getId() %>)"><%=rank.getMin_price() %>-<%=rank.getMax_price() %></a>
          				
          				<%
          				}	
          				%>
					</li>
                </ul><ul class="c4_b3_search">
                	<li class="c4_b3_name">出版社：</li>
                    <li class="c4_b3_vals">
                        <%
                        	Iterator<Publish> it_publish = publishs.iterator();
          					while (it_publish.hasNext()) {
          						Publish publish = it_publish.next();
          				%>
          				
          					<a href="javascript:void(0)" id="publish_<%=publish.getId() %>" name="a_publish" value="<%=publish.getId() %>" onclick="clickPublish(<%=publish.getId() %>)"><%=publish.getName() %></a>
          					
          				<%
          					}
                         %>
					</li>
                </ul>
            </div>
        </div>
    	<div class="c4_b4">
        	<div class="c4_b4_x">
            	<ul class="c4_b4_nav">
                	<li id="default_li" name="sort_li" style="background: #e4393c;"><a style="color: white;" href="javascript:void(0)" id="default" name="sort" onclick="clickSort('default')">默认</a></li>
                    <li id="sale_num_li" name="sort_li"><a href="javascript:void(0)" id="sale_num" onclick="clickSort('sale_num')" name="sort">销量</a></li>
                    <li id="price_li" name="sort_li"><a href="javascript:void(0)" id="price" onclick="clickSort('price')" name="sort">价格</a></li>
                    <li id="new_product_li" name="sort_li"><a href="javascript:void(0)" id="new_product" onclick="clickSort('new_product')" name="sort">新品</a></li>
                </ul>
                <div class="c4_b4_search">
                	<input type="text"/><span>确认</span>
                </div>
                <div class="c4_b4_pages">
                	
                </div>
				<div class="clear"></div>
            </div>
             <div class="c4_b4_y">
            	<ul>
                    <li><input type="checkbox"> 仅显示有货</li>
                </ul>               
            </div>
       	</div>
       	
       	
       	<div id="list_bottom_div">
       	
       	<div>
       	<%
        	//获取当前页和页面总数
        	int pageNow = (int) request.getAttribute("pageNow");
        	int pageCount = (int) request.getAttribute("pageCount");
         %>
         
         <input type="hidden" value="<%=pageNow %>" id="pageNow"/>
         <input type="hidden" value="<%=pageCount %>" id="pageCount"/>
       	
    	<!--商品列表-->
        <div class="c4_b5">	
        	<div class="c4_b5_content">
            	<ul class="c4_b5_c_boxes">
            	<%
            		Set<Product> books = (Set<Product>) request.getAttribute("books");
            		Iterator<Product> it = books.iterator();
            		while (it.hasNext()) {
            			Product book = it.next();
            	%>
            		<li class="c4_b5_c_box">
                    	<!--图片-->
                    	<div class="c4_b5_c_box_pic">
                        	<div class="c4b5_pic_view">
                            	<a href="/MyBookShop/ToBookView?bookId=<%=book.getId() %>"><img src=<%=book.getFeature_images() %>></a>
                            </div>
                        </div>
                        <!--价钱-->
                        <div class="c4_b5_c_box_txt">
                        	<h1>￥ <%=book.getPrice() %></h1>
                            <h2><a href="/MyBookShop/ToBookView?bookId=<%=book.getId() %>"><%=book.getName() %></a></h2>
                            <h2>销量   <span style="color: #e4393c"><%=book.getSale_num() %></span> 件</h2>
                        </div>
                        <!--购买等操作-->
                        <div class="c4b5_el">
                        	<div class="c4b5_el_x">
                            	<span class="wjx01"></span>
                            </div>
                        </div>
                        <%-- <input type="hidden" id="bookId" value="<%=book.getId() %>"/>
                        <%
                        	System.out.println("bookId : " + book.getId());
                         %> --%>
                        <ul class="c4b5_option">
                            <li class="shopcar_box"><span class="shopcar01"></span><a href="javascript:void(0);" onclick="addShopCar(<%=book.getId() %>)">加入购物车</a></li>
                        </ul>
                    </li>
            	<%
            		}
            	 %>
                </ul>
            </div>
        </div>
    </div>
     <br>
    
    <div style="text-align: center;">
    	<ul style="display: inline-block ;" class="pro_select_vals">
    	<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=1 %>)" onmouseover="mouseOver('first')" onmouseout="mouseOut('first')"><li class="li_sort" id="li_sort_first">首页</li></a>
    	<%
    		if (1 != pageNow) {
    	%>
    	
    		<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=pageNow - 1 %>)" onmouseover="mouseOver('last')" onmouseout="mouseOut('last')"><li class="li_sort" id="li_sort_last">上一页</li></a>
    	
    	<%			
    			}
    	
    		for (int i= 1; i <= pageCount; i ++) {
    	%>
    		
    		<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=i %>)" onmouseover="mouseOver(<%=i %>)" onmouseout="mouseOut(<%=i %>)"><li id="li_sort_<%=i %>" class="li_sort"><%=i %></li></a>
    	
    	<%	
    		}
    		
    		if (pageNow != pageCount) {
    	%>
    	
    		<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=pageNow + 1 %>)" onmouseover="mouseOver('next')" onmouseout="mouseOut('next')"><li id="li_sort_next" class="li_sort">下一页</li></a>
    	
    	<%		
    			}
    	 %>
    	 	<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=pageCount %>)" onmouseover="mouseOver('end')" onmouseout="mouseOut('end')"><li class="li_sort" id="li_sort_end">尾页</li></a>
    		<li class="li_sort" id="current_page">当前页 <%=pageNow %>/<%=pageCount %></li>
    	</ul>
    	<br>
    	<span style="display:inline-block; text-align: center;">
    		<span class="span_font">到第</span>
    		<input id="input_page" type="text" style="width: 60px;"/>
    		<span class="span_font">页</span>
    		<a href="javascript:void(0)" onclick="jumpPage2()"><img style="margin-left: 19px;margin-top: 5px;" src="images/sure.png" /></a>
   		</span>
    </div>
    
    </div>
   
    
    <br><br><br><br>
    
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
            <p>沪ICP备14033591号-8 杰普briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>
  		
  </body>
  
</html>
