
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>采用multipart提供的file.transfer方法上传文件</title>
</head>

<body>

<form name="Form2" action="${pageContext.request.contextPath}/fileUpload" method="post"  enctype="multipart/form-data">
 <h1>上传文件格式为txt，xlsx的数据格式</h1> 
 
<%  
        HttpSession sess = request.getSession();  
        String message = (String)sess.getAttribute("uploadMessage");  
      
    if(message == ""){  
        %>  
  
        <%  
    }else{  
        %>  
             <script type="text/javascript">  
                    alert("<%=message %>");  
             </script>  
        <%  
        sess.setAttribute("uploadMessage", "upload successfully");  
    }  
 %> 
 <c:forEach items="${allProjects } var="pro">
 ${$pro.name }
 </c:forEach>
<center>
<select id="project" name="project" path="allProjects" cssStyle="width:300px;">
<option></option>
<option>xxx</option>
<option>ggg</option>
<c:forEach items="${allProjects}" var="project">
<option value="${project}" >${project}</option>
</c:forEach>
</select> <br><br><br><br><br>

<input type="file" name="file">
<input type="submit" value="upload"/>
</center>
</form>

<br><br><br>
<br>
<center>
<input type="button" value="写入数据库" onclick="mm()">
</center>

</body>
</html>
