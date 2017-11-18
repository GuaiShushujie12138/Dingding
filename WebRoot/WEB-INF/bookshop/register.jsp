<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>briup电子商务-注册页</title>
    
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
	<script type="text/javascript" src="http://127.0.0.1:8080/MyBookShop/js/register.js" charset="utf-8"></script>
  </head>
  
  <body>
  		<div class="container2">
    	<div class="header2">
        	<div>
            	<a href="#"><img class="logo" src="images/logon_register.png"></a>
            </div>
            <div>
            	<ul class="tabs">
                	<li class="phone current"><a href="#">用手机登陆</a></li>
                </ul>
            </div>
            <div class="tlg">
            	已有账号 <a href="/MyBookShop/ToLogin">登陆</a>
            </div>
        </div>
        <div class="content2">
        	<form action="/MyBookShop/UserCheck" method="post">
			<ul class="reg_box">
            	<li>
                	<span><b>*</b>用户名：</span>
                    <input type="text" placeholder="请输入用户名(必填)" name="username" id="username" onblur="checkUserName()"/>
                    <div id="namediv" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="nameimg" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="nameInfo" style="margin-top: 8px;"></h5>
                </li>
               <li>
                	<span><b>*</b>密码：</span>
                    <input type="password" name="passwd" placeholder="请输入密码(必填)" id="passwd" onblur="checkPasswd()"/>
                    <div id="passwddiv" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="passwdimg" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="passwdInfo" style="margin-top: 8px;"></h5>
                </li>
                <li>
                	<span><b>*</b>确认密码：</span>
                    <input type="password" name="sure_passwd" placeholder="请再次输入密码(必填)" id="sure_passwd" onblur="checkSurePasswd()"/>
                    <div id="sure_passwd_div" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="sure_passwd_img" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="sure_passwdInfo" style="margin-top: 8px;"></h5>
                </li>
                <li>
                	<span><b>*</b>邮编：</span>
                    <input type="text" name="zip" placeholder="请输入邮编(必填)" id="zip" onblur="checkZip()"/>
                    <div id="zip_div" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="zip_img" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="zipInfo" style="margin-top: 8px;"></h5>
                </li>
                <li>
                	<span><b>*</b>地址：</span>
                    <input type="text" name="address" placeholder="请输入地址(必填)" id="address" onblur="checkAddress()"/>
                    <div id="address_div" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="address_img" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="addressInfo" style="margin-top: 8px;"></h5>
                </li>
                <li>
                	<span><b>*</b>电话：</span>
                    <input type="text" name="telephone" placeholder="请输入电话(必填)" id="telephone" onblur="checkTel()"/>
                    <div id="telephone_div" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="telephone_img" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="telephoneInfo" style="margin-top: 8px;"></h5>
                </li>
                <li>
                	<span><b>*</b>电子邮箱：</span>
                    <input type="text" name="email" placeholder="请输入邮箱(必填)" id="email" onblur="checkEmail()"/>
                    <div id="email_div" style="background: none; border-style: none;margin-top: 8px; display: none">
                    	<img id="email_img" src="css/easyUI/icons/ok.png">
                    </div>
                    <h5 id="emailInfo" style="margin-top: 8px;"></h5>
                </li>
            </ul>
			<p>
            	<input type="checkbox" value="check" id="agree"/>
                我已阅读并同意
                <a href="#">用户注册协议</a>
            </p>
            
            <input type="hidden" name="type" value="register"/>
            <input class="btn_submit" type="submit" id="submit" onclick="return checkSubmit()" value="立即注册"/>
       		</form>
        </div>
   	</div>
  </body>
</html>
