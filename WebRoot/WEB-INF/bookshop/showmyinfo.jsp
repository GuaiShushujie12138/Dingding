<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人信息</title>
    
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
	<link rel="stylesheet" href="css/newmain.css" />
	<script type="text/javascript" src="http://127.0.0.1:8080/MyBookShop/js/showmyinfo.js" charset="utf-8"></script>

  </head>
  
  <body>
  		<!--顶部-->
	<div class="top">
    	<div class="top_center">
            <ul class="top_bars">
            	<li><a href="/MyBookShop/ToIndex">首页</a>|</li>
            	<li><a href="/MyBookShop/ToIndex?type=exit">安全退出</a>|</li>
                <li><a href="/MyBookShop/ToShowOrder?type=all">我的订单<span class="jt_down"></span></a>|</li>
                <li><a href="#">关注杰普<span class="jt_down"></span></a>|</li>
                <li><a href="#">网站导航<span class="jt_down"></span></a></li>
            </ul>
        </div>
    </div>
    <!--头部-->
    <div class="header3">
    	<a href="#"><img src="images/logo.png"></a>
        <div class="h3_right">
            <div class="tsc">
            	去购物车结算
                <span class="sj_right"></span>
            </div>
        </div>
    </div>
    
    <div class="container4">
    	<div class="register_box">
    			<div class="head" align="center">
    				<h2 style="color: red; size: 17">个人信息</h2>
    			</div>
    			<div class="security">
    				<form action="/MyBookShop/UserDeal" method="post">
    					<ul class="list">
    						<li>	
    								<input type="hidden" value="${login_user.id}" name="id"/>
    								<input id="J_euserName" class="long" readonly="readonly" name="username" value="${login_user.name}" type="text" />
    								<div class="name">用户名:</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    								<div id="passwddiv" style="background: none; border-style: none;margin-top: 8px; display: none">
                    					<img id="passwdimg" src="css/easyUI/icons/no.png">
                   					</div>
                    				<h5 id="passwdInfo" style="margin-top: 8px;"></h5>
    						</li>
    						<%-- <li>
    								<input id="passwd" class="long" name="passwd" value="${login_user.password}"  onblur="checkPasswd()" type="text"/>
    								<div class="name">密码:</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    								<div id="passwddiv" style="background: none; border-style: none;display: none">
                    					<img id="passwdimg" src="css/easyUI/icons/no.png">
                    					<span id="passwdInfo" style="font-size: 10px"></span>
                   					</div>
    						</li> --%>
    						<li>
    								<input id="zip" class="long" name="zip" value="${login_user.zip}" onblur="checkZip()" type="text" />
    								<div class="name">邮编:</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    								<div id="zip_div" style="background: none; border-style: none; display: none">
                    					<img id="passwdimg" src="css/easyUI/icons/no.png">
                   					</div>
                    				<h5 id="zipInfo" ></h5>
    						</li>
    						<li>
    								<input id="address" class="long" name="address" value="${login_user.address}" onblur="checkAddress()" type="text" />
    								<div class="name">地址:</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    								<div id="address_div" style="background: none; border-style: none; display: none">
                    					<img id="passwdimg" src="css/easyUI/icons/no.png">
                   					</div>
                    				<h5 id="addressInfo"></h5>
    						</li>
    						<li>
    								<input class="long" id="telephone" name="phone" value="${login_user.phone}" onblur="checkTel()" type="text" />
    								<div class="name">电话:</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    								<div id="telephone_div" style="background: none; border-style: none; display: none">
                    					<img id="passwdimg" src="css/easyUI/icons/no.png">
                   					</div>
                    				<h5 id="telephoneInfo" ></h5>
    						</li>
    						<li>
    								<input class="long" id="email" name="email" value="${login_user.email}" onblur="checkEmail()" type="text" />
    								<div class="name">电子邮箱:</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    								<div id="email_div" style="background: none; border-style: none;display: none">
                    					<img id="passwdimg" src="css/easyUI/icons/no.png">
                   					</div>
                    				<h5 id="emailInfo" ></h5>
    						</li>
    						<li>
    								<input onclick="return checkModify()" onmouseover="over()" onmouseout="out()" id="modify" class="long" style="width:70px;font-size: large;cursor: pointer;text-align: center;line-height: 40px; background: #F75D08; color:white;"  value="修改" type="submit" />
    								<div class="name">&nbsp;</div>
    								<span id="J_eUserNameTipImg" class="icon"></span>
    								<div id="J_eUserNameTipInfo" class="ltip"></div>
    								<span class="placeholder" style="position: absolute;z-index: 20;color: rgb(153,153,153);top: 14px;left: 345px;line-height: 37px;"></span>
    						</li>
    						
    					</ul>
    				</form>
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
            <p>沪ICP备14033591号-8 杰普briup.com版权所有 杰普软件科技有限公司 </p>
          	<img src="images/police.png">
        </div>
    </div>
  </body>
</html>
