<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cac-resources" />
<html>
<head>
<title>View<fmt:message key="cac.title"/></title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".div2").click(
						function() {
							$(this).next("div").slideToggle("slow").siblings(
									".div3:visible").slideUp("slow");
						});
			});
	function openurl(url) {
		var rframe = parent.document.getElementById("rightFrame");
		rframe.src = url;
	}
</script>
</head>


<body>

<div class="top1"></div>
    <div class="top2">
    <div class="title">能源管理系统
    <div style="clear:both;"></div>  
    </div>
    <div class="fr top-link">
			<a href="admin_list.html" target="mainCont" title="DeathGhost"><div
				class="adminIcon"></div><span>管理员：DeathGhost</span></a> 
		</div>

    </div>
    
    <div class="left">
		<div class="div1">
			
			
           <div class="div2">
				<div class="spgl"></div>
				用户管理
			</div>
			<div class="div3">
				<li><a class="a" href="javascript:void(0);"
					onClick="openurl('videoQuery.html');">添加用户</a></li>
				<li><a class="a" href="javascript:void(0);"
					onClick="openurl('uservideoQuery.html');">修改用户</a></li>
			   <li><a class="a" href="javascript:void(0);"
					onClick="openurl('uservideoQuery.html');">删除用户</a></li>
			</div>
			<div class="div2">
				<div class="spgl"></div>
				公司管理
			</div>
			<div class="div3">
				<ul>
					<li><a class="a" href="javascript:void(0);"
						onClick="openurl('documentQuery.html');">添加公司</a></li>
						<li><a class="a" href="javascript:void(0);"
						onClick="openurl('userdocumentQuery.html');">修改公司</a></li>
						<li><a class="a" href="javascript:void(0);"
						onClick="openurl('userdocumentQuery.html');">删除公司</a></li>
					
				</ul>
			</div>
			<div class="div2">
				<div class="spgl"></div>
				项目管理
			</div>
			<div class="div3">
				<ul>
					<li><a class="a" href="javascript:void(0);"
						onClick="openurl('classQuery.html');">添加项目</a></li>
						<li><a class="a" href="javascript:void(0);"
						onClick="openurl('classQuery.html');">修改项目</a></li>
						<li><a class="a" href="javascript:void(0);"
						onClick="openurl('classQuery.html');">删除项目</a></li>
						
				</ul>
			</div>
			<div class="div2">
				<div class="spgl"></div>
				数据上传
			</div>
			<div class="div3">
				<ul>
					<li><a class="a" href="javascript:void(0);"
						onClick="openurl('studentQuery.html');">数据上传</a></li>
				</ul>
			</div>
			
			<div class="div2">
				<div class="spgl"></div>
				数据查询
			</div>
			<div class="div3">

				<ul>
					<li><a class="a" href="javascript:void(0);"
						onClick="openurl('deletecomment.html');">数据展示</a></li>
					<li><a class="a" href="javascript:void(0);"
						onClick="openurl('useredit.html');">数据导出</a></li>
				</ul>

			</div>
			
			<a class="a1" href="login.html"><div class="div2">
					<div class="spgl"></div>
					退出后台
				</div></a>
		</div>
	</div>

	<div class="mainPart">
										<div id="content">
											<h1>
												<fmt:message key="navigation.view" />
												<fmt:message key="cacdevice.title" />
											</h1>
											<div class="navitem">
												<a class="button"
													href="${pageContext.request.contextPath}/indexCac?page=homePage"><span><img
														src="images/icons/back.gif" />
													<fmt:message key="navigation.back" /></span></a>
											</div>

								<form action="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=eachPageShow" method="post">
									选择每页显示<input type="text" id="pageSize" name="pageSize" style="height:15px;width:50px">条 
									<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
								</form>
								<table cellpadding="0" cellspacing="0" id="viewTable">
												<thead>
													<tr>
														<th class="thead"><fmt:message key="cacdevice.id.title"/></th>
														<th class="thead"><fmt:message key="cacdevice.name.title"/></th>
													</tr>
												</thead>
											<tbody>
												<c:forEach items="${cacdevices}" var="current"  varStatus="i">	
												<c:choose>
												<c:when test="${(i.count) % 2 == 0}">
					    						<c:set var="rowclass" value="rowtwo"/>
												</c:when>
												<c:otherwise>
					    						<c:set var="rowclass" value="rowone"/>
												</c:otherwise>
												</c:choose>
												<tr class="${rowclass}">
												<td>${current.id}&nbsp;</td>
												<td>${current.name}&nbsp;</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<tr>
											<td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=homePage">首页</a></td>
											<td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=previousPage">上一页</a></td>
											<c:if test="${cacdeviceTotalPage>=7}">
											<c:if test="${cacdevicePageNumber-2>0&&cacdevicePageNumber+4<=cacdeviceTotalPage}">
											<%String pageCount2 = request.getAttribute("cacdevicePageNumber").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for(int i=pageCount-2;i<pageCount+5;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
											<%}%>
										</c:if>
										<c:if test="${cacdevicePageNumber-2<=0}">
										 <%for(int i=1;i<8;i++){%>
											<td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
			   							</c:if>		    
			   							 <c:if test="${cacdevicePageNumber+4>cacdeviceTotalPage }">
			   							 <%String pageCount2 = request.getAttribute("cacdeviceTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
			   							 for(int i=pageCount-5;i<pageCount+2;i++){%>
			   							 <td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			    						<%}%>
			   							 </c:if>
			   							 </c:if>			
										<c:if test="${cacdeviceTotalPage<7}">
										<% 
											String pageCount2 = request.getAttribute("cacdeviceTotalPage").toString();
											int pageCount = Integer.parseInt(pageCount2);
											for (int i = 1; i <= pageCount+1; i++) {%>
										 <td><a href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
										<%}%>
										</c:if>
										<td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=nextPage">下一页</a></td>
										<td><a  href="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=lastPage">尾页</a> 	</td>
								</tr><br>
									当前为第${cacdevicePageNumber+1}页         每页显示${cacdevicePageSize}条      总共${cacdeviceTotalPage+1}页<br>
									<form action="${pageContext.request.contextPath}/selectCacCacdevices?cac_id=${cac.id}&page=jumpPage" method="post">
									跳转到第<input type="text" id="WantToPage" name="WantToPage" style="height:15px;width:100px">页 
									<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
								</form>

		</div>
	</div>
</body>

</html>