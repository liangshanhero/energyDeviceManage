<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacsensor-resources" />
<html>
<head>
<title>Edit<fmt:message key="cacsensor.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="cacsensor.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacsensor"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveCacsensor" method="POST" modelAttribute="cacsensor">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensor.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacsensor_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacsensor.id}&nbsp;
						<form:hidden id="cacsensor_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacsensor.name.title" />:</td>
					<td><form:input id="cacsensor_name" path="name" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cac.title" />:</td>
					<td>
						<form:select id="relativeCac" name="relativeCac" path="relativeCac" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allCacs}" var="cac">
						<form:option value="${cac}" >${cac.remark}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
			<tr>
				<td valign="top"><fmt:message key="cacsensordatas.title" />:</td>
				<td>
				<c:forEach items="${allCacsensordatas}" var="cacsensordata">
				<input type="checkbox" id="relativeCacsensordatas" name="relativeCacsensordatas" value="${cacsensordata}"
				<c:forEach items="${havedCacsensordatas}" var="havedCacsensordata">
                <c:if test="${havedCacsensordata == cacsensordata}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${cacsensordata.value}
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
