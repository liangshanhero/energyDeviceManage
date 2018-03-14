<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacsensordata-resources" />
<html>
<head>
<title>Edit<fmt:message key="cacsensordata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="cacsensordata.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacsensordata"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveCacsensordata" method="POST" modelAttribute="cacsensordata">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.cacrecordtime.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacsensordata_cacrecordtime" path="cacrecordtime" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacsensordata.cacrecordtime}&nbsp;
						<form:hidden id="cacsensordata_cacrecordtime" path="cacrecordtime" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.cacsensor.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacsensordata_cacsensor" path="cacsensor" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacsensordata.cacsensor}&nbsp;
						<form:hidden id="cacsensordata_cacsensor" path="cacsensor" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.value.title" />:</td>
					<td><form:input id="cacsensordata_value" path="value" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensordata.isreport.title" />:</td>
					<td><form:input id="cacsensordata_isreport" path="isreport" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacrecordtime.title" />:</td>
					<td>
						<form:select id="relativeCacrecordtime" name="relativeCacrecordtime" path="relativeCacrecordtime" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allCacrecordtimes}" var="cacrecordtime">
						<form:option value="${cacrecordtime}" >${cacrecordtime.recordTime}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensor.title" />:</td>
					<td>
						<form:select id="relativeCacsensor" name="relativeCacsensor" path="relativeCacsensor" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allCacsensors}" var="cacsensor">
						<form:option value="${cacsensor}" >${cacsensor.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
		</tbody>
		</table>
		<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId : 'save',event : 'onclick'}));</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
