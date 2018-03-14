<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledmeter-resources" />
<html>
<head>
<title>Delete<fmt:message key="ledmeter.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="ledmeter.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedmeter"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.id.title"/>:</td>
					<td>${ledmeter.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.number.title"/>:</td>
					<td>${ledmeter.number}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.well.title"/>:</td>
					<td>${ledmeter.well}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.storey.title"/>:</td>
					<td>${ledmeter.storey}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteLedmeter?id=${ledmeter.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
