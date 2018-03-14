<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cac-resources" />
<html>
<head>
<title>Delete<fmt:message key="cac.title"/></title>
</head>
<body>
   
	<div id="content">
		<h1 class="titleOfPage">删除空调</h1>
		<div class="getBack"><a class="button" href="${pageContext.request.contextPath}/indexCac"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cac.id.title"/>:</td>
					<td>${cac.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cac.remark.title"/>:</td>
					<td>${cac.remark}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteCac?id=${cac.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
