<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.cacrecordtime-resources" />
<html>
<head>
<title>Edit<fmt:message key="cacrecordtime.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="cacrecordtime.title" /></h1>
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexCacrecordtime"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		<form:form action="${pageContext.request.contextPath}/saveCacrecordtime" method="POST" modelAttribute="cacrecordtime">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacrecordtime.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="cacrecordtime_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${cacrecordtime.id}&nbsp;
						<form:hidden id="cacrecordtime_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacrecordtime.RecordTime.title" />:</td>
					<td>
						<input id="cacrecordtime_RecordTime" name="RecordTime" type="text" value="<fmt:formatDate value="${cacrecordtime.recordTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="label" valign="top"><fmt:message key="cacrecordtime.Watchkeeper.title" />:</td>
					<td><form:input id="cacrecordtime_Watchkeeper" path="watchkeeper" cssStyle="width:300px;" /></td>
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
