<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whstrategytype-resources" />
<html>
<head>
<title>Edit<fmt:message key="whstrategytype.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="whstrategytype.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhstrategytype?page=homePage"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveWhstrategytype" method="POST" modelAttribute="whstrategytype">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategytype.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="whstrategytype_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${whstrategytype.id}&nbsp;
						<form:hidden id="whstrategytype_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategytype.name.title" />:</td>
					<td><form:input id="whstrategytype_name" path="name" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td valign="top"><fmt:message key="whstrategydetails.title" />:</td>
					<td>
					 <c:forEach items="${allWhstrategydetails}" var="whstrategydetail">
					 <input type="checkbox" id="whstrategydetails" name="whstrategydetails" value="${whstrategydetail}"
					 <c:forEach items="${havedWhstrategydetails}" var="havedWhstrategydetail">
                	 <c:if test="${havedWhstrategydetail == whstrategydetail}">
                  	 checked="checked"
                	 </c:if>
               	 	 </c:forEach> />
				 	</c:forEach>
				</td>
				<c:if test="${editType=='new'}">
				<td><a  href="${pageContext.request.contextPath}/newWhstrategytype?id=${whstrategytype.id}&whstrategydetailPage=previousPage">上一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/newWhstrategytype?id=${whstrategytype.id}&whstrategydetailPage=nextPage">下一页</a></td>
				</c:if>
				<c:if test="${editType=='edit'}">
				<td><a  href="${pageContext.request.contextPath}/editWhstrategytype?id=${whstrategytype.id}&whstrategydetailPage=previousPage">上一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/editWhstrategytype?id=${whstrategytype.id}&whstrategydetailPage=nextPage">下一页</a></td>
				</c:if>
			</tr>
		</tbody>
		</table>
		<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId : 'save',event : 'onclick'}));</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
