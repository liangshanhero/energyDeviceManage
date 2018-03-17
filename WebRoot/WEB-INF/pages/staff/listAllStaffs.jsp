<%@page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.staff-resources" />
<html>
<head>
<title>List<fmt:message key="staff.title" /></title>
</head>
<body>
	<div id="content">
		<div>
			<div id="tablewrapper">
				<table  id="listTable" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="thead">&nbsp;</th>
							<th class="thead"><fmt:message key="staff.name.title" /></th>
							<th class="thead"><fmt:message key="staff.duty.title" /></th>
							<th class="thead"><fmt:message key="staff.token.title" /></th>
							<th class="thead"><fmt:message key="staff.type.title" /></th>
							<th class="thead"><fmt:message key="staff.status.title" /></th>
							<th class="thead"><fmt:message key="staff.level.title" /></th>
							<th class="thead"><fmt:message key="staff.loginname.title" /></th>
							<th class="thead"><fmt:message key="staff.password.title" /></th>
							<th class="thead"><fmt:message key="staff.remark.title" /></th>
							<th class="thead"><fmt:message key="company.title" /></th>
							<%-- <th class="thead"><fmt:message key="companys.title" /></th> --%>
							<th class="thead"><fmt:message key="projects.title" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${staffs}" var="current" varStatus="i">
							<c:choose>
								<c:when test="${(i.count) % 2 == 0}">
									<c:set var="rowclass" value="rowtwo" />
								</c:when>
								<c:otherwise>
									<c:set var="rowclass" value="rowone" />
								</c:otherwise>
							</c:choose>
							<tr class="${rowclass}">
								<td nowrap="nowrap" class="tabletd">
								<a
									title="<fmt:message key="navigation.edit" />"
									href="${pageContext.request.contextPath}/editStaff?id=${current.id}"><img
										src="images/icons/edit.gif" /></a>
								<a
									title="<fmt:message key="navigation.delete" />"
									href="${pageContext.request.contextPath}/confirmDeleteStaff?id=${current.id}"><img
										src="images/icons/delete.gif" /></a>
								</td>
								<td nowrap="nowrap" class="tabletd">${current.name}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.duty}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.token}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.type}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.status}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.level}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.loginname}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.password}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.remark}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.relativeCompany.name}
									&nbsp;</td>
						
								<td nowrap="nowrap" class="tabletd"><c:if
										test="${empty(current.relativeProjects) }">
										<span style="color:red">暂无项目</span>
									</c:if> <c:set var="isDone" value="0" /> <c:forEach
										items="${current.relativeProjects}" var="referenced"
										varStatus="i">
										<c:if test="${isDone == '0' }">
											<c:choose>
												<c:when test="${i.count <6 }">
				${referenced.name}<br />
												</c:when>
												<c:otherwise>
													<c:set var="isDone" value="1" />
													<a
														href="${pageContext.request.contextPath}/selectStaffProjects?staff_id=${current.id}&page=homePage">更多</a>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach> &nbsp;</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>

</html>
