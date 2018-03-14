<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacsensordata-resources" />
<html>
<head>
<title>Delete<fmt:message key="cacsensordata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="cacsensordata.title"/>Details</h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacsensordata"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.cacrecordtime.title"/>:</td>
					<td>${cacsensordata.cacrecordtime}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.cacsensor.title"/>:</td>
					<td>${cacsensordata.cacsensor}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.value.title"/>:</td>
					<td>${cacsensordata.value}&nbsp;</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.isreport.title"/>:</td>
					<td>${cacsensordata.isreport}&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/deleteCacsensordata?cacrecordtime=${cacsensordata.cacrecordtime}&cacsensor=${cacsensordata.cacsensor}"><span><img src="images/icons/delete.gif" /><fmt:message key="navigation.delete"/></span></a></div>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
