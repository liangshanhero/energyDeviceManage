<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cac-resources" />
<html>
<head>
<title>Edit<fmt:message key="cac.title"/></title>
</head>
<body>

	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="cac.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCac"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveCac" method="POST" modelAttribute="cac">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cac.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cac_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cac.id}&nbsp;
						<form:hidden id="cac_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cac.remark.title" />:</td>
					<td><form:input id="cac_remark" path="remark" cssStyle="width:300px;" /></td>
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
				<td valign="top"><fmt:message key="cacdevices.title" />:</td>
				<td>
				<c:forEach items="${allCacdevices}" var="cacdevice">
				<input type="checkbox" id="relativeCacdevices" name="relativeCacdevices" value="${cacdevice}"
				<c:forEach items="${havedCacdevices}" var="havedCacdevice">
                <c:if test="${havedCacdevice == cacdevice}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${cacdevice.name}
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td valign="top"><fmt:message key="cacsensors.title" />:</td>
				<td>
				<c:forEach items="${allCacsensors}" var="cacsensor">
				<input type="checkbox" id="relativeCacsensors" name="relativeCacsensors" value="${cacsensor}"
				<c:forEach items="${havedCacsensors}" var="havedCacsensor">
                <c:if test="${havedCacsensor == cacsensor}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${cacsensor.name}
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
