<%@ page language="java" import="java.util.*,com.guaishushu.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>briup电子商务-首页</title>
    
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
		
		.show_search_div{
			background: #C5000C;
			color: white;
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
	
	<script type="text/javascript" src="js/index.js"></script>
	
  </head>
  
  <body>
  	<% 	
  		boolean flag = false;//标志用户是否登录
  		User user = (User) session.getAttribute("login_user");
  		//System.out.println(user);
  		
  		//获取传递过来的图书分类数据
  		Set<Category> set = (Set<Category>) request.getAttribute("categories");
  		Iterator<Category> it = set.iterator();
  		
  		if (user != null) {
  			//说明用户登录了
  			flag = true;
  		}
  	 %>
    <!--顶部-->
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
            			<li><a href="/MyBookShop/ToRegister" style="color: #0000ff">免费注册</a></li>
            		<%
            	} %>
            	
                <li><a href="">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="">网站导航<span class="jt_down"></span></a></li>
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
            		while (it.hasNext()) {
            			Category category = it.next();
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
            	Iterator<Category> it2 = set.iterator();
            	while (it2.hasNext()) {
            		Category category = it2.next();
            %>
            	<li><a href="/MyBookShop/ToBookList?type=category&category_id=<%=category.getId() %>"><%=category.getName() %></a></li>
            <%	
            	}
            %>
            </ul>
        </div>
    </div>
    
	<div class="container3">
    	<div class="c3_b1">
        	<div class="c3_b1_left">
            	<dl>
               		
               			<%
               				Iterator<Category> it3 = set.iterator();
               				while (it3.hasNext()) {
               					Category category = it3.next();
               			%>	<dd>
               					<h1><%=category.getName() %></h1>
               					<p>
               			<%
               					Map<Long, CategoryDetail> details = category.getCate_detail();
               					Iterator<CategoryDetail> it4 = details.values().iterator();
               					while (it4.hasNext()) {
               						CategoryDetail detail = it4.next();
               				%> 
               						<a href="/MyBookShop/ToBookList?type=detail&category_id=<%=category.getId() %>&detail_id=<%=detail.getId() %>"><%=detail.getName() %></a>
               				<%
               					}
               				%> 
               					</p>
                    		</dd>	
               				<%
               				}
               			 %>
                </dl>
            </div>
            <div class="c3_b1_center">
            	<div>
                	<a id="turn_1" href="/MyBookShop/ToBookView?bookId=43"><img src="images/ad1.png"></a>
                	<a id="turn_2" style="display: none;" href="/MyBookShop/ToBookView?bookId=45"><img style="width: 698px;height: 288px;" src="images/lunbo3.png"></a>
                	<a id="turn_3" style="display: none;" href="/MyBookShop/ToBookView?bookId=44"><img style="width: 698px;height: 288px;" src="images/lunbo4.png"></a>
                </div>
                <div class="c3_b1_c_bottom">
                    <ul>
                    	<%
                    		//拿出热卖图书
                    		Set<Product> hot_books = (Set<Product>) request.getAttribute("hot_books");
							Iterator<Product> it_book = hot_books.iterator();
							
							while (it_book.hasNext()) {
								Product book = it_book.next();
						%>
							<li>
                           		<a href="/MyBookShop/ToBookView?bookId=<%=book.getId() %>"><img style="width: 140px; height: 160px" src="<%=book.getFeature_images() %>"/></a>
                           		<a style="color: blue;" href="/MyBookShop/ToBookView?bookId=<%=book.getId() %>"><%=book.getName() %></a>  
                       		</li>
						<%
							}                    		
                    	 %>
                    </ul>
            	</div>
            </div>
            <div class="c3_b1_right">
            	<h1>杰普快报<a href="#">更多</a></h1>
            	<ul>
            		<c:forEach items="${reports }" var="report">
                		<li><a href="/MyBookShop/ToBookView?bookId=${report.product.id }">〈加措〉${report.title }  </a></li>
                	</c:forEach>
                	
                	<c:forEach items="${reports }" var="report">
                		<li><a href="/MyBookShop/ToBookView?bookId=${report.product.id }">〈加措〉${report.title }  </a></li>
                	</c:forEach>
                	
                	<c:forEach items="${reports }" var="report">
                		<li><a href="/MyBookShop/ToBookView?bookId=${report.product.id }">〈加措〉${report.title }  </a></li>
                	</c:forEach>
                </ul>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="c3_b2">
        	<ul style="text-align: center;">
        	
        	<c:forEach items="${special_books }" var="special">
        		<li style="display: inline;">
        			<span style="display: inline-block;">
                	<div class="c3_b2_txt">
                    	<a href="/MyBookShop/ToBookView?bookId=${special.id }"><h1>${special.name }</h1></a>
                        <p>经典书籍</p>
                        <h2>畅销书籍</h2>
                        <h2>${special.explain }</h2>
                        <p><a href="/MyBookShop/ToBookView?bookId=${special.id }">更多精彩，点击进入</a></p>
                    </div>
                	</span>
                </li>
        	</c:forEach>

            </ul>
        </div>
        
        <br><br><br><br>
    <div class="c20"></div>
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
