<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   <script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
  <body>
    This is my JSP page. <br>
    
    <form action="<%=path%>/customer/test/mysql">
    
    
    <table width="100%" border="1" cellpadding="0" cellspacing="1" class="tableLine">
				<tr>
					<td colspan=4 align=center class="tableLineBg">学生新增</td>
				</tr>
				<tr>
					<td>名字</td>
					<td><input type="text" name="name" class="input"></td>
					<td>生日</td>
					<td><input type="text" name="birthday" style="width: 90%" class="Wdate"     onClick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})"  /> </td>
				</tr>
			</table>
       <input type="submit" value="提交" />
    </form>
  </body>
</html>
