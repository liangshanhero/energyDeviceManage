<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.project-resources" />
<html>
<head>
<title>Edit<fmt:message key="project.title"/></title>
</head>
<body>
	<div id="content">
		<h1><fmt:message key="navigation.edit" /><fmt:message key="project.title" /></h1>
		
		<%-- 
		<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexProject"><span><img src="images/icons/back.gif" /> <fmt:message key="navigation.back" /></span></a></div>
		 --%>
		 
		<form:form action="${pageContext.request.contextPath}/saveProject" method="POST" modelAttribute="project">
		<table cellpadding="0" cellspacing="0" id="viewTable">
		<tbody>
				<%-- <tr>			
					<td class="label2" valign="top"><fmt:message key="project.id.title" />:</td>
					
		 
					<td>
						<c:choose>
						<c:when test='${newFlag}'>
						<form:input id="project_id" path="id" cssStyle="width:300px;" />
						</c:when>
						<c:otherwise>
						${project.id}&nbsp;
						<form:hidden id="project_id" path="id" />
						</c:otherwise>
						</c:choose>
					</td>
				</tr> --%>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.name.title" />:</td>
					<td><form:input id="project_name" path="name" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.type.title" />:</td>
					<td><form:input id="project_type" path="type" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.status.title" />:</td>
					<td><form:input id="project_status" path="status" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.province.title" />:</td>
					<td><form:input id="project_province" path="province" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.city.title" />:</td>
					<td><form:input id="project_city" path="city" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.area.title" />:</td>
					<td><form:input id="project_area" path="area" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.address.title" />:</td>
					<td><form:input id="project_address" path="address" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.detail.title" />:</td>
					<td><form:input id="project_detail" path="detail" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="project.remark.title" />:</td>
					<td><form:input id="project_remark" path="remark" cssStyle="width:300px;" /></td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="company.title" />:</td>
					<td>
						<form:select id="relativeCompany" name="company" path="company" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allCompanys}" var="company">
						<form:option value="${company.id}" >${company.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
				<tr>
					<td class="label2" valign="top"><fmt:message key="staff.title" />:</td>
					<td>
						<form:select id="relativeStaff" name="staff" path="staff" cssStyle="width:300px;">
						<option></option>
						<c:forEach items="${allStaffs}" var="staff">
						<form:option value="${staff.id}" >${staff.name}</form:option>
						</c:forEach>
						</form:select> 
					</td>
				</tr>
			<tr>
				<td valign="top"><fmt:message key="cacs.title" />:</td>
				<td>
				<c:forEach items="${allCacs}" var="cac">
				<input type="checkbox" id="relativeCacs" name="relativeCacs" value="${cac}"
				<c:forEach items="${havedCacs}" var="havedCac">
                <c:if test="${havedCac == cac}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${cac.remark}
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td valign="top"><fmt:message key="ledbuildings.title" />:</td>
				<td>
				<c:forEach items="${allLedbuildings}" var="ledbuilding">
				<input type="checkbox" id="relativeLedbuildings" name="relativeLedbuildings" value="${ledbuilding}"
				<c:forEach items="${havedLedbuildings}" var="havedLedbuilding">
                <c:if test="${havedLedbuilding == ledbuilding}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${ledbuilding.name}
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td valign="top"><fmt:message key="whbuildings.title" />:</td>
				<td>
				<c:forEach items="${allWhbuildings}" var="whbuilding">
				<input type="checkbox" id="relativeWhbuildings" name="relativeWhbuildings" value="${whbuilding}"
				<c:forEach items="${havedWhbuildings}" var="havedWhbuilding">
                <c:if test="${havedWhbuilding == whbuilding}">
                   checked="checked"
                </c:if>
                </c:forEach> />
				${whbuilding.name}
				 </c:forEach>
				</td>
			</tr>
		</tbody>
		</table>
		<span style="display:none" class="inputbutton"><input class="savebutton"  id="save" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId : 'save',event : 'onclick'}));</script>
		</form:form>
		<span class="inputbutton"><input class="savebutton" id="newsave" type="submit" value="<fmt:message key="navigation.save"/>" /></span>
		<div class="clear">&nbsp;</div>
	</div>
		<script type="text/javascript">

		var save=document.getElementById("save");
		var newsave=document.getElementById("newsave");
		newsave.onclick=function()
		{
			var Company=$("#relativeCompany").val();			
			if(Company.trim()=="")
			alert("公司不能为空!");
			else
			$("#save").click();
		}
	
</script>
</body>
</html>
