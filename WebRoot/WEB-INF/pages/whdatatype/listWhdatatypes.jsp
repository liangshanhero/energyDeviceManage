<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdatatype-resources" />
<html>
<head>
<title>List<fmt:message key="whdatatype.title"/></title>
</head>
<body>
	<div id="content">
		<h1>Manage<fmt:message key="whdatatype.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newWhdatatype"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="whdatatype.title"/></span></a></div>
		<div>
			<form action="${pageContext.request.contextPath}/indexWhdatatype?page=eachPageShow" method="post">
			 	选择每页显示<input type="text" id="pageSize" name="pageSize" style="height:15px;width:50px">条 
			 	<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="whdatatype.id.title"/></th>
					<th class="thead"><fmt:message key="whdatatype.name.title"/></th>
					<th class="thead"><fmt:message key="whdevices.title"/></th>
					<th class="thead"><fmt:message key="whdevicedatas.title"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${whdatatypes}" var="current" varStatus="i">
			<c:choose>
			<c:when test="${(i.count) % 2 == 0}">
			<c:set var="rowclass" value="rowtwo"/>
			</c:when>
			<c:otherwise>
			<c:set var="rowclass" value="rowone"/>
			</c:otherwise>
			</c:choose>	
			<tr class="${rowclass}">
				<td nowrap="nowrap" class="tabletd">
					<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectWhdatatype?id=${current.id}&whdevicePage=homePage&whdevicedataPage=homePage"><img src="images/icons/view.gif" /></a>
					<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editWhdatatype?id=${current.id}"><img src="images/icons/edit.gif" /></a>
					<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteWhdatatype?id=${current.id}"><img src="images/icons/delete.gif" /></a>
				</td>
				<td nowrap="nowrap" class="tabletd">${current.id} &nbsp;</td>
				<td nowrap="nowrap" class="tabletd">${current.name} &nbsp;</td>
				<td nowrap="nowrap" class="tabletd">
				<c:if test="${empty(current.relativeWhdevices) }">
				<span style="color:red">暂无whdevice</span>
				</c:if> 
				<c:set var="isDone" value="0"/> 
				<c:forEach items="${current.relativeWhdevices}" var="referenced" varStatus="i">
				<c:if test="${isDone == '0' }">
				<c:choose>
				<c:when test="${i.count <6 }">
				${referenced.number}<br />
				</c:when>
				<c:otherwise>
				<c:set var="isDone" value="1"/>
				<a href="${pageContext.request.contextPath}/selectWhdatatypeWhdevices?whdatatype_id=${current.id}&page=homePage">更多</a>
				</c:otherwise>
				</c:choose>
				</c:if>
				</c:forEach> &nbsp;</td>
	       	    <td nowrap="nowrap" class="tabletd">
				<c:if test="${empty(current.relativeWhdevicedatas) }">
				<span style="color:red">暂无whdevicedatas</span>
				</c:if> 
				<c:set var="isDone" value="0"/>
				<c:forEach items="${current.relativeWhdevicedatas}" var="referenced" varStatus="i">
				<c:if test="${isDone == '0' }">
				<c:choose>
				<c:when test="${i.count <6 }">
				${referenced.value}<br />
				</c:when>
				<c:otherwise>
				<c:set var="isDone" value="1"/>
				<a href="${pageContext.request.contextPath}/selectWhdatatypeWhdevicedatas?whdatatype_id=${current.id}&page=homePage">更多</a>
				</c:otherwise>
				</c:choose>
				</c:if>
				</c:forEach> &nbsp;</td>
			</tr>
		</c:forEach>
		</tbody>		</table>
		</div>
		<tr>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=homePage">首页</a></td>
			<c:if test="${totalPage>=9}">
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=previousPage">上一页</a></td>
			<c:if test="${prePage-3>0&&prePage+5<=totalPage}">
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage-3}">${prePage-3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage-2}">${prePage-2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage-1}">${prePage-1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage}">${prePage}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage+1}">${prePage+1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage+2}">${prePage+2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage+3}">${prePage+3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage+4}">${prePage+4}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${prePage+5}">${prePage+5}</a></td>
			</c:if>
			<c:if test="${prePage-3<=0}">
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=1">1</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=2">2</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=3">3</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=4">4</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=5">5</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=6">6</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=7">7</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=8">8</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=9">9</a></td>
			</c:if>
			<c:if test="${prePage+5>totalPage}">
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-7}">${totalPage-7}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-6}">${totalPage-6}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-5}">${totalPage-5}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-4}">${totalPage-4}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-3}">${totalPage-3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-2}">${totalPage-2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage-1}">${totalPage-1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage}">${totalPage}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=${totalPage+1}">${totalPage+1}</a></td>
			</c:if>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=nextPage">下一页</a></td>
			</c:if>
			<c:if test="${totalPage<9}">
			<% 
			   String pageCount2 = request.getAttribute("totalPage").toString();
			   int pageCount = Integer.parseInt(pageCount2);
			   for (int i = 1; i <= pageCount+1; i++) {
			%>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			<%}%>
			</c:if>
			<td><a href="${pageContext.request.contextPath}/indexWhdatatype?page=lastPage">尾页</a></td>
		</tr>
		<br>
		<tr>
			<td>当前为第${prePage+1}页</td>&nbsp&nbsp&nbsp
		</tr>每页显示${prePageSize}条数据 &nbsp&nbsp总共${totalPage+1}页
		<form action="${pageContext.request.contextPath}/indexWhdatatype?page=jumpPage" method="post">
		跳转到第<input type="text" id="WantToPage" name="WantToPage" style="height:15px;width:100px">页 
		<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
		</form>
	</div>
</body>

</html>
