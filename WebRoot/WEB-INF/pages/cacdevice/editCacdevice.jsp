<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacdevice-resources" />
<html>
<head>
<title>Edit<fmt:message key="cacdevice.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="cacdevice.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacdevice"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveCacdevice" method="POST" modelAttribute="cacdevice">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevice.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacdevice_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacdevice.id}&nbsp;
						<form:hidden id="cacdevice_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacdevice.name.title" />:</td>
					<td><form:input id="cacdevice_name" path="name" cssStyle="width:300px;" /></td>
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
				<td valign="top"><fmt:message key="cacdevicedatas.title" />:</td>
				<td>
				<c:forEach items="${allCacdevicedatas}" var="cacdevicedata">
				<input type="checkbox" id="relativeCacdevicedatas" name="relativeCacdevicedatas" value="${cacdevicedata}"
				<c:forEach items="${havedCacdevicedatas}" var="havedCacdevicedata">
                <c:if test="${havedCacdevicedata == cacdevicedata}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${cacdevicedata.value}
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td valign="top"><fmt:message key="cacmalfunctions.title" />:</td>
				<td>
				<c:forEach items="${allCacmalfunctions}" var="cacmalfunction">
				<input type="checkbox" id="relativeCacmalfunctions" name="relativeCacmalfunctions" value="${cacmalfunction}"
				<c:forEach items="${havedCacmalfunctions}" var="havedCacmalfunction">
                <c:if test="${havedCacmalfunction == cacmalfunction}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${cacmalfunction.status}
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
