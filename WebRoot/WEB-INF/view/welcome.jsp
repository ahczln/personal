<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'hello.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
</head>
<body>
	<form action="AddFoodStyle" enctype="multipart/form-data" method="post">    
    <div style="width:300px;">

        <s:textfield label="菜式名称" name="foodname"></s:textfield><br/>       

        <s:select name="foodstyle" list="list" label="菜式类别" listKey="Itemid" listValue="itemname"  > </s:select><br/>       

        <s:textfield label="菜式价格" name="price"></s:textfield><br/>        
        
        <s:file label="菜式图片" name="foodimg"></s:file><br/>        
        
        <s:textarea label="菜式标签" name="foodtab" cols="20"  cssStyle=""></s:textarea><br/>       
        
        <s:textfield label="菜式状态" name="state"></s:textfield><br/>        
        
        <s:submit value="添加"/>
        </div>        
    </form>

模拟构造上面的请求表单：

private String url="http://192.168.2.189:8080/MyOrderMeal/AddFoodStyle";

     HttpClient httpclient= new DefaultHttpClient();
     HttpPost httpPost= new HttpPost(url);
     MultipartEntity mulentity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        mulentity.addPart("foodname", new StringBody(foodname.getText().toString().trim()));
        mulentity.addPart("foodstyle", new StringBody(foodstyle.getText().toString().trim()));
        mulentity.addPart("price", new StringBody(foodprice.getText().toString().trim()));  

       //添加图片表单数据        
        FileBody filebody = new FileBody(this.image);        
        mulentity.addPart("foodimg",filebody );    
        mulentity.addPart("foodtab", new StringBody(foodtab.getText().toString().trim()));
        mulentity.addPart("state", new StringBody("1"));         
        httpPost.setEntity(mulentity);
        HttpResponse response =    httpclient.execute(httpPost);
        
        if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
        {
            makeToase("上传成功",true);
            if(this.image.exists())
            this.image.delete();
        }
        else
        {
            makeToase("上传失败",true);
        }

）
</body>
</html>