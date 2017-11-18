<%@ page language="java" import="java.util.*, com.guaishushu.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书详情</title>
    
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
	
	<script type="text/javascript" src="js/viewbook.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	
	<style type="text/css">
		a:link{
			color: blue;
			text-decoration:none;/用于去除下划线/
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
  		<%
  			Product book = (Product) request.getAttribute("book");
  				
  			boolean flag = false;//标志用户是否登录
  			User user = (User) session.getAttribute("login_user");
  			
  			//获取传递过来的图书分类数据
  			Set<Category> set = (Set<Category>) request.getAttribute("categories");
  			Iterator<Category> it = set.iterator();
  		
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
    
	<div class="container5">
    	<div class="cn5_top">
            <div class="cn5_top_x center01">
            	<a class="font20" href="#">书籍</a> >
                <a href="#" style="color: blue">${category.name}</a>
            </div>
            <div class="cn5_top_y center01">
            	<div class="cn5topy_1">
                	<div class="cn5topy_imgview">
                    	<img src="<%=book.getFeature_images() %>"/>
                        <ul class="cn5topy_imglist">
                        	<li><a href="#"><img src="<%=book.getImages() %>"></a></li>
                            <li class="current"><a href="#"><img src="<%=book.getImages() %>"></a></li>
                            <li><a href="#"><img src="<%=book.getImages() %>"></a></li>
                            <li><a href="#"><img src="<%=book.getImages() %>"></a></li>
                            <li><a href="#"><img src="<%=book.getImages() %>"></a></li>
                        </ul>
                    </div>
                </div>
                <div class="cn5topy_2">
                	<h1 class="pro_title font15"><%=book.getName() %></h1>
                    <div class="pro_price">
                            <div class="pro_price_x">
                                <p> briup价：<b id="price">￥<%=book.getPrice() %></b> <a href="#">(降价通知)</a></p>
                            </div>
                            
                    </div>
                    <div class="pro_ship">
                        <div>
                        	<div class="pro_key fl">服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</div>
                            <ul class="pro_service f1">
                                <li class="service_fq"><%=book.getService_fg() %></li>
                                <li class="service_myf"><%=book.getService_myf() %></li>
                                <li class="service_zt"><%=book.getService_zt() %></li>
                                <li class="service_th"><%=book.getService_th() %></li>
                            </ul>
                        </div>
                        <br>
                        <div>
                    		<span>销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量：</span>
                    		&nbsp; <span style="color: #e4393c; font-size: 14px; font-family: 黑体"><%=book.getSale_num() %></span> 件
                    	</div>
                    </div>
                    <br>
                    
                    <div class="pro_selects">
                    	<div class="pro_select">
                        	<div class="pro_key pro_key_vertical fl">选择版本：</div>
                            <ul class="pro_select_vals">
                                <a href="javascript:void(0)" onmouseover="versionOver('jzb')" onmouseout="versionOut('jzb')" onclick="choose('jzb', <%=book.getId() %>)"><li id="jzb" style="background-color: #C5131E; color: white;">简装版</li></a>
                                <a href="javascript:void(0)" onmouseover="versionOver('jin')" onmouseout="versionOut('jin')" onclick="choose('jin', <%=book.getId() %>)"><li id="jin" style="background-color: #FFFFFF; color: black;">精装版</li></a>
                                <a href="javascript:void(0)" onmouseover="versionOver('scb')" onmouseout="versionOut('scb')" onclick="choose('scb', <%=book.getId() %>)"><li id="scb" style="background-color: #FFFFFF; color: black;">收藏版</li></a>
                            </ul>
                        </div>
                        
                        <div>
                    		<span>库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存：</span>
                    		&nbsp; <span style="color: #e4393c; font-size: 14px; font-family: 黑体"><%=book.getStock() %></span> 件
                    	</div>
                        
                    </div>
                    
                    <div class="pro_buy">
                    	<div class="pro_buy_nums">
                        	<input id="bookNum" type="text" value="1"/>
                            <dl>
                            	<dd><a href="javascript:void(0)" onclick="updateBook('add')">+</a></dd>
                                <dd><a href="javascript:void(0)" onclick="updateBook('des')">-</a></dd>
                            </dl>
                        </div>
                        <a href="javascript:void(0)" onclick="addShopCar(<%=book.getId() %>)"><div class="pro_addshop">加入购物车</div></a>
                    </div>
                    
                </div>
            </div>
        </div>
    	<div class="c5_b2">
    		<div class="c5_b2_right">
            	<!--选项卡-->
            	<ul class="c5_b2_tabs">
            	<!-- height:38px;
	background:#fff;
	position:relative;
	top:-1px;
	border-top:2px solid #e4393c;
	overflow:hidden; -->
            	<!-- border-top:2px solid #e4393c; -->
                	<li name="tab_li" id="introduce" onmouseover="mouseOver('introduce')">商品介绍</li>
                    <li name="tab_li" id="params" onmouseover="mouseOver('params')" class="current">规格参数</li>
                    <li name="tab_li" id="pack_list" onmouseover="mouseOver('pack_list')">包装清单</li>
                    <li name="tab_li" id="book_evaluate" onmouseover="mouseOver('book_evaluate')">商品评价</li>
                </ul>
                <!--内容-->
              	<div class="c5_b2_right_1 box">
                        <div class="introduce_item" name="tab_div" id="params_div">
                          <ul>
                        	<li>商品名称：<%=book.getName() %></li>
                            <li>商品编号：<%=book.getISBN() %></li>
                            <li class="fr"><a class="color_link1" href="#">更多参数>></a></li>
                          </ul>
                        </div>
                        
                        
                        <div style="display: none" class="introduce_item" name="tab_div" id="introduce_div">
                          <ul>
                        	<li>${book.explain }</li>
                            <li>${book.description }</li>
                          </ul>
                        </div>
                        
                        
                        <div style="display: none" class="introduce_item" name="tab_div" id="pack_list_div">
                          <ul>
                        	<li>包装完好</li>
                            <li>货物精美</li>
                          </ul>
                        </div>
                        
                        
                        <div style="display: none" class="introduce_item" name="tab_div" id="book_evaluate_div">
                          <ul>
                          	<c:if test="${evaluate == null}">
                          		<li>该图书还没有评价</li>
                          	</c:if>
                          	
                          	<c:if test="${evaluate != null}">
                          		<li style="color: #C5131E">${evaluate.user.name }</li>&nbsp;&nbsp;
                        		<li>评价 : ${evaluate.content }</li>&nbsp;&nbsp;
                        		<li style="color: #C5131E"> ❤  评分 :${evaluate.state } ❤</li>&nbsp;&nbsp;
                        		<li>版本 :【${book_version }】</li>&nbsp;&nbsp;
                        		<li>${evaluate.eva_date }</li>
                        		<li class="fr"><a class="color_link1" href="/MyBookShop/ToShowBookEvaluate?book_id=${book.id }">查看全部评价(${size })>></a></li>
                          	</c:if>
                          	
                          </ul>
                        </div>
                        
                        
               		</div>
                    <div class="intro_pics">
                    	<img class="intro_pic" src="<%=book.getFeature_images() %>">
                    </div>
                </div>
                
                
            <div class="c5_b2_right">
              	<div class="c5_b2_right_2">
                    	<h1>图书简介</h1>
                        <p><%=book.getExplain() %></p>
                        
                        <h1>图书内容</h1>
                        <p><%=book.getDescription() %></p>
                        
                        <h1>正品行货</h1>
                        <p>briup网上商城向您保证所售商品均为正品，briup自营商品开具机打发票或电子发票。</p>
                        
                        <h1>全国联保</h1>
                        <p>凭质保证书及briup网上商城发票，可享受全国联保服务，与您亲临商场选购的商品享受相同的质量保证。briup网上商城还为您提供具有竞争力的商品价格和运费政策，请您放心购买！</p>
                        
            		</div> 
            </div>
            
            <!--左侧栏-->
            <div class="c5_b2_left_container">
                <div class="c5_b2_left box">
                    <h1>服务时间 早9：00~凌晨1：00</h1>
                    <p>
                    <a href="#">
                        <img class="callmebyqq" src="images/icon_qq_03.png"/>
                    </a>
                    </p>
                </div>
                
                <div class="c5_b2_left box">
                    <h1>其他种类</h1>
                    <dl>
                        <dd>文学类</dd>
                        <dd>漫画类</dd>
                        <dd>通书类</dd>
                    </dl>
                    <dl>
                        <dd>文学类</dd>
                        <dd>漫画类</dd>
                        <dd>通书类</dd>
                    </dl>
                </div>
            </div> 
        </div>
    </div>
    	
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
