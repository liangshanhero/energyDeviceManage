<%@page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacdevice-resources" />
<html>
<head>
</head>
<body>
	<div id="content">
		<input type="button" value="本地文件上传"
			onclick="window.location.href='fileUpload.jsp'"><br>
		<!--    <input type ="button" value="文件数据写入数据库" onclick="window.location.href='writeDataIntoDB.jsp'"> -->

	</div>
</body>
</html>