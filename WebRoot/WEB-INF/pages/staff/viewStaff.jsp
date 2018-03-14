<%@page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp" />
<fmt:setBundle basename="bundles.staff-resources" />
<html>
<head>
<title>View<fmt:message key="staff.title"/></title>
</head>
<body>
	<div id="contentarea">
		<div id="content">
			<h1><fmt:message key="staff.title"/>Details</h1>
			<h1><fmt:message key="staff.title"/>Details</h1>
<div class="navitem"><a class="button" href="${pageContext.request.contextPath}/indexStaff?page=homePage"><span><img src="images/icons/back.gif" /><fmt:message key="navigation.back"/></span></a></div>	
				<table cellpadding="0" cellspacing="0" id="viewTable">
					<tbody>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.id.title"/>:
							</td>
							<td>
							${staff.id}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.name.title"/>:
							</td>
							<td>${staff.name}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.duty.title"/>:
							</td>
							<td>${staff.duty}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.token.title"/>:
							</td>
							<td>${staff.token}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.type.title"/>:
							</td>
							<td>${staff.type}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.status.title"/>:
							</td>
							<td>${staff.status}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.level.title"/>:
							</td>
							<td>${staff.level}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.loginname.title"/>:
							</td>
							<td>${staff.loginname}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.password.title"/>:
							</td>
							<td>${staff.password}&nbsp;</td>
						</tr>
						<tr>
							<td class="label" valign="top">
							<fmt:message key="staff.remark.title"/>:
							</td>
							<td>${staff.remark}&nbsp;</td>
						</tr>
				</tbody>
			</table>
			<h1><fmt:message key="company.title"/></h1>
			<c:if test='${staff.relativeCompany != null}'>
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<tbody>
					<tr>
						<td  class="label">
							<fmt:message key="company.id.title"/>:
						</td>
						<td>
							${staff.relativeCompany.id}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.name.title"/>:
						</td>
						<td>
							${staff.relativeCompany.name}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.phone.title"/>:
						</td>
						<td>
							${staff.relativeCompany.phone}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.fax.title"/>:
						</td>
						<td>
							${staff.relativeCompany.fax}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.postcode.title"/>:
						</td>
						<td>
							${staff.relativeCompany.postcode}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.address.title"/>:
						</td>
						<td>
							${staff.relativeCompany.address}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.website.title"/>:
						</td>
						<td>
							${staff.relativeCompany.website}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.personduty.title"/>:
						</td>
						<td>
							${staff.relativeCompany.personduty}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.detail.title"/>:
						</td>
						<td>
							${staff.relativeCompany.detail}
						&nbsp;
						</td>
					</tr>
					<tr>
						<td  class="label">
							<fmt:message key="company.remark.title"/>:
						</td>
						<td>
							${staff.relativeCompany.remark}
						&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
			</c:if>

			<h1><fmt:message key="companys.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=eachPageShow&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}" method="post">
				选择每页显示<input type="text" id="companyPageSize" name="companyPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="company.id.title"/></th>
						<th class="thead"><fmt:message key="company.name.title"/></th>
						<th class="thead"><fmt:message key="company.phone.title"/></th>
						<th class="thead"><fmt:message key="company.fax.title"/></th>
						<th class="thead"><fmt:message key="company.postcode.title"/></th>
						<th class="thead"><fmt:message key="company.address.title"/></th>
						<th class="thead"><fmt:message key="company.website.title"/></th>
						<th class="thead"><fmt:message key="company.personduty.title"/></th>
						<th class="thead"><fmt:message key="company.detail.title"/></th>
						<th class="thead"><fmt:message key="company.remark.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${companys}" var="current"  varStatus="i">
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td>
							${current.id}
						&nbsp;
						</td>
						<td>
							${current.name}
						&nbsp;
						</td>
						<td>
							${current.phone}
						&nbsp;
						</td>
						<td>
							${current.fax}
						&nbsp;
						</td>
						<td>
							${current.postcode}
						&nbsp;
						</td>
						<td>
							${current.address}
						&nbsp;
						</td>
						<td>
							${current.website}
						&nbsp;
						</td>
						<td>
							${current.personduty}
						&nbsp;
						</td>
						<td>
							${current.detail}
						&nbsp;
						</td>
						<td>
							${current.remark}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=homePage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=previousPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">上一页</a></td>
				<c:if test="${companyTotalPage>=7}">
				<c:if test="${companyPageNumber-2>0&&companyPageNumber+4<=companyTotalPage}">
				<%String pageCount2 = request.getAttribute("companyPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=jumpPage&companyWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${companyPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=jumpPage&companyWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${companyPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("companyTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=jumpPage&companyWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${companyTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("companyTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=jumpPage&companyWantToPage=<%=i%>&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=nextPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=lastPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${companyPageNumber+1}页         每页显示${companyPageSize}条      总共${companyTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&companyPage=jumpPage&projectPage=${projectPage}&projectPageSize=${projectPageSize}&projectWantToPage=${projectWantToPage}" method="post">
					跳转到第<input type="text" id="companyWantToPage" name="companyWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<h1><fmt:message key="projects.title"/></h1>
			<form action="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=eachPageShow&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}" method="post">
				选择每页显示<input type="text" id="projectPageSize" name="projectPageSize" style="height:15px;width:50px">条 
				<input type="submit" id="sure" value="确认" style="height:22px;width:35px">
			</form>	
			<table cellpadding="0" cellspacing="0" id="viewTable">
				<thead>
					<tr>
						<th class="thead"><fmt:message key="project.id.title"/></th>
						<th class="thead"><fmt:message key="project.name.title"/></th>
						<th class="thead"><fmt:message key="project.type.title"/></th>
						<th class="thead"><fmt:message key="project.status.title"/></th>
						<th class="thead"><fmt:message key="project.province.title"/></th>
						<th class="thead"><fmt:message key="project.city.title"/></th>
						<th class="thead"><fmt:message key="project.area.title"/></th>
						<th class="thead"><fmt:message key="project.address.title"/></th>
						<th class="thead"><fmt:message key="project.detail.title"/></th>
						<th class="thead"><fmt:message key="project.remark.title"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${projects}" var="current"  varStatus="i">
						<c:choose>
							<c:when test="${(i.count) % 2 == 0}">
					    		<c:set var="rowclass" value="rowtwo"/>
							</c:when>
							<c:otherwise>
					    		<c:set var="rowclass" value="rowone"/>
							</c:otherwise>
						</c:choose>
					<tr class="${rowclass}">
						<td>
							${current.id}
						&nbsp;
						</td>
						<td>
							${current.name}
						&nbsp;
						</td>
						<td>
							${current.type}
						&nbsp;
						</td>
						<td>
							${current.status}
						&nbsp;
						</td>
						<td>
							${current.province}
						&nbsp;
						</td>
						<td>
							${current.city}
						&nbsp;
						</td>
						<td>
							${current.area}
						&nbsp;
						</td>
						<td>
							${current.address}
						&nbsp;
						</td>
						<td>
							${current.detail}
						&nbsp;
						</td>
						<td>
							${current.remark}
						&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<tr>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=homePage&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}">首页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=previousPage&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}">上一页</a></td>
				<c:if test="${projectTotalPage>=7}">
				<c:if test="${projectPageNumber-2>0&&projectPageNumber+4<=projectTotalPage}">
				<%String pageCount2 = request.getAttribute("projectPageNumber").toString();
				int pageCount = Integer.parseInt(pageCount2);
				for(int i=pageCount-2;i<pageCount+5;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<c:if test="${projectPageNumber-2<=0}">
				<%for(int i=1;i<8;i++){%>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}"><%=i%></a></td>
				<%}%>
			    </c:if>
			    <c:if test="${projectPageNumber+4>totalPage }">
			     <%String pageCount2 = request.getAttribute("projectTotalPage").toString();
				int pageCount = Integer.parseInt(pageCount2);
			   	for(int i=pageCount-5;i<pageCount+2;i++){%>
			    <td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}"><%=i%></a></td>
			   <%}%>
			   	</c:if>
			    </c:if>				
				<c:if test="${projectTotalPage<7}">
				<% 
						String pageCount2 = request.getAttribute("projectTotalPage").toString();
						int pageCount = Integer.parseInt(pageCount2);
						for (int i = 1; i <= pageCount+1; i++) {
				%>
				 <td><a href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=jumpPage&projectWantToPage=<%=i%>&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}"><%=i%></a></td>
				<%}%>
				</c:if>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=nextPage&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}">下一页</a></td>
				<td><a  href="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=lastPage&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}">尾页</a></td>
				</tr><br>
				当前为第${projectPageNumber+1}页         每页显示${projectPageSize}条      总共${projectTotalPage+1}页<br>
				<form action="${pageContext.request.contextPath}/selectStaff?id=${staff.id}&projectPage=jumpPage&companyPage=${companyPage}&companyPageSize=${companyPageSize}&companyWantToPage=${companyWantToPage}" method="post">
					跳转到第<input type="text" id="projectWantToPage" name="projectWantToPage" style="height:15px;width:100px">页 
					<input type="submit" id="skip" value="跳转" style="height:22px;width:35px">
				</form>
			<div class="clear">&nbsp;</div>
		</div>
	</div>
</body>
</html>
