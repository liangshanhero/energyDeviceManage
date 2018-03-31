<%@page language="java" isELIgnored="false" contentType="text/html;
 charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.company-resources" />

<html>
<head>
<title>Delete<fmt:message key="company.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="company.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCompany"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.id.title"/>:</td>
					<td>${company.id}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.name.title"/>:</td>
					<td>${company.name}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.phone.title"/>:</td>
					<td>${company.phone}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.fax.title"/>:</td>
					<td>${company.fax}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.postcode.title"/>:</td>
					<td>${company.postcode}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.address.title"/>:</td>
					<td>${company.address}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.website.title"/>:</td>
					<td>${company.website}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.personduty.title"/>:</td>
					<td>${company.personduty}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.detail.title"/>:</td>
					<td>${company.detail}&nbsp;</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.remark.title"/>:</td>
					<td>${company.remark}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteCompany?id=${company.id}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
