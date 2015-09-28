<%@ page language="java" pageEncoding="UTF-8"%>
<%
	request.setAttribute("base", request.getContextPath());
%>
<html class="no-js demo">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<meta name="renderer" content="webkit">
		<meta name="description" content="">
		<link rel="alternate icon" type="image/png" href="${base }/assets/i/favicon.png">
		<link rel="apple-touch-icon-precomposed" href="${base }/assets/i/app-icon72x72@2x.png">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${base }/assets/css/amazeui.min.css">
		<link rel="stylesheet" href="${base }/assets/css/amaze.min.css">
		<link rel="stylesheet" href="${base }/assets/css/app.css">
		<script src="${base }/assets/js/jquery.min.js"> </script>
		<script src="${base }/assets/js/handlebars.min.js"></script>
		<script src="${base }/assets/js/amazeui.min.js"> </script>
		<script src="${base }/assets/js/amazeui.widgets.helper.js"> </script>
		<script src="${base }/assets/js/app.js"> </script>
		<script type="text/javascript" src="${base }/ichartjs/ichart.1.2.js"></script>