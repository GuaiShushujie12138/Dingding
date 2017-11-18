<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>briup电子商务-登录页</title>
    
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

  </head>
  
  <body>
  		<div class="header1">
    	<a href="#">
        	<img src="images/logo.png">
        </a>
    </div>
    <div class="content1">	<!--背景颜色-->
    	<div class="c1_box1"><!--背景图片-->
        	<div class="login_box"><!--登陆框-->
            	<div class="center1">
        		<form action="/MyBookShop/UserCheck" method="post">
                	<h1>账号登陆</h1>
                    <h2>公共场所请不要泄露您的密码，以防止账号丢失
                    </h2>
                    <div class="si_box">
                    	<span class="usr_icon"></span>
                        <input type="text" name="username"/>
                    </div>
                    <!--分割条-->
                    <div class="c10"></div>
                    <div class="si_box">
                    	<span class="pwd_icon"></span>
                        <input type="password" name="passwd"/>
                    </div>
                    <div class="fg_box">
                    	<a class="fg" href="/MyBookShop/ToForgetPwd">忘记登陆密码？</a>
                        <a class="treg" href="/MyBookShop/ToRegister">立即注册</a>
                    </div>
                    <%
                    	//判断当前是不是由于用户名或者密码错误而跳转回来的
                    	//显示出错误信息,如果没有错误信息,显示空字符串
                    	String info = (String) request.getAttribute("info");
                    	if (info != null && !"".equals(info)) {
                    %>
                    	<div>
                    		<span style="font-size: 12px; color: E4393C"><%=info %></span>
                   		</div>
                    <%		
                    	}
                     %>
                     
                    <div class="sub_box">
                    	<input type="hidden" name="type" value="login"/>
                    	<input type="submit" value="登陆"/>
                    </div>
                 </form>
                    <div>
                    	请使用合作网站账号登陆杰普电子商务网：
                    </div>
                    <div class="com_box">
                    	<span class="tencent"></span>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer1">
    	<div class="links">
        	<a href="#">关于我们</a>|
            <a href="#">联系我们</a>|
            <a href="#">友情链接</a>|
            <a href="#">关于我们</a>|
            <a href="#">联系我们</a>|
            <a href="#">友情链接</a>
        </div>
        <div>
        	沪ICP备1928832 杰普软件briup.com版权所有。
        </div>
        
        <img src="images/police.png">
        
    </div>
  </body>
</html>
