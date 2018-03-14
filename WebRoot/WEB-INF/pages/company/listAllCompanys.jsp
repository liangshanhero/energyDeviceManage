<%@page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.company-resources" />

<html>
<head>
<title>List<fmt:message key="company.title" /></title>
</head>
<body>
	<div id="content">
		<div>
			<div id="tablewrapper">
				<table id="listTable" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="thead">&nbsp;</th>
							<th class="thead"><fmt:message key="company.id.title" /></th>
							<th class="thead"><fmt:message key="company.name.title" /></th>
							<th class="thead"><fmt:message key="company.phone.title" /></th>
							<th class="thead"><fmt:message key="company.fax.title" /></th>
							<th class="thead"><fmt:message key="company.postcode.title" /></th>
							<th class="thead"><fmt:message key="company.address.title" /></th>
							<th class="thead"><fmt:message key="company.website.title" /></th>
							<th class="thead"><fmt:message key="company.personduty.title" /></th>
							<th class="thead"><fmt:message key="company.detail.title" /></th>
							<th class="thead"><fmt:message key="company.remark.title" /></th>
							<th class="thead"><fmt:message key="staff.title" /></th>
							<th class="thead"><fmt:message key="projects.title" /></th>
							<th class="thead"><fmt:message key="staffs.title" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${companys}" var="current" varStatus="i">
							<c:choose>
								<c:when test="${(i.count) % 2 == 0}">
									<c:set var="rowclass" value="rowtwo" />
								</c:when>
								<c:otherwise>
									<c:set var="rowclass" value="rowone" />
								</c:otherwise>
							</c:choose>
							<tr class="${rowclass}">

								<td nowrap="nowrap" class="tabletd"><a
									title="<fmt:message key="navigation.edit" />"
									href="${pageContext.request.contextPath}/editCompany?id=${current.id}">
										<img src="images/icons/edit.gif" />
								</a> <a title="<fmt:message key="navigation.delete" />"
									href="${pageContext.request.contextPath}/confirmDeleteCompany?id=${current.id}">
										<img src="images/icons/delete.gif" />
								</a></td>

								<td nowrap="nowrap" class="tabletd">${current.id}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.name}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.phone}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.fax}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.postcode}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.address}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.website}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.personduty}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.detail}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.remark}
									&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.relativeStaff.name}
									&nbsp;</td>

								<!-- 获取它的项目值 -->
								<td nowrap="nowrap" class="tabletd"><c:if
										test="${empty(current.relativeProjects) }">
										<span style="color:red">暂无projects</span>
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
														href="${pageContext.request.contextPath}/selectCompanyProjects?company_id=${current.id}&page=homePage">更多</a>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach> &nbsp;</td>
								<!-- 项目值的获取============== -->
								<td nowrap="nowrap" class="tabletd"><c:if
										test="${empty(current.relativeStaffs) }">
										<span style="color:red">暂无staffs</span>
									</c:if> <c:set var="isDone" value="0" /> <c:forEach
										items="${current.relativeStaffs}" var="referenced"
										varStatus="i">
										<c:if test="${isDone == '0' }">
											<c:choose>
												<c:when test="${i.count <6 }">
				${referenced.name}<br />
												</c:when>
												<c:otherwise>
													<c:set var="isDone" value="1" />
													<a
														href="${pageContext.request.contextPath}/selectCompanyStaffs?company_id=${current.id}&page=homePage">更多</a>
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
