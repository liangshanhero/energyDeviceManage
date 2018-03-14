
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>写文件数据到数据库中</title>

</head>
<body>
	<form name="Form2"
		action="${pageContext.request.contextPath}/cacDataPersistenceBYColumn"
		method="post" enctype="multipart/form-data">
		<h1>读取CAC文件数据</h1>

		<center>
		<input type="submit" value="持久化中央空调数据">
	</form>
	</center>

	<form name="Form3"
		action="${pageContext.request.contextPath}/readWHDataIntoDB"
		method="post">
		<h1>读取WH文件数据</h1>
		<center>
		</select><br> <br> <br> <input type="submit" value="持久化热水数据">
	</form>
	
	<form name="Form4"
		action="${pageContext.request.contextPath}/cacMalfunctionPersistence"
		method="post">
		<h1>读取CAC文件数据</h1>
		<center>
		<br> <br> <br> <input type="submit" value="持久化空调故障数据">
	</form>

	<form name="Form5"
		action="${pageContext.request.contextPath}/readLEDDataIntoDB"
		method="post">
		<h1>读取LED热水文件数据</h1>
		<center>
		<br> <br> <br> <input type="submit" value="持久化LED数据">
	</form>
	
</body>
</html>
