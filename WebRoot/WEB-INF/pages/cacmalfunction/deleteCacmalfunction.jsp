<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacmalfunction-resources" />
<html>
<head>
<title>Delete<fmt:message key="cacmalfunction.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="cacmalfunction.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacmalfunction"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacmalfunction.cacrecordtime.title"/>:</td>
					<td>${cacmalfunction.cacrecordtime}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacmalfunction.cacdevice.title"/>:</td>
					<td>${cacmalfunction.cacdevice}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacmalfunction.status.title"/>:</td>
					<td>${cacmalfunction.status}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteCacmalfunction?cacrecordtime=${cacmalfunction.cacrecordtime}&cacdevice=${cacmalfunction.cacdevice}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
