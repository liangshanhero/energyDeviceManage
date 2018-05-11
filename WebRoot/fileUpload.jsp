<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>采用multipart提供的file.transfer方法上传文件</title>
</head>
<body>
	<form name="Form2"	action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
		上传文件格式为txt，xlsx的数据格式
		<center>
			<select name="select" id="select_k1" class="xla_k">
				<option value="中央空调">中央空调</option>
				<option value="LED">LED</option>
				<option value="热水">热水</option>
			</select>
			<input type="file"	name="file"> <input type="submit" value="upload" />
		</center>
	</form>

</body>
</html>
