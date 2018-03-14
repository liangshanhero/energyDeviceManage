<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whstrategydetail-resources" />
<html>
<head>
<title>Edit<fmt:message key="whstrategydetail.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="whstrategydetail.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhstrategydetail?page=homePage"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveWhstrategydetail" method="POST" modelAttribute="whstrategydetail">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategydetail.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="whstrategydetail_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${whstrategydetail.id}&nbsp;
						<form:hidden id="whstrategydetail_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategydetail.max.title" />:</td>
					<td><form:input id="whstrategydetail_max" path="max" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategydetail.min.title" />:</td>
					<td><form:input id="whstrategydetail_min" path="min" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.title" />:</td>
					<td>
						<option></option>
						<c:forEach items="${allWhstrategys}" var="whstrategy">
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategytype.title" />:</td>
					<td>
						<form:select id="whstrategytype" name="whstrategytype" path="whstrategytype" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allWhstrategytypes}" var="whstrategytype">
						<form:option value="${whstrategytype}" >${whstrategytype.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
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
