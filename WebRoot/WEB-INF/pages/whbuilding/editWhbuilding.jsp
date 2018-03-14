<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whbuilding-resources" />
<html>
<head>
<title>Edit<fmt:message key="whbuilding.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="whbuilding.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhbuilding"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveWhbuilding" method="POST" modelAttribute="whbuilding">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whbuilding.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="whbuilding_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${whbuilding.id}&nbsp;
						<form:hidden id="whbuilding_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whbuilding.name.title" />:</td>
					<td><form:input id="whbuilding_name" path="name" cssStyle="width:300px;" /></td>
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
				<td valign="top"><fmt:message key="whdevices.title" />:</td>
				<td>
				<c:forEach items="${allWhdevices}" var="whdevice">
				<input type="checkbox" id="relativeWhdevices" name="relativeWhdevices" value="${whdevice}"
				<c:forEach items="${havedWhdevices}" var="havedWhdevice">
                <c:if test="${havedWhdevice == whdevice}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${whdevice.number}
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
