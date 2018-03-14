<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdevice-resources" />
<html>
<head>
<title>Delete<fmt:message key="whdevice.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="whdevice.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhdevice"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevice.id.title"/>:</td>
					<td>${whdevice.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevice.number.title"/>:</td>
					<td>${whdevice.number}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteWhdevice?id=${whdevice.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
