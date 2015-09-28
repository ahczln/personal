<%@page contentType="text/html;charset=utf-8"%>
<%@ include file="../common/header.inc"%>
<title>注册</title>
</head>
<style>
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>
</head>
<body>
	<div class="am-topbar am-topbar-inverse">
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
						<a href="${base}/regist.do">注册</a>
					</button>
				</div>
			</div>

			<div class="am-topbar-right">
				<button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
					<a href="${base}/regist.do">登录</a>
				</button>
			</div>
		</div>
	</div>

	<div class="header">
		<div class="am-g">
			<h1>
				注册
			</h1>
			<p>
				Integrated Development Environment
				<br />
				代码编辑，代码生成，界面设计，调试，编译
			</p>
		</div>
		<hr />
	</div>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<form method="post" class="am-form">
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label"><i
						class="am-icon-user am-icon-fw"></i> </span>
					<input type="text" class="am-form-field" placeholder="姓名">
				</div>
				<br/>
				<div class="am-input-group am-input-group-secondary">
					<span class="am-input-group-label"><i
						class="am-icon-user am-icon-fw"></i> </span>
					<input type="text" class="am-form-field" placeholder="邮箱">
				</div>
				<br/>
				<div class="am-input-group am-input-group-success">
					<span class="am-input-group-label"><i
						class="am-icon-user am-icon-fw"></i> </span>
					<input type="text" class="am-form-field" placeholder="QQ">
				</div>
				<br/>
				<div class="am-input-group am-input-group-warning">
					<span class="am-input-group-label"><i
						class="am-icon-user am-icon-fw"></i> </span>
					<input type="text" class="am-form-field" placeholder="密码">
				</div>
				<br/>
				<div class="am-input-group am-input-group-danger">
					<span class="am-input-group-label"><i
						class="am-icon-location-arrow am-icon-fw"></i> </span>
					<input type="text" class="am-form-field" placeholder="确认密码">
				</div>
				<br/>
				<div class="am-cf">
					<input type="submit" name="" value="提交"
						class="am-btn am-btn-primary am-btn-sm am-fl">
					<input type="submit" name="" value="重置 "
						class="am-btn am-btn-default am-btn-sm am-fr">
				</div>
			</form>
			<hr>
			<p>
				© 2014 AllMobilize, Inc. Licensed under MIT license.
			</p>
		</div>
	</div>
</body>
</html>