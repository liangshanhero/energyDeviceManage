<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.staff-resources" />
<html>
<head>
<title>Edit<fmt:message key="staff.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="staff.title" /></h1>
		
		<form:form action="${pageContext.request.contextPath}/saveStaff" method="POST" modelAttribute="staff">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.id.title" />:</td>
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="staff_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${staff.id}&nbsp;
						<form:hidden id="staff_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
				  
				    <!--valign="top"> 表示垂直对齐 -->
				
					<td class="label2" valign="top"><fmt:message key="staff.name.title" />:</td>
					<td><form:input id="staff_name" path="name" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><%-- <fmt:message key="staff.duty.title" /> --%>权限:</td>
					<td><%-- <form:input id="staff_duty" path="duty" cssStyle="width:300px;" /> --%>
						<select name="duty">
							<option value="ROLE_USER">系统管理员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.token.title" />:</td>
					<td><form:input id="staff_token" path="token" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.type.title" />:</td>
					<td><form:input id="staff_type" path="type" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.status.title" />:</td>
					<td><form:input id="staff_status" path="status" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.level.title" />:</td>
					<td><form:input id="staff_level" path="level" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.loginname.title" />:</td>
					<td><form:input id="staff_loginname" path="loginname" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.password.title" />:</td>
					<td><form:input id="staff_password" path="password" type="password" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.remark.title" />:</td>
					<td><form:input id="staff_remark" path="remark" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.title" />:</td>
					<td>
						<form:select id="relativeCompany" name="relativeCompany" path="relativeCompany" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allCompanys}" var="company">
						<form:option value="${company}" >${company.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
			<%-- <tr>
				<td valign="top"><fmt:message key="companys.title" />:</td>
				<td>
				<c:forEach items="${allCompanys}" var="company">
				<input type="checkbox" id="relativeCompanys" name="relativeCompanys" value="${company}"
				<c:forEach items="${havedCompanys}" var="havedCompany">
                <c:if test="${havedCompany == company}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${company.name}
				 </c:forEach>
				</td>
			</tr> --%>
			<%-- <tr>
				<td valign="top"><fmt:message key="projects.title" />:</td>
				<td>
				<c:forEach items="${allProjects}" var="project">
				<input type="checkbox" id="relativeProjects" name="relativeProjects" value="${project}"
				<c:forEach items="${havedProjects}" var="havedProject">
                <c:if test="${havedProject == project}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${project.name}
				 </c:forEach>
				</td>
			</tr> --%>
		</tbody>
		</table>
		<span class="inputbutton"><input class="savebutton" id="save" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId : 'save',event : 'onclick'}));</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
