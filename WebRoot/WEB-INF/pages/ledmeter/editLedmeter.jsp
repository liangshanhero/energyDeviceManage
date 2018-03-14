<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledmeter-resources" />
<html>
<head>
<title>Edit<fmt:message key="ledmeter.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="ledmeter.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedmeter"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveLedmeter" method="POST" modelAttribute="ledmeter">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="ledmeter_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${ledmeter.id}&nbsp;
						<form:hidden id="ledmeter_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.number.title" />:</td>
					<td><form:input id="ledmeter_number" path="number" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.well.title" />:</td>
					<td><form:input id="ledmeter_well" path="well" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledmeter.storey.title" />:</td>
					<td><form:input id="ledmeter_storey" path="storey" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.title" />:</td>
					<td>
						<form:select id="relativeLedbuilding" name="relativeLedbuilding" path="relativeLedbuilding" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allLedbuildings}" var="ledbuilding">
						<form:option value="${ledbuilding}" >${ledbuilding.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
			<tr>
				<td valign="top"><fmt:message key="ledmeterdatas.title" />:</td>
				<td>
				<c:forEach items="${allLedmeterdatas}" var="ledmeterdata">
				<input type="checkbox" id="relativeLedmeterdatas" name="relativeLedmeterdatas" value="${ledmeterdata}"
				<c:forEach items="${havedLedmeterdatas}" var="havedLedmeterdata">
                <c:if test="${havedLedmeterdata == ledmeterdata}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${ledmeterdata.value}
				 </c:forEach>
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
