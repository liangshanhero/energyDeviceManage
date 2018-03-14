<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.project-resources" />
<html>
<head>
<title>Delete<fmt:message key="project.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="project.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexProject"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.id.title"/>:</td>
					<td>${project.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.name.title"/>:</td>
					<td>${project.name}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.type.title"/>:</td>
					<td>${project.type}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.status.title"/>:</td>
					<td>${project.status}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.province.title"/>:</td>
					<td>${project.province}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.city.title"/>:</td>
					<td>${project.city}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.area.title"/>:</td>
					<td>${project.area}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.address.title"/>:</td>
					<td>${project.address}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.detail.title"/>:</td>
					<td>${project.detail}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.remark.title"/>:</td>
					<td>${project.remark}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteProject?id=${project.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
