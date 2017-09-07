<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ app }/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- 将头部(_head.jsp)包含进来 -->
<%@include file="/_head.jsp" %>
<div id="content">

	<div id="search_div">
		<form method="post" action="#">
			<span class="input_span">商品名：<input type="text" name="name"/></span>
			<span class="input_span">商品种类：<input type="text" name="category"/></span>
			<span class="input_span">商品价格区间：<input type="text" name="minprice"/> - <input type="text" name="maxprice"/></span>
			<input type="submit" value="查 询">
		</form>
	</div>
	<div id="prod_content">
		<div class="prod_div">
			<a href="${ app }/prod_info.jsp"><img src="${ app }/img/prodlist/prod.jpg"></img></a>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="${ app }/cart.jsp">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div" style="margin-right: 0px;">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div class="prod_div" style="margin-right: 0px;">
			<img src="${ app }/img/prodlist/prod.jpg"></img>
			<div id="prod_name_div">
				华为荣耀6plus
			</div>
			<div id="prod_price_div">
				￥2099元
			</div>
			<div>
				<div id="gotocart_div">
					<a href="#">加入购物车</a>
				</div>
				<div id="say_div">
					133人评价
				</div>
			</div>
		</div>
		<div style="clear: both"></div>
	</div>

</div>
<!-- 将尾部(_foot.jsp)包含进来 -->
<%@include file="/_foot.jsp" %>
</body>
</html>
