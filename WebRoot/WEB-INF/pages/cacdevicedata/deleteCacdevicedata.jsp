<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacdevicedata-resources" />
<html>
<head>
<title>Delete<fmt:message key="cacdevicedata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="cacdevicedata.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacdevicedata"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.cacdevice.title"/>:</td>
					<td>${cacdevicedata.cacdevice}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.cacrecordtime.title"/>:</td>
					<td>${cacdevicedata.cacrecordtime}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.value.title"/>:</td>
					<td>${cacdevicedata.value}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.isreport.title"/>:</td>
					<td>${cacdevicedata.isreport}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteCacdevicedata?cacdevice=${cacdevicedata.cacdevice}&cacrecordtime=${cacdevicedata.cacrecordtime}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
