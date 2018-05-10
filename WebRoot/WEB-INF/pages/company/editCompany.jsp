<%@page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.company-resources" />

<html>
<head>
<title>Edit<fmt:message key="company.title" /></title>
</head>
<body>
	<div id="content">
		<h1>
			<fmt:message key="company.title" />
		</h1>
		<form:form action="${pageContext.request.contextPath}/saveCompany"
			method="POST" modelAttribute="company">
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<%-- <td class="label2" valign="top"><fmt:message
								key="company.id.title" />:</td>
 --%>						
                           <td><c:choose>
								<c:when test='${newFlag}'>
									<form:hidden id="company_id" path="id" cssStyle="width:300px;" />
								</c:when>
								<c:otherwise>
						${company.id}&nbsp;
						<form:hidden id="company_id" path="id" />
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.name.title" />:</td>
						<td><form:input id="company_name" path="name"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.phone.title" />:</td>
						<td><form:input id="company_phone" path="phone"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.fax.title" />:</td>
						<td><form:input id="company_fax" path="fax"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.postcode.title" />:</td>
						<td><form:input id="company_postcode" path="postcode"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.address.title" />:</td>
						<td><form:input id="company_address" path="address"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.website.title" />:</td>
						<td><form:input id="company_website" path="website"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.personduty.title" />:</td>
						<td><form:input id="company_personduty" path="personduty"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.detail.title" />:</td>
						<td><form:input id="company_detail" path="detail"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><fmt:message
								key="company.remark.title" />:</td>
						<td><form:input id="company_remark" path="remark"
								cssStyle="width:300px;" /></td>
					</tr>
					<tr>
						<td class="label2" valign="top"><%-- <fmt:message key="staff.title" /> --%>法人:</td>
						<td><form:select id="relativeStaff" name="relativeStaff"
								path="relativeStaff" cssStyle="width:300px;">
								<option></option>
								<c:forEach items="${allStaffs}" var="staff">
									<form:option value="${staff}">${staff.name}</form:option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<td valign="top"><fmt:message key="projects.title" />:</td>
						<td><c:forEach items="${allProjects}" var="project">
								<input type="checkbox" id="relativeProjects"
									name="relativeProjects" value="${project}"
									<c:forEach items="${havedProjects}" var="havedProject">
                <c:if test="${havedProject == project}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${project.name}
				 </c:forEach></td>
					</tr>
					<tr>
						<td valign="top"><fmt:message key="staffs.title" />:</td>
						<td><c:forEach items="${allStaffs}" var="staff">
								<input type="checkbox" id="relativeStaffs" name="relativeStaffs"
									value="${staff}"
									<c:forEach items="${havedStaffs}" var="havedStaff">
                <c:if test="${havedStaff == staff}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${staff.name}
				 </c:forEach></td>
					</tr>
				</tbody>
			</table>
			<span class="inputbutton"><input class="savebutton" id="save"
				type="submit" value="<fmt:message key="navigation.save"/>" /></span>
			<script type="text/javascript">
				Spring.addDecoration(new Spring.ValidateAllDecoration({
					elementId : 'save',
					event : 'onclick'
				}));
			</script>
		</form:form>
		<div class="clear">&nbsp;</div>
	</div>
</body>
</html>
