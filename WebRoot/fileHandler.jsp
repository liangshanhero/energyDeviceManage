<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"">
	function writeIntoDb() {
		var dataType ="<%=session.getAttribute("selectValue")%>";
		if (dataType == "LED") {
			document.writeToDB.action = "${pageContext.request.contextPath}/readLEDDataIntoDB";
			alert("批处理LED数据");
		} else if (dataType == "CAC") {
			document.writeToDB.action = "${pageContext.request.contextPath}/cacDataPersistenceBYColumn";
			alert("批处理CAC数据");
		} else if (dataType == "CACMAl") {
			document.writeToDB.action = "${pageContext.request.contextPath}/cacMalfunctionPersistence";
			alert("批处理CAC故障数据");
		}
	}

	function check() {
		var checkbox = document.getElementById("ch");//选中checkbox的id；
		if (checkbox.checked == true) {//按钮已选中
			document.getElementById("sub").removeAttribute("disabled");//移除disabled
		} else {
			//按钮没选中
			document.getElementById("sub").disabled = "disabled";
		}
	}
</script>
</head>

<body>
	<!-- 上传文件 -->
	<form name="Form2"	action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
		<%
			HttpSession sess = request.getSession();
			String message = (String) sess.getAttribute("uploadMessage");
			if (message == "") {
		%>
		<%
			} else {
		%>
		<script type="text/javascript">  
                    alert("<%=message%>");
		</script>
		<%
			sess.setAttribute("uploadMessage", "upload successfully");
			}
		%>
		<center>
			<select name="select" id="select_k1" class="xla_k">
				<option value="CAC">CAC文件</option>
				<option value="LED">LED文件</option>
				<option value="CACMAL">CAC故障文件</option>
			</select><br> <br> <br> <input type="file" name="file">
			<input type="submit" value="上传" />
		</center>
	</form>
<!-- 处理文件 -->
	<%
		String uploadfile = (String) sess.getAttribute("uploadFile");
	%>
	<div>
		<center>
			<form name="writeToDB" method="post">
				<table>
					<input type="checkbox" id="ch" name="fileName"	value="<%=uploadfile%>" onclick="check()">
					<%=uploadfile%>
					<input type="submit" id="sub" disabled="disabled" name="wirteDBbutton" onclick="writeIntoDb();" value="写入数据库">
				</table>
			</form>
		</center>
	</div>
=======
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传文件并写入数据库</title>

<script type="text/javascript"">
	
	function writeIntoDb() {
	
		var dataType ="<%=session.getAttribute("selectValue")%>";
		
		if( dataType=="LED"){
	
		document.writeToDB.action="${pageContext.request.contextPath}/readLEDDataIntoDB";
	 	
		alert("批处理LED数据");
		}
		
		else
		if(dataType=="CAC"){
		document.writeToDB.action="${pageContext.request.contextPath}/cacDataPersistenceBYColumn";
		alert("批处理CAC数据");
		}
		else
		if(dataType=="CACMAl"){
		
		document.writeToDB.action="${pageContext.request.contextPath}/cacMalfunctionPersistence";
		
		alert("批处理CAC故障数据");
		}
		
		}
		
		function check(){
		
		  var checkbox = document.getElementById("ch");//选中checkbox的id；
		 
		   if(checkbox.checked==true){//按钮已选中
               document.getElementById("sub").removeAttribute("disabled");//移除disabled
            }
            else{
            //按钮没选中
               document.getElementById("sub").disabled="disabled";
            }
		} 
		
</script>
</head>

<body>
	<form name="Form2"
		action="${pageContext.request.contextPath}/fileUpload" method="post"
		enctype="multipart/form-data">
		<h1>上传文件格式为xlsx形式</h1>

		<%
			HttpSession sess = request.getSession();
			String message = (String) sess.getAttribute("uploadMessage");
			if (message == "") {
		%>
		<%
			} else {
			%>
		<script type="text/javascript">  
                    alert("<%=message %>");  
             </script>

		<%
			sess.setAttribute("uploadMessage", "upload successfully");
			}
		%>

		<center>
			<select name="select" id="select_k1" class="xla_k">
				<option value="CAC">CAC文件</option>
				<option value="LED">LED文件</option>
				<option value="CACMAL">CAC故障文件</option>
			</select><br> <br> <br> <input type="file" name="file">
			<input type="submit" value="上传" />
	</form>

	<%
           String uploadfile = (String) sess.getAttribute("uploadFile");
			%>
	<div>
		<form name="writeToDB" method="post">
			<table>
				<br>
				<br>
				<td>已上传文件： &nbsp&nbsp <input type="checkbox" id="ch"
					name="fileName" value="<%=uploadfile%>" onclick="check()">
					<%=uploadfile%> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp

					<input type="submit" id="sub" disabled="disabled"
					name="wirteDBbutton" onclick="writeIntoDb();" value="写入数据库">
				</td>
			</table>
		</form>
	</div>
	</center>
>>>>>>> branch 'master' of https://github.com/liangshanhero/energyDeviceManage.git
</body>
</html>
