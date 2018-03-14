<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.ledbuilding-resources" />
<html>
<head>
<title>Edit<fmt:message key="ledbuilding.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="ledbuilding.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexLedbuilding"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveLedbuilding" method="POST" modelAttribute="ledbuilding">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="ledbuilding_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${ledbuilding.id}&nbsp;
						<form:hidden id="ledbuilding_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.name.title" />:</td>
					<td><form:input id="ledbuilding_name" path="name" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.well.title" />:</td>
					<td><form:input id="ledbuilding_well" path="well" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="ledbuilding.storey.title" />:</td>
					<td><form:input id="ledbuilding_storey" path="storey" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="project.title" />:</td>
					<td>
						<form:select id="relativeProject" name="relativeProject" path="relativeProject" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allProjects}" var="project">
						<form:option value="${project}" >${project.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
			<tr>
				<td valign="top"><fmt:message key="ledmeters.title" />:</td>
				<td>
				<c:forEach items="${allLedmeters}" var="ledmeter">
				<input type="checkbox" id="relativeLedmeters" name="relativeLedmeters" value="${ledmeter}"
				<c:forEach items="${havedLedmeters}" var="havedLedmeter">
                <c:if test="${havedLedmeter == ledmeter}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${ledmeter.number}
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
