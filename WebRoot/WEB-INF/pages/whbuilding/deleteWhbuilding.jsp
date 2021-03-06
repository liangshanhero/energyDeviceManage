<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whbuilding-resources" />
<html>
<head>
<title>Delete<fmt:message key="whbuilding.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="whbuilding.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhbuilding"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label2" valign="top"><fmt:message key="whbuilding.id.title"/>:</td>
					<td>${whbuilding.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="whbuilding.name.title"/>:</td>
					<td>${whbuilding.name}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteWhbuilding?id=${whbuilding.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
