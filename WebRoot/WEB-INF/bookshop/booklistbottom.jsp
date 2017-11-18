<%@ page language="java" import="java.util.*, com.guaishushu.bean.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--商品列表-->

		<%
        	//获取当前页和页面总数
        	int pageNow = (int) request.getAttribute("pageNow");
        	int pageCount = (int) request.getAttribute("pageCount");
         %>
         
         <input type="hidden" value="<%=pageNow %>" id="pageNow"/>
         <input type="hidden" value="<%=pageCount %>" id="pageCount"/>

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
    		if (i == pageNow) {
    	%>
    		<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=i %>)" onmouseover="mouseOver(<%=i %>)" onmouseout="mouseOut(<%=i %>)"><li style="background-color: #FA5000; color: white;" id="li_sort_<%=i %>" class="li_sort"><%=i %></li></a>
    	<%	
    		} else {
    	%>
    		<a class="a_sort" href="javascript:void(0)" onclick="jumpPage(<%=i %>)" onmouseover="mouseOver(<%=i %>)" onmouseout="mouseOut(<%=i %>)"><li id="li_sort_<%=i %>" class="li_sort"><%=i %></li></a>
    	<%		
    		}
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
