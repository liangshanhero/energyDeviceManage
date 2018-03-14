<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whstrategy-resources" />
<html>
<head>
<title>List<fmt:message key="whstrategy.title"/></title>
</head>
<body>
	<div id="content">
		<h1>Manage<fmt:message key="whstrategy.title"/>s</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/newWhstrategy?&whstrategydetailPage=previousPage"><span><img src="${pageContext.request.contextPath}/images/icons/new.gif" /><fmt:message key="navigation.new"/> <fmt:message key="whstrategy.title"/></span></a></div>
		<div>
			<form action="${pageContext.request.contextPath}/indexWhstrategy?page=eachPageShow" method="post">
			 	选择每页显示<input type="text" id="pageSize" name="pageSize" style="height:15px;width:50px">条 
			 	<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>
		<div id="tablewrapper">
		<table id="listTable" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="thead">&nbsp;</th>
					<th class="thead"><fmt:message key="whstrategy.enable.title"/></th>
					<th class="thead"><fmt:message key="whstrategy.createDate.title"/></th>
					<th class="thead"><fmt:message key="whstrategy.remark.title"/></th>
					<th class="thead"><fmt:message key="whdevice.title"/></th>
					<th class="thead"><fmt:message key="whstrategydetails.title"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${whstrategys}" var="current" varStatus="i">
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
					<a title="<fmt:message key="navigation.view" />" href="${pageContext.request.contextPath}/selectWhstrategy?id=${current.id}&whstrategydetailPage=homePage"><img src="images/icons/view.gif" /></a>
					<a title="<fmt:message key="navigation.edit" />" href="${pageContext.request.contextPath}/editWhstrategy?id=${current.id}&whstrategydetailPage=previousPage"><img src="images/icons/edit.gif" /></a>
					<a title="<fmt:message key="navigation.delete" />" href="${pageContext.request.contextPath}/confirmDeleteWhstrategy?id=${current.id}"><img src="images/icons/delete.gif" /></a>
				</td>
				<td nowrap="nowrap" class="tabletd">${current.enable} &nbsp;</td>
				<td nowrap="nowrap" class="tabletd"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${current.createDate}"/>&nbsp;</td>
				<td nowrap="nowrap" class="tabletd">${current.remark} &nbsp;</td>
	       	    <td nowrap="nowrap" class="tabletd">
				<c:if test="${empty(current.whstrategydetails) }">
				<span style="color:red">暂无whstrategydetails</span>
				</c:if> 
				<c:set var="isDone" value="0"/>
				<c:forEach items="${current.whstrategydetails}" var="referenced" varStatus="i">
				<c:if test="${isDone == '0' }">
				<c:choose>
				<c:when test="${i.count <6 }">
				</c:when>
				<c:otherwise>
				<c:set var="isDone" value="1"/>
				<a href="${pageContext.request.contextPath}/selectWhstrategyWhstrategydetails?whstrategy_id=${current.id}&page=homePage">更多</a>
				</c:otherwise>
				</c:choose>
				</c:if>
				</c:forEach> &nbsp;</td>
			</tr>
		</c:forEach>
		</tbody>		</table>
		</div>
		<tr>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=homePage">首页</a></td>
			<c:if test="${totalPage>=9}">
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=previousPage">上一页</a></td>
			<c:if test="${prePage-3>0&&prePage+5<=totalPage}">
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage-3}">${prePage-3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage-2}">${prePage-2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage-1}">${prePage-1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage}">${prePage}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage+1}">${prePage+1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage+2}">${prePage+2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage+3}">${prePage+3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage+4}">${prePage+4}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${prePage+5}">${prePage+5}</a></td>
			</c:if>
			<c:if test="${prePage-3<=0}">
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=1">1</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=2">2</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=3">3</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=4">4</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=5">5</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=6">6</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=7">7</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=8">8</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=9">9</a></td>
			</c:if>
			<c:if test="${prePage+5>totalPage}">
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-7}">${totalPage-7}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-6}">${totalPage-6}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-5}">${totalPage-5}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-4}">${totalPage-4}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-3}">${totalPage-3}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-2}">${totalPage-2}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage-1}">${totalPage-1}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage}">${totalPage}</a></td>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=${totalPage+1}">${totalPage+1}</a></td>
			</c:if>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=nextPage">下一页</a></td>
			</c:if>
			<c:if test="${totalPage<9}">
			<% 
			   String pageCount2 = request.getAttribute("totalPage").toString();
			   int pageCount = Integer.parseInt(pageCount2);
			   for (int i = 1; i <= pageCount+1; i++) {
			%>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage&WantToPage=<%=i%>"><%=i%></a></td>
			<%}%>
			</c:if>
			<td><a href="${pageContext.request.contextPath}/indexWhstrategy?page=lastPage">尾页</a></td>
		</tr>
		<br>
		<tr>
			<td>当前为第${prePage+1}页</td>&nbsp&nbsp&nbsp
		</tr>每页显示${prePageSize}条数据 &nbsp&nbsp总共${totalPage+1}页
		<form action="${pageContext.request.contextPath}/indexWhstrategy?page=jumpPage" method="post">
		跳转到第<input type="text" id="WantToPage" name="WantToPage" style="height:15px;width:100px">页 
		<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
		</form>
	</div>
</body>

</html>
