<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledbuilding-resources" />
<html>
<head>
<title>Delete<fmt:message key="ledbuilding.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="ledbuilding.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedbuilding"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.id.title"/>:</td>
					<td>${ledbuilding.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.name.title"/>:</td>
					<td>${ledbuilding.name}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.well.title"/>:</td>
					<td>${ledbuilding.well}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.storey.title"/>:</td>
					<td>${ledbuilding.storey}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteLedbuilding?id=${ledbuilding.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
