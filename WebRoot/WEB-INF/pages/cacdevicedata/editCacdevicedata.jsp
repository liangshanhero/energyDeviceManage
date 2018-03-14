<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacdevicedata-resources" />
<html>
<head>
<title>Edit<fmt:message key="cacdevicedata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="cacdevicedata.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacdevicedata"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveCacdevicedata" method="POST" modelAttribute="cacdevicedata">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.cacdevice.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacdevicedata_cacdevice" path="cacdevice" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacdevicedata.cacdevice}&nbsp;
						<form:hidden id="cacdevicedata_cacdevice" path="cacdevice" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.cacrecordtime.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacdevicedata_cacrecordtime" path="cacrecordtime" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacdevicedata.cacrecordtime}&nbsp;
						<form:hidden id="cacdevicedata_cacrecordtime" path="cacrecordtime" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.value.title" />:</td>
					<td><form:input id="cacdevicedata_value" path="value" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevicedata.isreport.title" />:</td>
					<td><form:input id="cacdevicedata_isreport" path="isreport" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevice.title" />:</td>
					<td>
						<form:select id="relativeCacdevice" name="relativeCacdevice" path="relativeCacdevice" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allCacdevices}" var="cacdevice">
						<form:option value="${cacdevice}" >${cacdevice.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
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
		</tbody>
		</table>
		<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId : 'save',event : 'onclick'}));</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
