<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledmeterdata-resources" />
<html>
<head>
<title>Delete<fmt:message key="ledmeterdata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="ledmeterdata.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedmeterdata"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeterdata.id.title"/>:</td>
					<td>${ledmeterdata.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeterdata.value.title"/>:</td>
					<td>${ledmeterdata.value}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeterdata.time.title"/>:</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ledmeterdata.time}"/>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteLedmeterdata?id=${ledmeterdata.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
