<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.staff-resources" />
<html>
<head>
<title>Delete<fmt:message key="staff.title"/></title>
</head>
<body>
	<div id="content">
	<%-- 	<h1><fmt:message key="staff.title"/>Details</h1> --%>
		<%-- <tr>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=homePage">首页</a></td>
			
			<c:if test="${totalPage>=9}">
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=previousPage">上一页</a></td>
			
			<c:if test="${prePage-3>0&&prePage+5<=totalPage}">
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage-3}">${prePage-3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage-2}">${prePage-2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage-1}">${prePage-1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage}">${prePage}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage+1}">${prePage+1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage+2}">${prePage+2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage+3}">${prePage+3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage+4}">${prePage+4}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${prePage+5}">${prePage+5}</a></td>
			</c:if>
			<c:if test="${prePage-3<=0}">
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=1">1</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=2">2</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=3">3</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=4">4</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=5">5</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=6">6</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=7">7</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=8">8</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=9">9</a></td>
			</c:if>
			<c:if test="${prePage+5>totalPage}">
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-7}">${totalPage-7}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-6}">${totalPage-6}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-5}">${totalPage-5}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-4}">${totalPage-4}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-3}">${totalPage-3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-2}">${totalPage-2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage-1}">${totalPage-1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage}">${totalPage}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=${totalPage+1}">${totalPage+1}</a></td>
			</c:if>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=nextPage">下一页</a></td>
			</c:if>
			<c:if test="${totalPage<9}">
			<% 
			   String pageCount2 = request.getAttribute("totalPage").toString();
			   int pageCount = Integer.parseInt(pageCount2);
			   for (int i = 1; i <= pageCount+1; i++) {
			%>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			<%}%>
			</c:if>
			<td><a href="${pageContext.request.contextPath}/indexStaff?page=lastPage">尾页</a></td>
		</tr>
		<br>
		<tr>
			<td>当前为第${prePage+1}页</td>&nbsp&nbsp&nbsp
		</tr>每页显示${prePageSize}条数据 &nbsp&nbsp总共${totalPage+1}页
		
		</form> --%>
		
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.id.title"/>:</td>
					<td>${staff.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.name.title"/>:</td>
					<td>${staff.name}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.duty.title"/>:</td>
					<td>${staff.duty}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.token.title"/>:</td>
					<td>${staff.token}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.type.title"/>:</td>
					<td>${staff.type}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.status.title"/>:</td>
					<td>${staff.status}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.level.title"/>:</td>
					<td>${staff.level}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.loginname.title"/>:</td>
					<td>${staff.loginname}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.password.title"/>:</td>
					<td>${staff.password}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.remark.title"/>:</td>
					<td>${staff.remark}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteStaff?id=${staff.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
