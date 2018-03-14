<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whstrategy-resources" />
<html>
<head>
<title>Delete<fmt:message key="whstrategy.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="whstrategy.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhstrategy?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.enable.title"/>:</td>
					<td>${whstrategy.enable}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.createDate.title"/>:</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${whstrategy.createDate}"/>&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whstrategy.remark.title"/>:</td>
					<td>${whstrategy.remark}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteWhstrategy?id=${whstrategy.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
