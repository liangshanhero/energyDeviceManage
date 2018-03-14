<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.whdevice-resources" />
<html>
<head>
<title>Edit<fmt:message key="whdevice.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="whdevice.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexWhdevice"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveWhdevice" method="POST" modelAttribute="whdevice">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevice.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="whdevice_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${whdevice.id}&nbsp;
						<form:hidden id="whdevice_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whdevice.number.title" />:</td>
					<td><form:input id="whdevice_number" path="number" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="whbuilding.title" />:</td>
					<td>
						<form:select id="relativeWhbuilding" name="relativeWhbuilding" path="relativeWhbuilding" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allWhbuildings}" var="whbuilding">
						<form:option value="${whbuilding}" >${whbuilding.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
				<tr>
					<td valign="top"><fmt:message key="whdatatypes.title" />:</td>
					<td>
					<c:forEach items="${allWhdatatypes}" var="whdatatype">
					<input type="checkbox" id="relativeWhdatatypes" name="relativeWhdatatypes" value="${whdatatype}"
					<c:forEach items="${havedWhdatatypes}" var="havedWhdatatype">
                    <c:if test="${havedWhdatatype == whdatatype}">
                    checked="checked"
                    </c:if>
              	    </c:forEach> />
					${whdatatype.name}
					</c:forEach>
					</td>
				</tr>
					</td>
				</tr>
			<tr>
				<td valign="top"><fmt:message key="whdevicedatas.title" />:</td>
				<td>
				<c:forEach items="${allWhdevicedatas}" var="whdevicedata">
				<input type="checkbox" id="relativeWhdevicedatas" name="relativeWhdevicedatas" value="${whdevicedata}"
				<c:forEach items="${havedWhdevicedatas}" var="havedWhdevicedata">
                <c:if test="${havedWhdevicedata == whdevicedata}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${whdevicedata.value}
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
