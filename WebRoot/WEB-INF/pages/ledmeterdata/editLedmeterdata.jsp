<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledmeterdata-resources" />
<html>
<head>
<title>Edit<fmt:message key="ledmeterdata.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="ledmeterdata.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedmeterdata"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveLedmeterdata" method="POST" modelAttribute="ledmeterdata">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeterdata.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="ledmeterdata_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${ledmeterdata.id}&nbsp;
						<form:hidden id="ledmeterdata_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeterdata.value.title" />:</td>
					<td><form:input id="ledmeterdata_value" path="value" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeterdata.time.title" />:</td>
					<td>
						<input id="ledmeterdata_time" name="time" type="text" value="<fmt:formatDate value="${ledmeterdata.time}" pattern="yyyy-MM-dd HH:mm:ss"/>" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.title" />:</td>
					<td>
						<form:select id="relativeLedmeter" name="relativeLedmeter" path="relativeLedmeter" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allLedmeters}" var="ledmeter">
						<form:option value="${ledmeter}" >${ledmeter.number}</form:option>
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
