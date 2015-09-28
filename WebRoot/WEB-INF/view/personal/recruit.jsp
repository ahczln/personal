<%@page contentType="text/html;charset=utf-8"%>
<%@ include file="../common/header.inc"%>
<title>招聘</title>
</head>
<style>
.get {
	background: #1E5B94;
	color: #fff;
	text-align: center;
	padding: 100px 0;
}

.get-title {
	font-size: 200%;
	border: 2px solid #fff;
	padding: 20px;
	display: inline-block;
}

.get-btn {
	background: #fff;
}

.detail {
	background: #fff;
}

.detail-h2 {
	text-align: center;
	font-size: 150%;
	margin: 40px 0;
}

.detail-h3 {
	color: #1f8dd6;
}

.detail-p {
	color: #7f8c8d;
}

.detail-mb {
	margin-bottom: 30px;
}

.hope {
	background: #0bb59b;
	padding: 50px 0;
}

.hope-img {
	text-align: center;
}

.hope-hr {
	border-color: #149C88;
}

.hope-title {
	font-size: 140%;
}

.about {
	background: #fff;
	padding: 40px 0;
	color: #7f8c8d;
}

.about-color {
	color: #34495e;
}

.about-title {
	font-size: 180%;
	padding: 30px 0 50px 0;
	text-align: center;
}

.footer p {
	color: #7f8c8d;
	margin: 0;
	padding: 15px 0;
	text-align: center;
	background: #2d3e50;
}
</style>
<script type="text/javascript">

$(document).ready(function() {

});
$(function() {
	var $slider = $('#demo-slider-0');
	var counter = 0;
	var getSlide = function() {
		counter++;
		return '<li><img src="http://s.amazeui.org/media/i/demos/bing-'
				+ (Math.floor(Math.random() * 4) + 1) + '.jpg" />'
				+ '<div class="am-slider-desc">动态插入的 slide ' + counter
				+ '</div></li>';
	};

	$('.js-demo-slider-btn').on(
			'click',
			function() {
				var action = this.getAttribute('data-action');
				if (action === 'add') {
					$slider.flexslider('addSlide', getSlide());
				} else {
					var count = $slider.flexslider('count');
					count > 1
							&& $slider.flexslider('removeSlide', $slider
									.flexslider('count') - 1);
				}
			});

});
</script>
<body>
	<header class="am-topbar am-topbar-inverse">
	<h1 class="am-topbar-brand">
		<a href="#">我的网站</a>
	</h1>

	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: '#doc-topbar-collapse'}">
		<span class="am-sr-only">导航切换</span>
		<span class="am-icon-bars"></span>
	</button>

	<div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
		<ul class="am-nav am-nav-pills am-topbar-nav">
			<li class="">
				<a href="${base}/personal/index.do">首页</a>
			</li>
			<li>
				<a href="${base}/personal/recruit.do">招聘</a>
			</li>
			<li class="am-dropdown" data-am-dropdown>
				<a class="am-dropdown-toggle" data-am-dropdown-toggle
					href="javascript:;"> </span> </a>
				<ul class="am-dropdown-content">
					<li class="am-dropdown-header">
						标题
					</li>
					<li>
						<a href="#">1. 去月球</a>
					</li>
					<li class="am-active">
						<a href="#">2. 去火星</a>
					</li>
					<li>
						<a href="#">3. 还是回地球</a>
					</li>
					<li class="am-disabled">
						<a href="#">4. 下地狱</a>
					</li>
					<li class="am-divider"></li>
					<li>
						<a href="#">5. 桥头一回首</a>
					</li>
				</ul>
			</li>
		</ul>

		<form class="am-topbar-form am-topbar-left am-form-inline"
			role="search">
			<div class="am-form-group">
				<input type="text" class="am-form-field am-input-sm"
					placeholder="搜索">
			</div>
		</form>

		<div class="am-topbar-right">
			<div class="am-dropdown" data-am-dropdown="{boundary: '.am-topbar'}">
				<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
					<a href="${base}/personal/regist.do">注册</a>
				</button>
			</div>
		</div>

		<div class="am-topbar-right">
			<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
				<a href="${base}/personal/regist.do">登录</a>
			</button>
		</div>
	</div>

	<div class="get">
		<div class="am-g">
			<div class="am-u-lg-12">
				<h1 class="get-title">
					Amaze UI - HTML5 跨屏前端框架
				</h1>

				<p>
					期待你的参与，共同打造一个简单易用的前端框架
				</p>

				<p>
					<a href="http://amazeui.org" class="am-btn am-btn-sm get-btn">获取新get技能√</a>
				</p>
			</div>
		</div>
	</div>

	<div class="hope">
		<div class="am-g am-container">
			<div class="am-u-lg-4 am-u-md-6 am-u-sm-12 hope-img">
				<img src="${base}/img/landing.gif" alt=""
					data-am-scrollspy="{animation:'slide-left', repeat: false}">
				<hr class="am-article-divider am-show-sm-only hope-hr">
			</div>
			<div class="am-u-lg-8 am-u-md-6 am-u-sm-12">
				<h2 class="hope-title">
					同我们一起打造你的前端框架
				</h2>

				<p>
					在知识爆炸的年代，我们不愿成为知识的过客，拥抱开源文化，发挥社区的力量，参与到Amaze Ui开源项目能获得自我提升。
				</p>
			</div>
		</div>
	</div>

	<div class="about">
		<div class="am-g am-container">
			<div class="am-u-lg-12">
				<h2 class="about-title about-color">
					Amaze UI 崇尚开放、自由，非常欢迎大家的参与
				</h2>

				<div class="am-g">
					<div class="am-u-lg-6 am-u-md-4 am-u-sm-12">
						<form class="am-form">
							<label for="name" class="about-color">
								你的姓名
							</label>
							<input id="name" type="text">
							<br />
							<label for="email" class="about-color">
								你的邮箱
							</label>
							<input id="email" type="email">
							<br />
							<label for="message" class="about-color">
								你的留言
							</label>
							<textarea id="message"></textarea>
							<br />
							<button type="submit" class="am-btn am-btn-primary am-btn-sm">
								<i class="am-icon-check"></i> 提 交
							</button>
						</form>
						<hr class="am-article-divider am-show-sm-only">
					</div>

					<div class="am-u-lg-6 am-u-md-8 am-u-sm-12">
						<h4 class="about-color">
							关于我们
						</h4>

						<p>
							AllMobilize Inc (美通云动科技有限公司)
							由前微软美国总部IE浏览器核心研发团队成员及移动互联网行业专家在美国西雅图创立，旨在解决网页在不同移动设备屏幕上的适配问题。基于国际专利技术并结合最前沿的HTML5技术，云适配解决方案可以帮助企业快速将桌面版网站适配到各种移动设备终端的屏幕上，不仅显著地提高了企业网站的用户体验以及销售转化率，而且大幅度地节省了企业开发和维护移动网站的费用。
						</p>
						<h4 class="about-color">
							团队介绍
						</h4>

						<p>
							AllMobilize Inc
							获得了微软创投孵化器的支持，其领先科技已得到全球多家企业及机构的认可与信赖，客户包括全球500强企业、美国政府、国内政府机关、国内外上市公司、以及互联网标准化组织W3C。
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer">
	<p>
		© 2014
		<a href="http://www.yunshipei.com" target="_blank">AllMobilize,
			Inc.</a> Licensed under
		<a href="http://opensource.org/licenses/MIT" target="_blank">MIT
			license</a>. by the AmazeUI Team.
	</p>
	</footer>

	<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="${base}/assets/js/jquery.min.js">
</script>
	<!--<![endif]-->
	<script src="${base}/assets/js/amazeui.min.js">
</script>

</body>
</html>