<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdevicedata-resources" />
<html>
<head>
<title>Edit<fmt:message key="whdevicedata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="whdevicedata.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhdevicedata"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveWhdevicedata" method="POST" modelAttribute="whdevicedata">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevicedata.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="whdevicedata_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${whdevicedata.id}&nbsp;
						<form:hidden id="whdevicedata_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevicedata.time.title" />:</td>
					<td>
						<input id="whdevicedata_time" name="time" type="text" value="<fmt:formatDate value="${whdevicedata.time}" pattern="yyyy-MM-dd HH:mm:ss"/>" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevicedata.value.title" />:</td>
					<td><form:input id="whdevicedata_value" path="value" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevicedata.isupdate.title" />:</td>
					<td><form:input id="whdevicedata_isupdate" path="isupdate" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevicedata.isio.title" />:</td>
					<td><form:input id="whdevicedata_isio" path="isio" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdatatype.title" />:</td>
					<td>
						<form:select id="relativeWhdatatype" name="relativeWhdatatype" path="relativeWhdatatype" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allWhdatatypes}" var="whdatatype">
						<form:option value="${whdatatype}" >${whdatatype.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevice.title" />:</td>
					<td>
						<form:select id="relativeWhdevice" name="relativeWhdevice" path="relativeWhdevice" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allWhdevices}" var="whdevice">
						<form:option value="${whdevice}" >${whdevice.number}</form:option>
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
