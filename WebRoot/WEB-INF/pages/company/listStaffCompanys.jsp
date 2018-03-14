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
							<th class="thead"><fmt:message key="company.id.title" /></th>
							<th class="thead"><fmt:message key="company.name.title" /></th>
							<th class="thead"><fmt:message key="projects.title" /></th>
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

								<td nowrap="nowrap" class="tabletd">${current.id}&nbsp;</td>
								<td nowrap="nowrap" class="tabletd">${current.name}&nbsp;</td>

								<!-- 获取它的项目值 -->
								<td nowrap="nowrap" class="tabletd"><c:if
										test="${empty(current.relativeProjects) }">
										<span style="color:red">暂无projects</span>
									</c:if> 
									<c:set var="isDone" value="0" />
									<c:forEach
									items="${current.relativeProjects}" var="referenced">
											<br/>
											<a
											href="${pageContext.request.contextPath}/selectCompanyProjects?company_id=${current.id}&page=homePage">${referenced.name}
											</a>
											</c:forEach>
									</td>
								<!-- 项目值的获取============== -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>
